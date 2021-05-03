import orm.Db
import service.{AlbumService, PersonService}

import scala.language.postfixOps

object CultureMap extends App {
  private val BulletPoint = "* "

  Db.setUrl(args(0))

  try {
    val personService = new PersonService()
    println("Kompozytorzy:")
    println()
    println(BulletPoint + (personService.getAll mkString s"\n$BulletPoint"))

    println()

    val albumService = new AlbumService()
    println("Albumy:")
    println()
    println(BulletPoint + (albumService.getAll mkString s"\n$BulletPoint"))
  } finally {
    Db.connection.close()
  }
}