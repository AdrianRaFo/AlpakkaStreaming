/**
 * https://github.com/AdrianRaFo
 */
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.amqp._
import akka.stream.alpakka.amqp.scaladsl.AmqpSource
import akka.stream.scaladsl.Sink

object Consumer extends App {
  implicit val system       : ActorSystem       = ActorSystem()
  implicit val materializer : ActorMaterializer = ActorMaterializer()

  val connectionProvider = AmqpDetailsConnectionProvider("localhost", 5672)

  private val queueName = "akkaMQ"

  val queueDeclaration = QueueDeclaration(queueName)

  val amqpSource = AmqpSource.atMostOnceSource(
    NamedQueueSourceSettings(AmqpLocalConnectionProvider, queueName)
      .withDeclarations(queueDeclaration),
    bufferSize = 10
  ).runWith(Sink.foreach(btStr  => println(btStr.bytes.utf8String)))

}
