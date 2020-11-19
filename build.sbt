scalaVersion := "2.13.3"

javaOptions += "-Duser.timezone=UTC"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

val specs2Version = "4.10.5"
val circeVersion = "0.13.0"

libraryDependencies ++= Seq(
  "org.scala-lang.modules"        %% "scala-parser-combinators"       % "1.1.2",
  "io.circe"                      %% "circe-parser"                   % circeVersion,
  "org.specs2"                    %% "specs2-core"                    % specs2Version          % Test,
  "org.specs2"                    %% "specs2-matcher-extra"           % specs2Version          % Test,
  "org.specs2"                    %% "specs2-scalacheck"              % specs2Version          % Test,
)

scalacOptions ++= Seq(
  "-Xlint:-byname-implicit", // As of Scala 2.13.3 `deriveEncoder[T]` breaks
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
