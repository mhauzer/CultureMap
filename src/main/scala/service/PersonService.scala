package service

import model.Person

class PersonService extends Service[Person] {
  def getAll: List[Person] = Person.getAll
}
