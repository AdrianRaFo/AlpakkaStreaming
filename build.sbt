name := "AlpakkaStreaming"

version := "0.1"

scalaVersion := "2.12.4"

val akkaRabbitDeps = Seq(
  "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % "0.17")

lazy val consumer =

libraryDependencies ++= akkaRabbitDeps
