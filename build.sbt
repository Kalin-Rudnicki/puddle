
lazy val root = project
  .in(file("."))
  .settings(
    name := "puddle-dotty",
    version := "0.1.0",

    scalaVersion := "0.23.0-RC1",

    libraryDependencies ++= List(
      // "org.scalaz" %% "scalaz-core" % "7.3.0",
    )
  )
