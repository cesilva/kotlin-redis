package io.corbs.redis

import org.springframework.util.ObjectUtils
import java.net.InetAddress
import java.net.UnknownHostException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object AppUtils{
    fun getJavaVersion(): String {
        var javaVersion = System.getProperty("java.version")
        val pos = javaVersion.indexOf("-")
        if (pos > -1) {
            javaVersion = javaVersion.substring(0, pos)
        }
        return javaVersion
    }

    fun getIp(): InetAddress? {
        var ip: InetAddress? = null
        try {
            ip = InetAddress.getLocalHost()
            println("Your current IP address : " + ip!!)
        } catch (ex: UnknownHostException) {
            ex.printStackTrace()
        }

        return ip
    }

    // The formats are as follows. Exactly the components shown here must be
    // present, with exactly this punctuation. Note that the "T" appears literally
    // in the string, to indicate the beginning of the time element, as specified in
    // ISO 8601.

    // Year:
    // YYYY (eg 1997)
    // Year and month:
    // YYYY-MM (eg 1997-07)
    // Complete date:
    // YYYY-MM-DD (eg 1997-07-16)
    // Complete date plus hours and minutes:
    // YYYY-MM-DDThh:mmTZD (eg 1997-07-16T19:20+01:00)
    // Complete date plus hours, minutes and seconds:
    // YYYY-MM-DDThh:mm:ssTZD (eg 1997-07-16T19:20:30+01:00)
    // Complete date plus hours, minutes, seconds and a decimal fraction of a
    // second
    // YYYY-MM-DDThh:mm:ss.sTZD (eg 1997-07-16T19:20:30.45+01:00)

    // where:

    // YYYY = four-digit year
    // MM = two-digit month (01=January, etc.)
    // DD = two-digit day of month (01 through 31)
    // hh = two digits of hour (00 through 23) (am/pm NOT allowed)
    // mm = two digits of minute (00 through 59)
    // ss = two digits of second (00 through 59)
    // s = one or more digits representing a decimal fraction of a second
    // TZD = time zone designator (Z or +hh:mm or -hh:mm)
    @Throws(java.text.ParseException::class)
    fun parseDate(input: String): Date {
        var input = input

        // NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        // things a bit. Before we go on we have to repair this.
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")

        // this is zero time so we need to add that TZ indicator for
        if (input.endsWith("Z")) {
            input = input.substring(0, input.length - 1) + "GMT-00:00"
        } else {
            val inset = 6
            val s0 = input.substring(0, input.length - inset)
            val s1 = input.substring(input.length - inset, input.length)
            input = s0 + "GMT" + s1
        }

        return df.parse(input)
    }

    /**
     * Same as parseDate method but does not throw.
     * In case input date string cannot be parsed, null is returned.
     */
    fun parseDateSilently(input: String): Date? {
        try {
            return if (ObjectUtils.isEmpty(input)) null else parseDate(input)
        } catch (e: ParseException) {
            return null
        }

    }

    fun dateAsString(date: Date): String {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val tz = TimeZone.getTimeZone("UTC")
        df.timeZone = tz
        val output = df.format(date)
        return output.replace("UTC".toRegex(), "+00:00")
    }

    fun now(): String {
        return dateAsString(Date())
    }
}