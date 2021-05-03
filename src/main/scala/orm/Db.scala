package orm

import java.sql.{Connection, DriverManager}

object Db {
  classOf[org.postgresql.Driver]

  var url: String = ""

  def setUrl(url: String): Unit = this.url = url

  def connection: Connection = DriverManager.getConnection(url)
}
