import model.Person
import orm.Db
import plist.PListParser
import service.{AlbumService, PersonService}

import scala.collection.mutable
import scala.language.postfixOps

object CultureMap extends App {
  private val BulletPoint = "* "
  val personService = new PersonService()
  val albumService = new AlbumService()

  if (args(0) == "-import") {
    val filename = args(1)
    println(s"Reading from $filename...")
    val musicLibrary = new PListParser(filename).getMusicLibrary
    //    println(musicLibrary.toString)
    //    println(musicLibrary.playlists.map(_.name) mkString "\n")

    val composers = new mutable.HashMap[String, Person]

    for (entry <- musicLibrary.tracks.map(_.composer).filter(_ != null)) {
      println(entry)
      for (rawComposer <- entry
        .replaceAll(" *N/A *", "Not Available")
        .replace("&", ",")
        .replace("/", ",")
        .split(",")) {
        val composer: String = rawComposer.trim()
        if (!composers.keys.exists(_ == composer)) composers(composer) = new Person(name = composer, "", "")
        println("* " + composer)
      }
    }

    println(composers.keys.toList.sortWith((a, b) => a < b) mkString "\n")

    println("Liczba kompozytorów: " + composers.size)
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