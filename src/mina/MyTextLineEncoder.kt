package mina

import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolEncoder
import org.apache.mina.filter.codec.ProtocolEncoderOutput
import java.nio.charset.Charset
import java.nio.charset.CharsetEncoder

class MyTextLineEncoder: ProtocolEncoder {

    var encoder: CharsetEncoder?= null

    override fun encode(session: IoSession?, message: Any?, out: ProtocolEncoderOutput?) {
        var s = ""
        if(session!!.getAttribute("encoder")==null){
            encoder = Charset.forName("utf-8").newEncoder()
            session.setAttribute("encoder",encoder)
        }
//        val encoder: CharsetEncoder = Charset.forName("utf-8").newEncoder()
        if(message is String){
            s = message
        }
        var buffer = IoBuffer.allocate(s.length)
        buffer.isAutoExpand = true
        buffer.putString(s,encoder)
        buffer.flip()
        out?.write(buffer)
    }

    override fun dispose(session: IoSession?) {
    }

}