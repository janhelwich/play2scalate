package controllers.tools

import play.api._
import http.{Writeable, ContentTypeOf, ContentTypes}
import mvc.{AnyContent, Request, Codec}
import play.api.Play.current
import org.fusesource.scalate._
import layout.DefaultLayoutStrategy
import util.FileResourceLoader

object Render {


  var format = Play.configuration.getString("scalate.format") match {
    case Some(configuredFormat) => configuredFormat
    case _ => "scaml"
  }

  lazy val scalateEngine = {
    val engine = new TemplateEngine
    engine.resourceLoader = new FileResourceLoader(Some(Play.getFile("app/views")))
    engine.layoutStrategy = new DefaultLayoutStrategy(engine, "app/views/layouts/default." + format)
    engine.classpath = "tmp/classes"
    engine.workingDirectory = Play.getFile("tmp")
    engine.combinedClassPath = true
    engine.classLoader = Play.classloader
    engine.importStatements = List(
      "import controllers.routes._",
      "import models._",
      "import play.api._"
    )
//    engine.allowReload =  false
//    engine.allowCaching =  true
    engine
  }

  def apply(template: String, args: (Symbol, Any)*) = Template(template).render(
    args.map {
      case (k, v) => k.name -> v
    } toMap
  )(Map())

  def apply(template: String, attributes: scala.Predef.Map[scala.Predef.String, scala.Any]) = Template(template).render(attributes)(Map())

  def withStds(template: String, args: (Symbol, Any)*)(implicit request:Request[AnyContent]) = Template(template).render(
    args.map {
      case (k, v) => k.name -> v
    } toMap
  )(StdPageAttributes())

  def withStds(template: String, attributes: scala.Predef.Map[scala.Predef.String, scala.Any])(implicit request:Request[AnyContent]) =
    Template(template).render(attributes)(StdPageAttributes())

  case class Template(name: String) {

    def render(attributes: scala.Predef.Map[scala.Predef.String, scala.Any])(stdAttributes: scala.Predef.Map[scala.Predef.String, scala.Any]) = {
      ScalateContent {
        scalateEngine.layout(name, attributes ++ stdAttributes)
      }
    }

  }

  case class ScalateContent(val cont: String)

  implicit def writeableOf_ScalateContent(implicit codec: Codec): Writeable[ScalateContent] = {
    Writeable[ScalateContent]((scalaCont:ScalateContent) => scalaCont.cont.getBytes(codec.charset))
  }

  implicit def contentTypeOf_ScalateContent(implicit codec: Codec): ContentTypeOf[ScalateContent] = {
    ContentTypeOf[ScalateContent](Some(ContentTypes.HTML))
  }
}