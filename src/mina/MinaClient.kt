package mina

import org.apache.mina.core.service.IoHandlerAdapter
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolCodecFactory
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import org.apache.mina.transport.socket.nio.NioSocketConnector
import java.net.InetSocketAddress

object MinaClient {

    @JvmStatic
    fun main(args: Array<String>) {
        var connector = NioSocketConnector()
        connector.handler =MyClientHandler()
        connector.filterChain.addLast("codec",
                ProtocolCodecFilter(TextLineCodecFactory()))
        var future = connector.connect(InetSocketAddress("127.0.0.1",9898))
        future.awaitUninterruptibly()
        var session = future.session
        var line:String = readLine().toString()
        while (line!="bye"){
            session.write(line)
            line = readLine().toString()
        }
    }

    class MyClientHandler:IoHandlerAdapter(){
        override fun inputClosed(session: IoSession?) {
            println("inputClosed")
        }

        override fun messageReceived(session: IoSession?, message: Any?) {
            println("messageReceived：${message.toString()}")
        }

        override fun sessionOpened(session: IoSession?) {
            println("sessionOpened")
        }

        override fun sessionClosed(session: IoSession?) {
            println("sessionClosed")
        }

        override fun messageSent(session: IoSession?, message: Any?) {
            println("messageSent")
        }

        override fun sessionCreated(session: IoSession?) {
            println("sessionCreated")
        }

        override fun sessionIdle(session: IoSession?, status: IdleStatus?) {
            println("sessionIdle")
        }

        override fun exceptionCaught(session: IoSession?, cause: Throwable?) {
            println("exceptionCaught ${cause.toString()}")
        }
    }

}

