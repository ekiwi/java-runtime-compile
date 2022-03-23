ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "2.5.0"
ThisBuild / crossScalaVersions := Seq("2.12.15", "2.13.7")

scalacOptions ++= Seq(
  "-language:reflectiveCalls",
  "-deprecation",
  "-feature",
  "-Xcheckinit",
)


libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.8.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"


Compile / scalaSource := baseDirectory.value / "src"
Test / scalaSource := baseDirectory.value / "test"
Test / resourceDirectory := baseDirectory.value / "test" / "resources"
