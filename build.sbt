scalaVersion := "2.13.3"

javaOptions += "-Duser.timezone=UTC"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

val specs2Version = "4.10.5"
val circeVersion = "0.13.0"

libraryDependencies ++= Seq(
  "org.specs2"                    %% "specs2-core"                    % specs2Version          % Test,
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

scalacOptions in Test ++= Seq("-Yrangepos")

scalacOptions in Test --= Seq(
  "-Wnumeric-widen",
  "-Wvalue-discard"
)

scalacOptions in (Compile, console) --= Seq(
  "-Werror",
  "-Wunused",
  "-Xlint"
)

scalaSource in Test := baseDirectory.value / "src" / "test"

fork := true

testFrameworks := Seq(sbt.TestFrameworks.Specs2)

assemblyOutputPath in assembly := new File("target/app.jar")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _ @_*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

test in assembly := {}
