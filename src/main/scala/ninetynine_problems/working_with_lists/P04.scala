package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P04 {

  def main(args: Array[String]): Unit = {

    /**
      * P04: Find the number of elements of a list.
      * List(1, 1, 2, 3, 5, 8)
      */

    val list = List(1, 1, 2, 3, 5, 8)

    /**
      * Solution 1
      * Petra
      */

    println(list.size)
    // RESULT: 6

    /**
      * Solution 2
      * Petra
      */

    println(list.length)
    // RESULT: 6

    /**
      * Solution 3
      * Phil Gold
      */

    def findNumberOfElementsInAList (n : Int, list: List[Int]) : Int = (n, list) match {
      case (n, _ :: Nil) => n + 1   //if end of list is reached, return n and a last + 1
      case (n, _ :: tail) => findNumberOfElementsInAList(n + 1, tail)
      case (0, Nil) => 0
      }

    println(findNumberOfElementsInAList(0, list))   // n = 0 at the beginning
    // RESULT: 6

  }
}
