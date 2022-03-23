ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "2.5.0"


scalacOptions ++= Seq(
  "-language:reflectiveCalls",
  "-deprecation",
  "-feature",
  "-Xcheckinit",
)


libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

fork := true

Compile / scalaSource := baseDirectory.value / "src"
Test / scalaSource := baseDirectory.value / "test"
Test / resourceDirectory := baseDirectory.value / "test" / "resources"
