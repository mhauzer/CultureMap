import orm.Db
import plist.PListParser
import service.{AlbumService, PersonService}

import scala.language.postfixOps

object CultureMap extends App {
  private val BulletPoint = "* "
  val personService = new PersonService()
  val albumService = new AlbumService()

  if (args(0) == "-import") {
    val filename = args(1)
    println(s"Reading from $filename...")
    val musicLibrary = new PListParser(filename).getMusicLibrary
    println(musicLibrary.toString)
  } else {
    Db.setUrl(args(0))

    try {
      println(getStringAll("Kompozytorzy:", "person"))
      println()
      println(getStringAll("Albumy:", "album"))
    } finally {
      Db.connection.close()
    }
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