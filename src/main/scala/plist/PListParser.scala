package plist

import common.StringUtils.convertStringToDate

import java.util.Date
import scala.xml.{Elem, Node, SAXParser}
import scala.xml.factory.XMLLoader

class PListParser(filename: String) {
  private val musicLibrary = parse(MyXML.loadFile(filename))

  def getMusicLibrary: AppleMusicLibrary = musicLibrary

  private def getInteger(value: Node): Integer = value.text.toInt

  private def getLong(value: Node): Long = value.text.toLong

  private def getDate(value: Node): Date = convertStringToDate(value.text)

  private def getString(value: Node): String = value.text

  private def getBoolean(value: Node): Boolean = value.label.toBoolean

  private def getStringData(value: Node): String = value.text

  def getTrackField(key: String, value: Node, builder: TrackBuilder): TrackBuilder =
    key match {
      case "Track ID" => builder.setTrackId(getInteger(value))
      case "Name" => builder.setName(getString(value))
      case "Artist" => builder.setArtist(getString(value))
      case "Album Artist" => builder.setAlbumArtist(getString(value))
      case "Composer" => builder.setComposer(getString(value))
      case "Album" => builder.setAlbum(getString(value))
      case "Grouping" => builder.setGrouping(getString(value))
      case "Work" => builder.setWork(getString(value))
      case "Movement Number" => builder.setMovementNumber(getInteger(value))
      case "Movement Count" => builder.setMovementCount(getInteger(value))
      case "Movement Name" => builder.setMovementName(getString(value))
      case "Genre" => builder.setGenre(getString(value))
      case "Kind" => builder.setKind(getString(value))
      case "Size" => builder.setSize(getInteger(value))
      case "Kind" => builder.setKind(getString(value))
      case "Total Time" => builder.setTotalTime(getInteger(value))
      case "Disc Number" => builder.setDiscNumber(getInteger(value))
      case "Disc Count" => builder.setDiscCount(getInteger(value))
      case "Track Number" => builder.setTrackNumber(getInteger(value))
      case "Track Count" => builder.setTrackCount(getInteger(value))
      case "Year" => builder.setYear(getInteger(value))
      case "Date Modified" => builder.setDateModified(getDate(value))
      case "Date Added" => builder.setDateAdded(getDate(value))
      case "Bit Rate" => builder.setBitRate(getInteger(value))
      case "Sample Rate" => builder.setSampleRate(getInteger(value))
      case "Part Of Gapless Album" => builder.setPartOfGaplessAlbum(getBoolean(value))
      case "Play Count" => builder.setPlayCount(getInteger(value))
      case "Play Date" => builder.setPlayDate(getLong(value))
      case "Play Date UTC" => builder.setPlayDateUtc(getDate(value))
      case "Skip Count" => builder.setSkipCount(getInteger(value))
      case "Skip Date" => builder.setSkipDate(getDate(value))
      case "Release Date" => builder.setReleaseDate(getDate(value))
      case "Compilation" => builder.setCompilation(getBoolean(value))
      case "Loved" => builder.setLoved(getBoolean(value))
      case "Album Loved" => builder.setAlbumLoved(getBoolean(value))
      case "Normalization" => builder.setNormalization(getInteger(value))
      case "Rating" => builder.setRating(getInteger(value))
      case "Album Rating" => builder.setAlbumRating(getInteger(value))
      case "Album Rating Computed" => builder.setAlbumRatingComputed(getBoolean(value))
      case "Artwork Count" => builder.setArtworkCount(getInteger(value))
      case "Sort Album" => builder.setSortAlbum(getString(value))
      case "Sort Album Artist" => builder.setSortAlbumArtist(getString(value))
      case "Sort Artist" => builder.setSortArtist(getString(value))
      case "Sort Composer" => builder.setSortComposer(getString(value))
      case "Sort Name" => builder.setSortName(getString(value))
      case "Persistent ID" => builder.setPersistentId(getString(value))
      case "Explicit" => builder.setExplicit(getBoolean(value))
      case "Clean" => builder.setClean(getBoolean(value))
      case "Track Type" => builder.setTrackType(getString(value))
      case "Purchased" => builder.setPurchased(getBoolean(value))
      case "Protected" => builder.setProtected(getBoolean(value))
      case "Apple Music" => builder.setAppleMusic(getBoolean(value))
      case "Location" => builder.setLocation(getString(value))
      case "File Folder Count" => builder.setFileFolderCount(getInteger(value))
      case "Library Folder Count" => builder.setLibraryFolderCount(getInteger(value))
      case _ => throw new RuntimeException(s"$key was not expected!")
    }

  def getTrackDict(value: Node): Track = {
    val trackBuilder = Track.builder()

    var key = ""
    for (n <- value.child) {
      n.label match {
        case "key" => key = n.text
        case _ => getTrackField(key, n, trackBuilder)
      }
    }

    trackBuilder.build()
  }

  def getTracks(value: Node): List[Track] = {
    var tracks: List[Track] = Nil

    var key: Integer = 0
    for (n <- value.child) {
      n.label match {
        case "key" => key = getInteger(n)
        case "dict" => tracks = getTrackDict(n) :: tracks
        case _ => throw new RuntimeException(s"<${n.label}> was not expected as <${value.label}>'s child!")
      }
    }

    tracks.reverse
  }

  private def getPlaylistItems(node: Node): Array[Int] = {
    var items: List[Int] = Nil
    node.label match {
      case "array" =>
      case "dict" =>
      case "key" =>
      case "integer" => items = getInteger(node) :: items
      case _ => throw new RuntimeException(s"${node.label} was not expected!")
    }
    items.toArray
  }

  private def getPlaylistField(key: String, value: Node, builder: PlaylistBuilder): PlaylistBuilder =
    key match {
      case "Playlist Persistent ID" => builder.setPlaylistPersistentId(getString(value))
      case "Playlist ID" => builder.setPlaylistId(getInteger(value))
      case "Name" => builder.setName(getString(value))
      case "Description" => builder.setDescription(getString(value))
      case "Master" => builder.setMaster(getBoolean(value))
      case "Visible" => builder.setVisible(getBoolean(value))
      case "Distinguished Kind" => builder.setDistinguishedKind(getInteger(value))
      case "Music" => builder.setMusic(getBoolean(value))
      case "Movies" => builder.setMovies(getBoolean(value))
      case "TV Shows" => builder.setTvShows(getBoolean(value))
      case "Podcasts" => builder.setPodcasts(getBoolean(value))
      case "Audiobooks" => builder.setAudiobooks(getBoolean(value))
      case "Smart Info" => builder.setSmartInfo(getStringData(value))
      case "Smart Criteria" => builder.setSmartCriteria(getStringData(value))
      case "All Items" => builder.setAllItems(getBoolean(value))
      case "Playlist Items" => builder.setPlaylistItems(getPlaylistItems(value))
      case _ => throw new RuntimeException(s"$key was not expected!")
    }


  def getPlaylistDict(value: Node): Playlist = {
    val playlistBuilder = Playlist.builder()

    var key = ""
    for (n <- value.child) {
      n.label match {
        case "key" => key = n.text
        case _ => getPlaylistField(key, n, playlistBuilder)
      }
    }

    playlistBuilder.build()
  }

  private def getPlaylists(value: Node): Array[Playlist] = {
    var playlists: List[Playlist] = Nil

    for (n <- value.child) {
      n.label match {
        case "dict" => playlists = getPlaylistDict(n) :: playlists
        case _ => throw new RuntimeException(s"<${n.label}> was not expected as <${value.label}>'s child!")
      }
    }

    playlists.reverse.toArray
  }

  private def getAppleMusicLibraryField(field: String, value: Node, builder: AppleMusicLibraryBuilder): AppleMusicLibraryBuilder = {
    field match {
      case "Major Version" => builder.setMajorVersion(getInteger(value))
      case "Minor Version" => builder.setMinorVersion(getInteger(value))
      case "Date" => builder.setDate(getDate(value))
      case "Application Version" => builder.setApplicationVersion(getString(value))
      case "Features" => builder.setFeatures(getInteger(value))
      case "Show Content Ratings" => builder.setShowContentRatings(getBoolean(value))
      case "Music Folder" => builder.setMusicFolder(getString(value))
      case "Library Persistent ID" => builder.setLibraryPersistentId(getString(value))
      case "Tracks" => builder.setTracks(getTracks(value))
      case "Playlists" => builder.setPlaylists(getPlaylists(value))
      case _ => throw new RuntimeException(s"$field was not expected!")
    }
    builder
  }

  private def getMusicLibraryProperties(nodes: Seq[Node]): AppleMusicLibrary = {
    val appleMusicLibraryBuilder = AppleMusicLibrary.builder()
    var key = ""
    for (n <- nodes) {
      n.label match {
        case "key" => key = n.text
        case _ => getAppleMusicLibraryField(key, n, appleMusicLibraryBuilder)
      }
    }
    appleMusicLibraryBuilder.build()
  }

  private def getMusicLibraryDict(node: Node): AppleMusicLibrary = {
    var plist: AppleMusicLibrary = null
    for (n <- node.child) {
      n.label match {
        case "dict" => plist = if (plist == null) getMusicLibraryProperties(n.child) else throw new RuntimeException(s"Too many <${n.label}>nodes as <${n.label}>'s children!")
        case _ => throw new RuntimeException(s"<${n.label}> was not expected as <${node.label}>'s child!")
      }
    }
    plist
  }


  private def parse(xml: Node): AppleMusicLibrary = {
    xml.label match {
      case "plist" => getMusicLibraryDict(xml)
      case _ => throw new RuntimeException(s"<${xml.label}> was not expected at the root level!")
    }
  }
}

object MyXML extends XMLLoader[Elem] {
  override def parser: SAXParser = {
    val f = javax.xml.parsers.SAXParserFactory.newInstance()
    f.setNamespaceAware(false)
    f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    f.newSAXParser()
  }
}