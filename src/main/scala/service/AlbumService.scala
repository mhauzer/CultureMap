package service

import model.Album

class AlbumService extends Service[Album] {
  def getAll: List[Album] = Album.getAll
}
