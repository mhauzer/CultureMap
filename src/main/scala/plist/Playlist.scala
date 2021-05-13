package plist

case class Playlist(
                   playlistPersistentId: String,
                   playlistId: Int,
                   name: String,
                   description: String,
                   master: Boolean,
                   visible: Boolean,
                   distinguishedKind: Int,
                   music: Boolean,
                   movies: Boolean,
                   tvShows: Boolean,
                   podcasts: Boolean,
                   audiobooks: Boolean,
                   smartInfo: String,
                   smartCriteria: String,
                   allItems: Boolean,
                   playlistItems: Array[Int]
                   )

object Playlist {
  def builder(): PlaylistBuilder = new PlaylistBuilder
}

class PlaylistBuilder {
  private[this] var playlistPersistentId: String = _
  private[this] var playlistId: Int = _
  private[this] var name: String = _
  private[this] var description: String = _
  private[this] var master: Boolean = _
  private[this] var visible: Boolean = _
  private[this] var distinguishedKind: Int = _
  private[this] var music: Boolean = _
  private[this] var movies: Boolean = _
  private[this] var tvShows: Boolean = _
  private[this] var podcasts: Boolean = _
  private[this] var audiobooks: Boolean = _
  private[this] var smartInfo: String = _
  private[this] var smartCriteria: String = _
  private[this] var allItems: Boolean = _
  private[this] var playlistItems: Array[Int] = _

  def setPlaylistPersistentId(value: String): PlaylistBuilder = {
    playlistPersistentId = value
    this
  }

  def setPlaylistId(value: Int): PlaylistBuilder = {
    playlistId = value
    this
  }

  def setName(value: String): PlaylistBuilder = {
    name = value
    this
  }

  def setDescription(value: String): PlaylistBuilder = {
    description = value
    this
  }

  def setMaster(value: Boolean): PlaylistBuilder = {
    master = value
    this
  }

  def setVisible(value: Boolean): PlaylistBuilder = {
    visible = value
    this
  }

  def setDistinguishedKind(value: Int): PlaylistBuilder = {
    distinguishedKind = value
    this
  }

  def setMusic(value: Boolean): PlaylistBuilder = {
    music = value
    this
  }

  def setMovies(value: Boolean): PlaylistBuilder = {
    movies = value
    this
  }

  def setTvShows(value: Boolean): PlaylistBuilder = {
    tvShows = value
    this
  }

  def setPodcasts(value: Boolean): PlaylistBuilder = {
    podcasts = value
    this
  }

  def setAudiobooks(value: Boolean): PlaylistBuilder = {
    audiobooks = value
    this
  }

  def setSmartInfo(value: String): PlaylistBuilder = {
    smartInfo = value
    this
  }

  def setSmartCriteria(value: String): PlaylistBuilder = {
    smartCriteria = value
    this
  }

  def setAllItems(value: Boolean): PlaylistBuilder = {
    allItems = value
    this
  }

  def setPlaylistItems(value: Array[Int]): PlaylistBuilder = {
    playlistItems = value
    this
  }

  def build(): Playlist = Playlist(
    playlistPersistentId,
    playlistId,
    name,
    description,
    master,
    visible,
    distinguishedKind,
    music,
    movies,
    tvShows,
    podcasts,
    audiobooks,
    smartInfo,
    smartCriteria,
    allItems,
    playlistItems
  )
}
