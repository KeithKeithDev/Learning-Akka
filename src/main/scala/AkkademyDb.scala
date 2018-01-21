import akka.actor.Actor
import akka.event.Logging
import com.akkademy.messages.SetRequest

import scala.collection.mutable

class AkkademyDb extends Actor {

  val map = new mutable.HashMap[String, String]()
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(k, v) =>
      log.info("received SetRequest - key: {} value: {}", k, v)
      map.put(k, v)
    case unknownMsg =>
      log.info("received unknown message: {}", unknownMsg)
  }

}