package mina

import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.CumulativeProtocolDecoder
import org.apache.mina.filter.codec.ProtocolDecoderOutput

class MyCumulativeDecoder: CumulativeProtocolDecoder() {

    override fun doDecode(session: IoSession?, buffer: IoBuffer?, out: ProtocolDecoderOutput?): Boolean {
        var startPos = buffer!!.position()
        while (buffer!!.hasRemaining()){
            var b = buffer.get()
            if(b.toChar()=='\n'){
                var currPos = buffer.position()
                var limit = buffer.limit()
                buffer.position(startPos)
                buffer.limit(currPos)
                var sliceBuf = buffer.slice()
                var dest = ByteArray(sliceBuf.limit())
                sliceBuf.get(dest)
                var str = String(dest)
                out!!.write(str)
                buffer.position(currPos)
                buffer.limit(limit)
                return true
            }
        }
        buffer.position(startPos)
        return false
    }
}