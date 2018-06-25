package mina

import org.apache.mina.core.session.IdleStatus
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import org.apache.mina.transport.socket.nio.NioSocketAcceptor
import java.net.InetSocketAddress

object MinaServer {

    @JvmStatic
    fun main(args: Array<String>) {
        var acceptor = NioSocketAcceptor()
        acceptor.handler = MyServerHandler()
        acceptor.filterChain.addLast("codec",
                ProtocolCodecFilter(MyTextLineFactory()))
                //ProtocolCodecFilter(TextLineCodecFactory()))
        acceptor.sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE,60)
        acceptor.bind(InetSocketAddress(9898))
    }

}