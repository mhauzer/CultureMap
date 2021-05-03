package service

abstract class Service[T] {
  def getAll: List[T]
}
