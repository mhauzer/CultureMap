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

  test(", is a separator") {
    assert(parser.parse(parser.separator, ",").successful)
  }

  test("Richard is a list of words") {
    assert(parser.parse(parser.words, "Richard").successful)
  }

  test("Richard Wagner, Richard Strauss is a list of phrases") {
    assert(parser.parse(parser.names, "Richard Wagner, Richard Strauss").successful)
  }
}
