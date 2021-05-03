package orm

import java.sql.{Connection, DriverManager}

object Db {
  var url: String = ""
  classOf[org.postgresql.Driver]
  def setUrl(url: String): Unit = this. url = url
  def connection: Connection = DriverManager.getConnection(url)
}
