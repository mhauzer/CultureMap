package orm

import java.sql.ResultSet

trait Orm[T] {
  val GetAll: String

  def getList(rs: ResultSet): List[T]

  def getPgValue(rs: ResultSet, colName: String): String = {
    val value = rs.getString(colName)

    if (value == null) "" else value
  }

  def getPgValueAsArray(rs: ResultSet, colName: String): Array[String] = {
    getPgValue(rs, colName).split(" ").filter(_.nonEmpty)
  }

  def getPgIntValue(rs: ResultSet, colName: String): Option[Int] = {
    val value = getPgValue(rs, colName)

    if (value.nonEmpty) Some(value.toInt) else None
  }

  def getAll: List[T] = {
    val stm = Db.connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
    getList(stm.executeQuery(GetAll))
  }
}
