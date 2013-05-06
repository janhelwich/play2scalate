import play.api.Play.current

package views.util{

  object requires {
    val productionFolderPrefix: String = "-min"
    val assets: String= "/asts/rjs"

    def apply(js:String) = {
      if(play.api.Play.isDev)
        assets+"/"+js
      else
        assets+productionFolderPrefix+"/"+js
    }
  }
}

package assets{
  object js {
    val jsFolder: String= "js"
    def apply(js:String) = {
      if(play.api.Play.isDev)
        controllers.routes.Assets.at(jsFolder+"/"+js).url
      else
      if(js.endsWith(".js")){
        controllers.routes.Assets.at(jsFolder+"/"+js.replaceAll("\\.js", ".min.js")).url
      } else {
        controllers.routes.Assets.at(jsFolder+"/"+js+".min").url
      }
    }
  }
}
