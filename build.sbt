name := "AlpakkaStreaming"

version := "0.1"

scalaVersion := "2.12.4"

val alpakkaDeps = Seq(
  "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % "0.17",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.11")

lazy val consumer = project in file("consumer") settings (
  libraryDependencies ++= alpakkaDeps
)

lazy val producer = project in file("producer") settings (
  libraryDependencies ++= alpakkaDeps
)
