import java.net.ServerSocket
import java.net.Socket
import java.util.*

object SocketServer :StringUtils{

    @JvmStatic
    fun main(args: Array<String>) {
        var server = ServerSocket(9898)
        println("服务器启动了")
        while (true){
            var socket = server.accept()
            manageConn(socket)
        }
    }

    fun manageConn(socket: Socket){
        Thread{
            println("有客户端连入")
            //服务器读取客户端的数据
            var reader = socket.getInputStream().bufferedReader()
            //服务器想客户端发送数据
            var writer = socket.getOutputStream().bufferedWriter()

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    "正在心跳".pln()
                    writer.write("心跳了一次\n")//一定要加换行符！！！
                    writer.flush()//别忘了flush
                }
            },3000,3000)

            var recMsg = reader.readLine()
            while (recMsg!="bye"){
                println(recMsg)
                recMsg = reader.readLine()
                writer.write("服务器回应：收到你的消息 $recMsg\n")
            }

        }.start()
    }

}