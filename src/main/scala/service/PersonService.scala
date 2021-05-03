package service

import model.Person

class PersonService {
  def getAll: List[Person] = Person.getAll
}
