import play.api._
import play.api.Play.current
import mvc.{Handler, RequestHeader}

object Global extends GlobalSettings {
  lazy val dev = Play.isDev

  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    if(dev){
      println("request:" + request.toString)
    }
    super.onRouteRequest(request)
  }
}