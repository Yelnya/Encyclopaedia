package westheide

/**
  * http://danielwestheide.com/blog/2012/12/05/the-neophytes-guide-to-scala-part-3-patterns-everywhere.html
  */

object E3_Patterns_Everywhere {

  def main(args: Array[String]): Unit = {

    /**
      * Pattern matching expressions
      */

    case class Player(name: String, score: Int)

    def printMessage(player: Player) = player match {
      case Player(_, score) if score > 100000 => println("Get a job, dude!")
      case Player(name, _) => println("Hey " + name + ", nice to see you again!")
    }

    val player = Player("Carlos", 999999)
    printMessage(player)
    // RESULT: Get a job, dude!
    val player2 = Player("Carlos", 12)
    printMessage(player2)
    // RESULT: Hey Carlos, nice to see you again!


    def message2(player: Player) = player match {
      case Player(_, score) if score > 100000 => "Get a job, dude!"
      case Player(name, _) => "Hey " + name + ", nice to see you again!"
    }
    def printMessage2(player: Player) = println(message2(player))

    printMessage2(player)
    // RESULT: Get a job, dude!
    printMessage2(player2)
    // RESULT: Hey Carlos, nice to see you again!

    /**
      * Patterns in value definitions
      */

    //    def currentPlayer(): Player = Player("Daniel", 3500)

    //    val player = currentPlayer()
    //    doSomethingWithTheName(player.name)

    //    val Player(name, _) = currentPlayer()
    //    doSomethingWithTheName(name)

    //    def scores: List[Int] = List()
    //    val best :: rest = scores
    //    println("The score of our champion is " + best)

    //    def gameResult(): (String, Int) = ("Daniel", 3500)

    //    val result = gameResult()
    //    println(result._1 + ": " + result._2)

    //    val (name, score) = gameResult()
    //    println(name + ": " + score)

    /**
      * Patterns in for comprehensions
      */

    def gameResults(): Seq[(String, Int)] =
    ("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil

    def hallOfFame = for {
      result <- gameResults()
      (name, score) = result
      if (score > 5000)
    } yield name

    println(hallOfFame)
    // RESULT: List(Melissa, John)

    def hallOfFame2 = for {
      (name, score) <- gameResults()
      if (score > 5000)
    } yield name

    println(hallOfFame2)
    // RESULT: List(Melissa, John)

    val lists = List(1, 2, 3) :: List.empty :: List(5, 3) :: Nil

    def listSize = {
      for {
        list @ head :: _ <- lists
      } yield list.size

    }

    println(listSize)
    // RESULT: List(3, 2)



  }
}
