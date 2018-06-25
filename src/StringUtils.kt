import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

interface StringUtils {

    fun Any.pln(){
        println(this.toString())
    }

    /*
    打印日期
     */
    fun Long.fmtDate(fmt:String):String{
        return SimpleDateFormat(fmt).format(Date(this))
    }

    /**
     * 转为UTF-8
     */
    fun String.utf8():String{
        try {
            return URLEncoder.encode(this, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("UnsupportedEncodingException occurred. ", e)
        }
    }

}