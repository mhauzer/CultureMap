package plist

import java.util.Date

case class Track(persistentId: String,
                 trackId: Int,
                 name: String,
                 artist: String,
                 albumArtist: String,
                 composer: String,
                 album: String,
                 grouping: String,
                 work: String,
                 movementNumber: Int,
                 movementCount: Int,
                 movementName: String,
                 genre: String,
                 kind: String,
                 size: Int,
                 totalTime: Int,
                 discNumber: Int,
                 discCount: Int,
                 trackNumber: Int,
                 trackCount: Int,
                 year: Int,
                 dateModified: Date,
                 dateAdded: Date,
                 bitRate: Int,
                 sampleRate: Int,
                 partOfGaplessAlbum: Boolean,
                 playCount: Int,
                 playDate: Long,
                 playDateUtc: Date,
                 skipCount: Int,
                 skipDate: Date,
                 releaseDate: Date,
                 compilation: Boolean,
                 loved: Boolean,
                 albumLoved: Boolean,
                 normalization: Int,
                 rating: Int,
                 albumRating: Int,
                 albumRatingComputed: Boolean,
                 artworkCount: Int,
                 sortAlbum: String,
                 sortAlbumArtist: String,
                 sortArtist: String,
                 sortComposer: String,
                 sortName: String,
                 explicit: Boolean,
                 clean: Boolean,
                 trackType: String,
                 purchased: Boolean,
                 `protected`: Boolean,
                 appleMusic: Boolean,
                 location: String,
                 fileFolderCount: Int,
                 libraryFolderCount: Int
                )

object Track {
  def builder(): TrackBuilder = new TrackBuilder
}

class TrackBuilder {
  private[this] var persistentId: String = _
  private[this] var trackId: Int = _
  private[this] var name: String = _
  private[this] var artist: String = _
  private[this] var albumArtist: String = _
  private[this] var composer: String = _
  private[this] var album: String = _
  private[this] var grouping: String = _
  private[this] var work: String = _
  private[this] var movementNumber: Int = _
  private[this] var movementCount: Int = _
  private[this] var movementName: String = _
  private[this] var genre: String = _
  private[this] var kind: String = _
  private[this] var size: Int = _
  private[this] var totalTime: Int = _
  private[this] var discNumber: Int = _
  private[this] var discCount: Int = _
  private[this] var trackNumber: Int = _
  private[this] var trackCount: Int = _
  private[this] var year: Int = _
  private[this] var dateModified: Date = _
  private[this] var dateAdded: Date = _
  private[this] var bitRate: Int = _
  private[this] var sampleRate: Int = _
  private[this] var partOfGaplessAlbum: Boolean = _
  private[this] var playCount: Int = _
  private[this] var playDate: Long = _
  private[this] var playDateUtc: Date = _
  private[this] var skipCount: Int = _
  private[this] var skipDate: Date = _
  private[this] var releaseDate: Date = _
  private[this] var compilation: Boolean = _
  private[this] var loved: Boolean = _
  private[this] var albumLoved: Boolean = _
  private[this] var normalization: Int = _
  private[this] var rating: Int = _
  private[this] var albumRating: Int = _
  private[this] var albumRatingComputed: Boolean = _
  private[this] var artworkCount: Int = _
  private[this] var sortAlbum: String = _
  private[this] var sortAlbumArtist: String = _
  private[this] var sortArtist: String = _
  private[this] var sortComposer: String = _
  private[this] var sortName: String = _
  private[this] var explicit: Boolean = _
  private[this] var clean: Boolean = _
  private[this] var trackType: String = _
  private[this] var purchased: Boolean = _
  private[this] var `protected`: Boolean = _
  private[this] var appleMusic: Boolean = _
  private[this] var location: String = _
  private[this] var fileFolderCount: Int = _
  private[this] var libraryFolderCount: Int = _

  def setPersistentId(value: String): TrackBuilder = {
    persistentId = value; this
  }

  def setTrackId(value: Int): TrackBuilder = {
    trackId = value; this
  }

  def setName(value: String): TrackBuilder = {
    name = value; this
  }

  def setArtist(value: String): TrackBuilder = {
    artist = value; this
  }

  def setAlbumArtist(value: String): TrackBuilder = {
    albumArtist = value; this
  }

  def setComposer(value: String): TrackBuilder = {
    composer = value; this
  }

  def setAlbum(value: String): TrackBuilder = {
    album = value; this
  }

  def setGrouping(value: String): TrackBuilder = {
    grouping = value; this
  }

  def setWork(value: String): TrackBuilder = {
    work = value; this
  }

  def setMovementNumber(value: Int): TrackBuilder = {
    movementNumber = value; this
  }

  def setMovementCount(value: Int): TrackBuilder = {
    movementCount = value; this
  }

  def setMovementName(value: String): TrackBuilder = {
    movementName = value; this
  }

  def setGenre(value: String): TrackBuilder = {
    genre = value; this
  }

  def setKind(value: String): TrackBuilder = {
    kind = value; this
  }

  def setSize(value: Int): TrackBuilder = {
    size = value; this
  }

  def setTotalTime(value: Int): TrackBuilder = {
    totalTime = value; this
  }

  def setDiscNumber(value: Int): TrackBuilder = {
    discNumber = value; this
  }

  def setDiscCount(value: Int): TrackBuilder = {
    discCount = value; this
  }

  def setTrackNumber(value: Int): TrackBuilder = {
    trackNumber = value; this
  }

  def setTrackCount(value: Int): TrackBuilder = {
    trackCount = value; this
  }

  def setYear(value: Int): TrackBuilder = {
    year = value; this
  }

  def setDateModified(value: Date): TrackBuilder = {
    dateModified = value; this
  }

  def setDateAdded(value: Date): TrackBuilder = {
    dateAdded = value; this
  }

  def setBitRate(value: Int): TrackBuilder = {
    bitRate = value; this
  }

  def setSampleRate(value: Int): TrackBuilder = {
    sampleRate = value; this
  }

  def setPartOfGaplessAlbum(value: Boolean): TrackBuilder = {
    partOfGaplessAlbum = value; this
  }

  def setPlayCount(value: Int): TrackBuilder = {
    playCount = value; this
  }

  def setPlayDate(value: Long): TrackBuilder = {
    playDate = value; this
  }

  def setPlayDateUtc(value: Date): TrackBuilder = {
    playDateUtc = value; this
  }

  def setSkipCount(value: Int): TrackBuilder = {
    skipCount = value; this
  }

  def setSkipDate(value: Date): TrackBuilder = {
    skipDate = value; this
  }

  def setReleaseDate(value: Date): TrackBuilder = {
    releaseDate = value; this
  }

  def setCompilation(value: Boolean): TrackBuilder = {
    compilation = value; this
  }

  def setLoved(value: Boolean): TrackBuilder = {
    loved = value; this
  }

  def setAlbumLoved(value: Boolean): TrackBuilder = {
    albumLoved = value; this
  }

  def setNormalization(value: Int): TrackBuilder = {
    normalization = value; this
  }

  def setRating(value: Int): TrackBuilder = {
    rating = value; this
  }

  def setAlbumRating(value: Int): TrackBuilder = {
    albumRating = value; this
  }

  def setAlbumRatingComputed(value: Boolean): TrackBuilder = {
    albumRatingComputed = value; this
  }

  def setArtworkCount(value: Int): TrackBuilder = {
    artworkCount = value; this
  }

  def setSortAlbum(value: String): TrackBuilder = {
    sortAlbum = value; this
  }

  def setSortAlbumArtist(value: String): TrackBuilder = {
    sortAlbumArtist = value; this
  }

  def setSortArtist(value: String): TrackBuilder = {
    sortArtist = value; this
  }

  def setSortComposer(value: String): TrackBuilder = {
    sortComposer = value; this
  }

  def setSortName(value: String): TrackBuilder = {
    sortName = value; this
  }

  def setExplicit(value: Boolean): TrackBuilder = {
    explicit = value; this
  }

  def setClean(value: Boolean): TrackBuilder = {
    clean = value; this
  }

  def setTrackType(value: String): TrackBuilder = {
    trackType = value; this
  }

  def setPurchased(value: Boolean): TrackBuilder = {
    purchased = value; this
  }

  def setProtected(value: Boolean): TrackBuilder = {
    `protected` = value; this
  }

  def setAppleMusic(value: Boolean): TrackBuilder = {
    appleMusic = value; this
  }

  def setLocation(value: String): TrackBuilder = {
    location = value; this
  }

  def setFileFolderCount(value: Int): TrackBuilder = {
    fileFolderCount = value; this
  }

  def setLibraryFolderCount(value: Int): TrackBuilder = {
    libraryFolderCount = value; this
  }

  def build(): Track = new Track(
    persistentId,
    trackId,
    name,
    artist,
    albumArtist,
    composer,
    album,
    grouping,
    work,
    movementNumber,
    movementCount,
    movementName,
    genre,
    kind,
    size,
    totalTime,
    discNumber,
    discCount,
    trackNumber,
    trackCount,
    year,
    dateModified,
    dateAdded,
    bitRate,
    sampleRate,
    partOfGaplessAlbum,
    playCount,
    playDate,
    playDateUtc,
    skipCount,
    skipDate,
    releaseDate,
    compilation,
    loved,
    albumLoved,
    normalization,
    rating,
    albumRating,
    albumRatingComputed,
    artworkCount,
    sortAlbum,
    sortAlbumArtist,
    sortArtist,
    sortComposer,
    sortName,
    explicit,
    clean,
    trackType,
    purchased,
    `protected`,
    appleMusic,
    location,
    fileFolderCount,
    libraryFolderCount
  )
}