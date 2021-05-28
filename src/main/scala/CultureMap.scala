import model.Person
import nlp.NameParser
import orm.Db
import plist.PListParser
import service.{AlbumService, PersonService}

import scala.collection.immutable.HashSet
import scala.collection.mutable
import scala.language.postfixOps
import scala.reflect.io.File

object CultureMap extends App {
  private val BulletPoint = "* "

  private def alphabeticalOrderCondition = (a: String, b: String) => a < b

  private val knownErrors = HashSet[String](
    "Ocarina Of Time (The Legend of Zelda: Ocarina Of Time)",
    "Astral Observatory (The Legend of Zelda: Majora's Mask)",
    "1AM (Animal Crossing New Leaf)",
    "Stickerbrush Symphony (Donkey Kong Country 2)",
    "Aquatic Ambience (Donkey Kong Country)",
    "muzyka tradycyjna",
    "Trad Arr",
    "Trad Arr.",
    "Trad arr",
    "Trad. Arr.",
    "Trad. arr",
    "Traditional",
    "Traditonal",
    "Traditional Song",
    "Traditional: The Irish Descendants",
    "Trad Arr David Downes",
    "Trad Arr. Arcady",
    "Trad Arr. E.Cowley",
    "Trad Arr. The High Kings",
    "Trad arr Shaw",
    "Trad. Arr. Bourke",
    "Trad. arr Pat Clancy"
  )

  val personService = new PersonService
  val albumService = new AlbumService
  val nameParser = new NameParser


  if (args(0) == "-import") {
    val filename = args(1)
    println(s"Reading from $filename...")
    val musicLibrary = new PListParser(filename).getMusicLibrary
    //    println(musicLibrary.toString)
    //    println(musicLibrary.playlists.map(_.name) mkString "\n")

    val composers = new mutable.HashMap[String, Person]
    val firstNames = new mutable.HashSet[String]
    val lastNames = new mutable.HashSet[String]
    val probablePseudonyms = new mutable.HashSet[String]
    val longNames = new mutable.HashSet[String]
    var longestNameCnt = 0
    var longestName = ""

    for (entry <- musicLibrary.tracks.map(_.composer).filter(_ != null)) {
      println(entry)
      nameParser.parse(
        nameParser.names,
        entry.replaceAll(" *N/A *", "Not Available")
      ) match {
        case nameParser.Success(r: List[List[String]], _) => for (parsedName <- r if parsedName.nonEmpty && !knownErrors.contains(parsedName mkString " ")) {
          if (longestNameCnt < parsedName.size) {
            longestNameCnt = parsedName.size; longestName = parsedName mkString " "
          }
          parsedName.size match {
            case 1 => probablePseudonyms += parsedName.head
            case 2 => firstNames += parsedName.head; lastNames += parsedName.tail.head
            case _ => longNames += parsedName mkString " "
          }
          val fullName = parsedName mkString " "
          if (!composers.keys.exists(_ == fullName))
            composers(fullName) = new Person(name = fullName, "", "")
          println(s"* [$fullName]")
        }
        case nameParser.Failure(msg, n) => println(msg)
        case nameParser.Error(msg, n) => println(msg)
      }
    }

    File("firstnames.txt").writeAll(firstNames.toList.sortWith(alphabeticalOrderCondition) mkString "\n")
    File("lastnames.txt").writeAll(lastNames.toList.sortWith(alphabeticalOrderCondition) mkString "\n")
    File("pseudonyms.txt").writeAll(probablePseudonyms.toList.sortWith(alphabeticalOrderCondition) mkString "\n")
    File("longnames.txt").writeAll(longNames.toList.sortWith(alphabeticalOrderCondition) mkString "\n")

    println(composers.keys.toList.sortWith((a, b) => a < b) mkString "\n")
    println("Liczba kompozytorów: " + composers.size)
    println("Najdłuższe imię i nazwisko: " + longestName + s" ($longestNameCnt)")
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