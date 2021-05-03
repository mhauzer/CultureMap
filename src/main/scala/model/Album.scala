package model

import orm.Orm

import java.sql.{Connection, ResultSet}

class Album(album: String, categories: Array[String], releaseYear: Option[Int]) {
  override def toString: String =
    s"$album${if (categories.nonEmpty) " " + (categories mkString " ") else ""}" +
      (if (releaseYear.nonEmpty) s" (${releaseYear.get})" else "")
}

object Album extends Orm[Album] {
  override val GetAll =
    """
SELECT
	COALESCE(b."name" || ' - ', '') || a.title album,
	string_agg(af.item_label, ' ') categories,
  release_year
FROM
	t_album a
	LEFT OUTER JOIN t_performing_band pb ON a.id = pb.album_id
	LEFT OUTER JOIN t_band b ON b.id = pb.band_id
	LEFT OUTER JOIN t_album_tag af ON a.id = af.album_id
GROUP BY
	a.id, b.id
ORDER BY
	release_year, b."name", a.title
"""

  override def getList(rs: ResultSet): List[Album] =
    if (!rs.next)
      List()
    else
      new Album(getPgValue(rs, "album"), getPgValueAsArray(rs, "categories"), getPgIntValue(rs, "release_year")) :: getList(rs)
}