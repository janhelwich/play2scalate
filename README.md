A Play 2.1 - Scalate application with some stuff I always find usefull. Any comments appriciated.

To be set:

- application secret

## Warning

Sometimes, you get `scala.reflect.internal...` errors, you most probably have a version mismatch between `scala-library`, `scala-compiler`, `scala-reflect`.
Run this :

```scala
$ sbt consoleProject
(fullClasspath in Runtime).eval.foreach(println)
```

You'll get the classpath list. Check for any version inconsistency and force the correct version in your `build.sbt`

Example, if your scala version 2.10.3 and your `scala-compiler` dep is `2.10.0`, add this to your `build.sbt`'s `libraryDependencies`

```scala
  "org.scala-lang" % "scala-compiler" % "2.10.3"
```
