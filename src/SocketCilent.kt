import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

object SocketCilent:StringUtils{
    @JvmStatic
    fun main(args: Array<String>) {
        var socket = Socket("127.0.0.1",9898)

        //客户端向服务端发送数据
        var writer = socket.getOutputStream().bufferedWriter()
        var reader = socket.getInputStream().bufferedReader()
        var read = BufferedReader(InputStreamReader(System.`in`))
        startServerReplyListener(reader)

        var content:String=read.readLine()
        while (content!="bye"){
            writer.write(content+"\n")
            writer.flush()
            //客户端读取服务端发来的数据
            content=read.readLine()
        }

    }

    fun startServerReplyListener(reader: BufferedReader){
        Thread{
            var res = reader.readLine()
            while (res!=null){
                res.pln()
                res = reader.readLine()
            }
        }.start()
    }

}




