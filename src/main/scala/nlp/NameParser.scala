package nlp

import scala.util.parsing.combinator.RegexParsers

// https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
// TODO: Composed by Luuk Bos pka Lifecycle
class NameParser extends RegexParsers {
  def separator: Parser[Any] = "," | "&" | "/"

  def word: Parser[String] = """[^,&/\s]+""".r

  def name: Parser[List[String]] = rep(word)

  def anotherName: Parser[List[String]] = separator ~ name ^^ { case _ ~ w1 => w1 }

  def anotherNames: Parser[List[List[String]]] = rep(anotherName)

  def names: Parser[List[List[String]]] =
    name ~ opt(anotherNames) ^^ {
      case list1 ~ None => list1 :: Nil
      case list1 ~ Some(another) => another match {
          case list2 => list1 :: Nil ::: list2
       }
    }
}
