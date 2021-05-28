import org.scalatest.funsuite.AnyFunSuite
import nlp.NameParser

class NameParserSuite extends AnyFunSuite {
  val parser: NameParser = new NameParser

  test("Richard is a word") {
    assert(parser.parse(parser.word, "Richard").successful)
  }

  test(", is not a word") {
    assertResult(false)(parser.parse(parser.word, ",").successful)
  }

  test("24 is a word") {
    assertResult("24")(parser.parse(parser.word, "24").getOrElse(""))
  }

  test(", is a separator") {
    assert(parser.parse(parser.separator, ",").successful)
  }



  test("Richard is a list of words") {
    assert(parser.parse(parser.name, "Richard").successful)
  }

  test("Richard Wagner, Richard Strauss is a list of phrases") {
    assert(parser.parse(parser.names, "Richard Wagner, Richard Strauss").successful)
  }

  test("Richard Wagner, Richard Strauss & Arnold Schoenberg is a list of phrases") {
    assert(parser.parse(parser.names, "Richard Wagner, Richard Strauss & Arnold Schoenberg").successful)
  }

  test("Teddy, 24, R. Tee & Bekuh Boom is a list of names") {
    assert(parser.parse(parser.names, "Teddy, 24, R. Tee & Bekuh Boom").successful)
  }
}
