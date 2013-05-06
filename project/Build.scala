import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play21-scalate"
  val appVersion      = "1.0"

  val appDependencies = Seq(
    filters,
    "org.fusesource.scalate" %% "scalate-core" % "1.6.1",
    "com.github.mumoshu" %% "play2-memcached" % "0.3.0"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
//    resolvers += "google-api-services" at "http://mavenrepo.google-api-java-client.googlecode.com/hg",
//    requireJsFolder := "rjs",
//    requireJsShim += "require-config.js",
//    requireJs += "main.js",
    resolvers += "Spy Repository" at "http://files.couchbase.com/maven2", // required to resolve `spymemcached`, the plugin's dependency
    resolvers += "spring-release" at "http://repo.springsource.org/libs-release"
  )
}
