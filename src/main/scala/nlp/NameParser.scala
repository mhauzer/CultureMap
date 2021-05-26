package nlp

import scala.util.parsing.combinator.RegexParsers

class NameParser extends RegexParsers {
  def separator: Parser[Any] = "," | "&" | "/"

  def word: Parser[String] = """[^,&/\d]+\d*""".r ^^ (w => w.trim)

  def words: Parser[List[String]] = rep(word)

  def anotherName: Parser[List[String]] = separator ~ words ^^ { case _ ~ w1 => w1 }

  def anotherNames: Parser[List[List[String]]] = rep(anotherName)

  def names: Parser[List[List[String]]] =
    words ~ opt(anotherNames) ^^ {
      case list1 ~ None => list1 :: Nil
      case list1 ~ Some(another) => another match {
          case list2 => list1 :: Nil ::: list2
       }
    }
}
