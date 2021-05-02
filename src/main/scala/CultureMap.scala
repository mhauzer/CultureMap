import java.sql.{Connection, DriverManager, ResultSet}
import scala.language.postfixOps

class Person(name: String, birth: String, death: String) {
  override def toString: String = name + (if (birth != death) s" ($birth - $death)" else "")
}

class Album(album: String, categories: Array[String], releaseYear: Option[Int]) {
  override def toString: String = s"$album${if (categories.nonEmpty) " " + (categories mkString " ") else ""}" + (if (releaseYear.nonEmpty) s" (${releaseYear.get})" else "")
}

class AlbumOrm(override val conn: Connection) extends Orm[Album] {
  override val GetAll =
    """
SELECT
	COALESCE(b."name" || ' - ', '') || a.title album,
	string_agg(af.item_label, ' ') categories,
  release_year
FROM
	t_album a
	LEFT OUTER JOIN t_performing_band pb ON a.id = pb.album_id
	LEFT OUTER JOIN t_band b ON b.id = pb.band_id
	LEFT OUTER JOIN t_album_tag af ON a.id = af.album_id
GROUP BY
	a.id, b.id
ORDER BY
	release_year, b."name", a.title
"""

  override def getList(rs: ResultSet): List[Album] =
    if (!rs.next)
      List()
    else
      new Album(getPgValue(rs, "album"), getPgValueAsArray(rs, "categories"), getPgIntValue(rs, "release_year")) :: getList(rs)
}

class PersonOrm(override val conn: Connection) extends Orm[Person] {
  override val GetAll =
    """
SELECT
	   COALESCE(title || ' ', '')
  || COALESCE(firstname || ' ', '')
	|| COALESCE(firstname2 || ' ', '')
	|| COALESCE(nameprefix || ' ', '')
	|| COALESCE(lastname, '')
	|| COALESCE('-' || lastname2, '') "name",
	extract (year from birth) as birth,
  extract (year from death) as death
FROM
	t_person
ORDER BY
	birth, lastname
"""

  override def getList(rs: ResultSet): List[Person] =
    if (!rs.next)
      List()
    else
      new Person(getPgValue(rs, "name"), getPgValue(rs, "birth"), getPgValue(rs, "death")) :: getList(rs)
}

trait Orm[T] {
  val conn: Connection
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
    val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
    getList(stm.executeQuery(GetAll))
  }
}

object CultureMap extends App {
  classOf[org.postgresql.Driver]
  private val conn = DriverManager.getConnection(args(0))
  private val BulletPoint = "* "

  try {
    val personOrm = new PersonOrm(conn)
    println("Kompozytorzy:")
    println()
    println(BulletPoint + (personOrm.getAll mkString s"\n$BulletPoint"))

    println()

    val albumOrm = new AlbumOrm(conn)
    println("Albumy:")
    println()
    println(BulletPoint + (albumOrm.getAll mkString s"\n$BulletPoint"))
  } finally {
    conn.close()
  }
}