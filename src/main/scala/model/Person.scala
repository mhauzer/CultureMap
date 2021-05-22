package model

import orm.Orm

import java.sql.ResultSet

class Person(name: String, birth: String, death: String) {
  override def toString: String = name + (if (birth != death) s" ($birth - $death)" else "")
}

object Person extends Orm[Person] {
  override val GetAll =
    """
SELECT
	   COALESCE(title || ' ', '')
  || COALESCE(firstname || ' ', '')
	|| COALESCE(firstname2 || ' ', '')
	|| COALESCE(nameprefix || ' ', '')
	|| COALESCE(lastname, '')
	|| COALESCE('-' || lastname2, '') "name",
	extract (year from birth) as birth,
  extract (year from death) as death
FROM
	t_person
ORDER BY
	birth, lastname
"""

  override def getList(rs: ResultSet): List[Person] =
    if (!rs.next)
      List()
    else
      new Person(
        getPgValue(rs, "name"),
        getPgValue(rs, "birth"),
        getPgValue(rs, "death")
      ) :: getList(rs)
}