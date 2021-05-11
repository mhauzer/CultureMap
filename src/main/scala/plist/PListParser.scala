package plist

import scala.xml.Node

class PListParser(filename: String) {
  private val musicLibrary = parse(scala.xml.XML.loadFile(filename))

  def getMusicLibrary: AppleMusicLibrary = musicLibrary

  private def getInteger(value: Node): Integer = value.text.toInt

  private def getDate(value: Node): String = value.text

  private def getString(value: Node): String = value.text

  private def getBoolean(value: Node): Boolean = value.label.toBoolean

  def getTracks(value: Node): List[Track] = Nil

  def getPlaylists(value: Node): Array[Playlist] = Array()

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
//      case "string" => DictValue(n.label, n.text)
//      case "integer" => DictValue(n.label, n.text)
//      case "date" => DictValue(n.label, n.text)
//      case "dict" => DictValue(n.label, s"DICT[${n.child.size}]")
//      case "array" => DictValue(n.label, s"ARRAY[${n.child.size}]")
//      case "true" => DictValue(n.label, "true")
//      case "false" => DictValue(n.label, "false")
//      case _ => throw new RuntimeException(s"<${n.label}> was not expected!")
    }
    builder
  }

  private def getMusicLibraryProperties(nodes: Seq[Node]): AppleMusicLibrary = {
    val appleMusicLibraryBuilder = new AppleMusicLibraryBuilder
    var key = ""
    for (n <- nodes) {
      n.label match {
        case "key" => key = n.text
        case _ => getAppleMusicLibraryField(key, n, appleMusicLibraryBuilder)
      }
    }
    appleMusicLibraryBuilder.build
  }

  private def getMusicLibraryDict(nodes: Seq[Node]): AppleMusicLibrary = {
    var plist: AppleMusicLibrary = null
    for (n <- nodes) {
      n.label match {
        case "dict" => plist = if (plist == null) getMusicLibraryProperties(n.child) else throw new RuntimeException(s"Too many <${n.label}>nodes as <${n.label}>'s children!")
        case _ => throw new RuntimeException(s"<${n.label}> was not expected as <${n.label}>'s child!")
      }
    }
    plist
  }


  private def parse(xml: Node): AppleMusicLibrary = {
      xml.label match {
        case "plist" => getMusicLibraryDict(xml.child)
        case _ => throw new RuntimeException(s"<${xml.label}> was not expected at the root level!")
      }
    }
  }
