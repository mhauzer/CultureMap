import orm.Db
import service.{AlbumService, PersonService, Service}

import scala.language.postfixOps

object CultureMap extends App {
  private val BulletPoint = "* "

  Db.setUrl(args(0))

  val personService = new PersonService()
  val albumService = new AlbumService()

  try {
    println(getStringAll("Kompozytorzy:", "person"))
    println()
    println(getStringAll("Albumy:", "album"))
  } finally {
    Db.connection.close()
  }

  private def getStringAll(header: String, table: String) = {
    header + "\n" +
      "\n" +
      BulletPoint + (
        (table match {
            case "person" => personService.getAll
            case "album" => albumService.getAll
        }) mkString s"\n$BulletPoint"
      )
  }
}