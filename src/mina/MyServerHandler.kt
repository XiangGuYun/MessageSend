package mina

import org.apache.mina.core.service.IoHandlerAdapter
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession

class MyServerHandler: IoHandlerAdapter() {

    override fun inputClosed(session: IoSession?) {
        println("inputClosed")
    }

    override fun messageReceived(session: IoSession?, message: Any?) {
        println("messageReceived：${message.toString()}")
        session?.write("server replay：${message.toString()}")
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