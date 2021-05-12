package common

import java.text.SimpleDateFormat
import java.util.Date

object StringUtils {
  val DateFormat = "yyyy-MM-dd'T'hh:mm:ss'Z'"

  def getDateAsString(d: Date): String = {
    val dateFormat = new SimpleDateFormat(DateFormat)
    dateFormat.format(d)
  }

  def convertStringToDate(s: String): Date = {
    val dateFormat = new SimpleDateFormat(DateFormat)
    dateFormat.parse(s)
  }
}
