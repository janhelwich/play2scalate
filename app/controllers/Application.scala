package controllers

import play.api.mvc._
import tools._

object Application extends Controller with HttpTools {

  def index(genre:String = "", instrument:String = "") = Action{ implicit request =>
    Ok(Render.withStds("index.scaml"))
  }

}
