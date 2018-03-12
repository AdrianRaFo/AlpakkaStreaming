/**
 * https://github.com/AdrianRaFo
 */
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.amqp._
import akka.stream.alpakka.amqp.scaladsl.{AmqpRpcFlow, AmqpSink}
import akka.stream.scaladsl.{Keep, Sink, Source}
import akka.util.ByteString

object Producer extends App {

  implicit val system       : ActorSystem       = ActorSystem()
  implicit val materializer : ActorMaterializer = ActorMaterializer()

  import system.dispatcher

  val connectionProvider = AmqpDetailsConnectionProvider("localhost", 5672)

  val queueName        = "akkaMQ"
  val queueDeclaration = QueueDeclaration(queueName)

  val amqpRpcFlow = AmqpSink.simple(
    AmqpSinkSettings(connectionProvider)
      .withRoutingKey(queueName)
      .withDeclarations(queueDeclaration)
  )

  val message = "Hello World!"

  val source = Source
    .single(message)
    .map(s => ByteString(s))
    .toMat(amqpRpcFlow)(Keep.right)
    .run
  
  println(s" [x] Sent '$message'")
  source.onComplete(_ =>system.terminate())

}
