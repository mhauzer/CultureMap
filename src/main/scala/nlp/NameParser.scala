package nlp

import scala.util.parsing.combinator.RegexParsers

class NameParser extends RegexParsers {
  def separator: Parser[Any] = "," | "&" | "/"

  def word: Parser[String] = """[a-zA-Z0-9]+\d*""".r

  def listOfWords: Parser[List[String]] = rep(word)

  def listOfPhrases: Parser[List[List[String]]] = {
    listOfWords ~ separator ~ listOfWords ^^ { case list1 ~ _ ~ list2 => list1 :: list2 :: Nil }
  }
}
