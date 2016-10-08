package lookup.partial_functions

/**
  * from: https://www.scala-exercises.org/std_lib/partial_functions
  */
object PartialFunctions {

  def main(args: Array[String]): Unit = {

    /**
      * A partial function is a trait that when implemented can be used as building blocks to determine a
      * solution. The trait PartialFunction requires that the method isDefinedAt and apply be implemented
      */

    val doubleEvens: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
      //States that this partial function will take on the task
      def isDefinedAt(x: Int) = x % 2 == 0

      //What we do if this partial function matches
      def apply(v1: Int) = v1 * 2
    }

    val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
      def isDefinedAt(x: Int) = x % 2 != 0

      def apply(v1: Int) = v1 * 3
    }

    val whatToDo = doubleEvens orElse tripleOdds //Here we chain the partial functions together

    println(whatToDo(3))
    // RESULT: 9
    println(whatToDo(4))
    // RESULT: 8

    /**
      * Case statements are a quick way to create partial functions. When you create a case statement, the
      * apply and isDefinedAt is created for you
      */

    val doubleEvens1: PartialFunction[Int, Int] = {
      case x if (x % 2) == 0 ⇒ x * 2
    }
    val tripleOdds1: PartialFunction[Int, Int] = {
      case x if (x % 2) != 0 ⇒ x * 3
    }

    val whatToDo1 = doubleEvens1 orElse tripleOdds1 //Here we chain the partial functions together
    println(whatToDo1(3))
    // RESULT: 9
    println(whatToDo1(4))
    // RESULT: 8

    /**
      * The result of partial functions can have an andThen function added to the end of the chain
      */

    val doubleEvens2: PartialFunction[Int, Int] = {
      case x if (x % 2) == 0 ⇒ x * 2
    }
    val tripleOdds2: PartialFunction[Int, Int] = {
      case x if (x % 2) != 0 ⇒ x * 3
    }

    val addFive = (x: Int) ⇒ x + 5
    val whatToDo2 = doubleEvens2 orElse tripleOdds2 andThen addFive //Here we chain the partial functions together
    println(whatToDo2(3))
    // RESULT: 14
    println(whatToDo2(4))
    // RESULT: 13

    /**
      * The result of partial functions can have an andThen function added to the end of the chain
      * used to continue onto another chain of logic
      */

    val doubleEvens3: PartialFunction[Int, Int] = {
      case x if (x % 2) == 0 ⇒ x * 2
    }
    val tripleOdds3: PartialFunction[Int, Int] = {
      case x if (x % 2) != 0 ⇒ x * 3
    }

    val printEven3: PartialFunction[Int, String] = {
      case x if (x % 2) == 0 ⇒ "Even"
    }
    val printOdd3: PartialFunction[Int, String] = {
      case x if (x % 2) != 0 ⇒ "Odd"
    }

    val whatToDo3 = doubleEvens3 orElse tripleOdds3 andThen (printEven3 orElse printOdd3)

    println(whatToDo3(3))
    // RESULT: Odd
    println(whatToDo3(4))
    // RESULT: Even

  }

}
