package service

import model.Album

class AlbumService {
  def getAll: List[Album] = Album.getAll
}
