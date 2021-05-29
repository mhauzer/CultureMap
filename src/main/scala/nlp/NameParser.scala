package nlp

import scala.util.parsing.combinator.RegexParsers

// https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
// TODO: Isaac "Zac" Deboni
class NameParser extends RegexParsers {
  def separator: Parser[Any] = "," | "&" | "/"

  def pka: Parser[Any] = "pka" | "PKA"

  def word: Parser[String] = not(pka) ~> """[^,&/()\s]+""".r

  def name: Parser[Name] = rep(word) ~ (opt("(" ~> pka ~> rep(word) <~ ")") | opt(pka ~> rep(word))) ^^ {
    case n1 ~ None => Name(name = n1)
    case n1 ~ Some(n2) => Name(name = n1, oldNickname = n2)
  }

  def anotherName: Parser[Name] = separator ~ name ^^ { case _ ~ w1 => w1 }

  def anotherNames: Parser[List[Name]] = rep(anotherName)

  def names: Parser[List[Name]] =
    name ~ opt(anotherNames) ^^ {
      case list1 ~ None => list1 :: Nil
      case list1 ~ Some(another) => another match {
          case list2 => list1 :: Nil ::: list2
       }
    }
}
