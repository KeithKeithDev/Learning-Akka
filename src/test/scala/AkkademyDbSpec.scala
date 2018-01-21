import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akkademy.messages.SetRequest
import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers {

  // An actor system is a place where actors and their addresses reside
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("AkkademyDB Actor") {
    describe("given SetRequest message") {
      it("should store key/value in map") {
        /**
          * TestActorRef has synchronous API that allows us access to the underlying actor.
          * Here we create the actor in our actor system.
          */
        val actorRef = TestActorRef(new AkkademyDb)
        /**
          * Here we place a message in the actor's mailbox. This is a SYNCHRONOUS
          * call. In normal operations, this would be asynchronous and would return
          * immediately but for the test, it is synchronous.
          */
        actorRef ! SetRequest("foo", "bar")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map("foo") should equal("bar")
      }
    }
  }

}
