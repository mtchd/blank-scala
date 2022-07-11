scalaVersion := "2.13.8"

javaOptions += "-Duser.timezone=UTC"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

val specs2Version = "4.12.12"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % specs2Version % Test
)

scalacOptions ++= Seq(
  "-Xlint:-byname-implicit",
  "-Wdead-code",
  "-Werror",
  "-Wextra-implicit",
  "-Wnumeric-widen",
  "-Woctal-literal",
  "-Wunused",
  "-Wvalue-discard",
  "-Xlint",
  "-deprecation",
  "-feature",
  "-language:higherKinds",
  "-unchecked"
)

Test / scalacOptions ++= Seq("-Yrangepos")

Test / scalaSource := baseDirectory.value / "src" / "test"

fork := true

testFrameworks := Seq(sbt.TestFrameworks.Specs2)

assembly / assemblyOutputPath := new File("target/app.jar")

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", _ @_*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

assembly / test := {}
