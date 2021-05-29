package nlp

case class Name(name: List[String] = Nil, nickname: List[String] = Nil, oldNickname: List[String] = Nil) {
  def nonEmpty: Boolean = this != Name.Empty
}

object Name {
  def Empty: Name = Name()
}