package westheide

/**
  * from: http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html
  */

object E8_Welcome_to_the_Future {

  def main(args: Array[String]): Unit = {

    /**
      * Why sequential code can be bad
      */

    //    Grind the required coffee beans
    //    Heat some water
    //    Brew an espresso using the ground coffee and the heated water
    //    Froth some milk
    //    Combine the espresso and the frothed milk to a cappuccino

    import scala.util.Try
    // Some type aliases, just for getting more meaningful method signatures:
    type CoffeeBeans = String
    type GroundCoffee = String
    case class Water(temperature: Int)
    type Milk = String
    type FrothedMilk = String
    type Espresso = String
    type Cappuccino = String
    // dummy implementations of the individual steps:
    def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"
    def heatWater(water: Water): Water = water.copy(temperature = 85)
    def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
    def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"
    def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"
    // some exceptions for things that might go wrong in the individual steps
    // (we'll need some of them later, use the others when experimenting
    // with the code):
    case class GrindingException(msg: String) extends Exception(msg)
    case class FrothingException(msg: String) extends Exception(msg)
    case class WaterBoilingException(msg: String) extends Exception(msg)
    case class BrewingException(msg: String) extends Exception(msg)
    // going through these steps sequentially:
    def prepareCappuccino(): Try[Cappuccino] = for {
      ground <- Try(grind("arabica beans"))
      water <- Try(heatWater(Water(25)))
      espresso <- Try(brew(ground, water))
      foam <- Try(frothMilk("milk"))
    } yield combine(espresso, foam)

    println(prepareCappuccino())
    // RESULT: Success(cappuccino)

    println("------------ NEXT CUP OF COFFEE --------------")

    /**
      * Semantics of Future
      */

    /**
      * Scala’s Future[T], residing in the scala.concurrent package, is a container type, representing a computation
      * that is supposed to eventually result in a value of type T. Alas, the computation might go wrong or time out,
      * so when the future is completed, it may not have been successful after all, in which case it contains an
      * exception instead.
      * *
      * Future is a write-once container – after a future has been completed, it is effectively immutable. Also, the Future
      * type only provides an interface for reading the value to be computed. The task of writing the computed value is
      * achieved via a Promise. Hence, there is a clear separation of concerns in the API design. In this post, we are
      * focussing on the former, postponing the use of the Promise type to the next article in this series.
      */

    /**
      * Working with Futures
      */

    import scala.concurrent.Future
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.util.Random

    def grind1(beans: CoffeeBeans): Future[GroundCoffee] = Future {
      println("start grinding...")
      Thread.sleep(Random.nextInt(2000))
      if (beans == "baked beans") throw GrindingException("are you joking?")
      println("finished grinding...")
      s"ground coffee of $beans"
    }

    def heatWater1(water: Water): Future[Water] = Future {
      println("heating the water now")
      Thread.sleep(Random.nextInt(2000))
      println("hot, it's hot!")
      water.copy(temperature = 85)
    }

    def frothMilk1(milk: Milk): Future[FrothedMilk] = Future {
      println("milk frothing system engaged!")
      Thread.sleep(Random.nextInt(2000))
      println("shutting down milk frothing system")
      s"frothed $milk"
    }

    def brew1(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
      println("happy brewing :)")
      Thread.sleep(Random.nextInt(2000))
      println("it's brewed!")
      "espresso"
    }

    def combine1(espresso: Espresso, frothedMilk: FrothedMilk): Future[Cappuccino] = Future {
      println("combining Espresso and Milk :)")
      Thread.sleep(Random.nextInt(2000))
      println("Cappuccino is ready")
      "Cappuccino, ready to consume"
    }

    //    grind1("arabica beans").onSuccess { case ground =>
    //      println("okay, got my ground coffee")
    //    }

    import scala.util.{Success, Failure}

    val grind2 = grind1("arrabica beans")
    val heat2 = heatWater1(Water(85))
    val froth2 = frothMilk1(new Milk)
    val brew2 = brew1(new GroundCoffee, Water(85))

    def startAction = {
      for {
        groundBeans <- grind2
        hotWater <- heat2
        frothMilk <- froth2
        brewedCoffee <- brew2
        espressoReady <- combine1(brewedCoffee, frothMilk)
      } yield Future(espressoReady)
    }

    startAction.onComplete {
      case Success(ready) => println(s"got my $ready")
      case Failure(failure) => println("This grinder needs a replacement, seriously!")
    }


    Thread.sleep(5000)
    // main method needs to have something to do after the completion of the async processes, otherwise it will
    // be finished BEFORE the async threads can complete their work!!

  }
}
