package plist

import java.util.Date

/*
  https://www.apple.com/DTDs/PropertyList-1.0.dtd

  <!ENTITY % plistObject "(array | data | date | dict | real | integer | string | true | false )" >
  <!ELEMENT plist %plistObject;>
  <!ATTLIST plist version CDATA "1.0" >

  <!-- Collections -->
  <!ELEMENT array (%plistObject;)*>
  <!ELEMENT dict (key, %plistObject;)*>
  <!ELEMENT key (#PCDATA)>

  <!--- Primitive types -->
  <!ELEMENT string (#PCDATA)>
  <!ELEMENT data (#PCDATA)> <!-- Contents interpreted as Base-64 encoded -->
  <!ELEMENT date (#PCDATA)> <!-- Contents should conform to a subset of ISO 8601 (in particular, YYYY '-' MM '-' DD 'T' HH ':' MM ':' SS 'Z'.  Smaller units may be omitted with a loss of precision) -->

  <!-- Numerical primitives -->
  <!ELEMENT true EMPTY>  <!-- Boolean constant true -->
  <!ELEMENT false EMPTY> <!-- Boolean constant false -->
  <!ELEMENT real (#PCDATA)> <!-- Contents should represent a floating point number matching ("+" | "-")? d+ ("."d*)? ("E" ("+" | "-") d+)? where d is a digit 0-9.  -->
  <!ELEMENT integer (#PCDATA)> <!-- Contents should represent a (possibly signed) integer number in base 10 -->
 */
case class AppleMusicLibrary(libraryPersistentId: String,
                             playlists: Array[Playlist],
                             musicFolder: String,
                             applicationVersion: String,
                             date: Date,
                             minorVersion: Int,
                             features: Int,
                             majorVersion: Int,
                             tracks: List[Track]) {

  override def toString: String = s"libraryPersistentId=$libraryPersistentId\n" +
    s"date=$date\n" +
    s"applicationVersion=$applicationVersion\n" +
    s"majorVersion=$majorVersion\n" +
    s"minorVersion=$minorVersion\n" +
    s"musicFolder=$musicFolder\n" +
    s"tracks=${tracks.size}\n" +
    s"playlists=${playlists.length}"
}

object AppleMusicLibrary {
  def builder(): AppleMusicLibraryBuilder = new AppleMusicLibraryBuilder
}

class AppleMusicLibraryBuilder() {
  def setTracks(value: List[Track]): AppleMusicLibraryBuilder = {
    tracks = value
    this
  }

  def setLibraryPersistentId(value: String): AppleMusicLibraryBuilder = {
    libraryPersistentId = value
    this
  }

  def setShowContentRatings(value: Boolean): AppleMusicLibraryBuilder = {
    showContentRatings = value
    this
  }

  def setFeatures(value: Integer): AppleMusicLibraryBuilder = {
    features = value
    this
  }

  def setApplicationVersion(value: String) : AppleMusicLibraryBuilder= {
    applicationVersion = value
    this
  }

  def setDate(value: Date) : AppleMusicLibraryBuilder= {
    date = value
    this
  }

  private var playlists: Array[Playlist] = Array()
  private var musicFolder: String = ""
  private var applicationVersion: String = ""
  private var date: Date = _
  private var libraryPersistentId: String = ""
  private var minorVersion: Int = 0
  private var features: Int = 0
  private var majorVersion: Int = 0
  private var showContentRatings: Boolean = false
  private var tracks: List[Track] = Nil

  def setPlaylists(playlists: Array[Playlist]): AppleMusicLibraryBuilder = {
    this.playlists = playlists
    this
  }

  def setMusicFolder(musicFolder: String): AppleMusicLibraryBuilder = {
    this.musicFolder = musicFolder
    this
  }

  def setMajorVersion(value: Integer): AppleMusicLibraryBuilder = {
    majorVersion = value
    this
  }

  def setMinorVersion(value: Integer): AppleMusicLibraryBuilder = {
    minorVersion = value
    this
  }

  def build(): AppleMusicLibrary =
    AppleMusicLibrary(libraryPersistentId, playlists, musicFolder, applicationVersion, date, minorVersion, features, majorVersion, tracks)
}
