package mina

import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolCodecFactory
import org.apache.mina.filter.codec.ProtocolDecoder
import org.apache.mina.filter.codec.ProtocolEncoder

class MyTextLineFactory:ProtocolCodecFactory {

    var decoder:MyTextLineDecoder = MyTextLineDecoder()
    var encoder:MyTextLineEncoder = MyTextLineEncoder()
    var decoder1:MyCumulativeDecoder = MyCumulativeDecoder()

    /**
     * 解码
     */
    override fun getDecoder(session: IoSession?): ProtocolDecoder {
        return decoder1
    }

    /**
     * 编码
     */
    override fun getEncoder(session: IoSession?): ProtocolEncoder {
        return encoder
    }
}

