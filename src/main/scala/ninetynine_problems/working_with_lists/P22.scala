package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P22 {

  def main(args: Array[String]): Unit = {

    /**
      * P22: Create a list containing all integers within a given range.
      * range(4, 9)
      * should look like
      * List[Int] = List(4, 5, 6, 7, 8, 9)
      */

    val start = 4
    val end = 9

    /**
      * Solution 1
      * Petra
      */

    val range = start to end
    val list = range.toList
    println(list)
    // RESULT: List(4, 5, 6, 7, 8, 9)

    /**
      * Solution 2
      * Petra
      * with use of method "List.range"
      */

    val list2 = List.range(start, end + 1)
    println(list2)
    // RESULT: List(4, 5, 6, 7, 8, 9)

    /**
      * Solution 3
      * Petra
      * without built-in methods
      */

    def generateListRange(start: Int, end: Int) : List[Int] = {
      if (start <= end) {
        start :: generateListRange(start + 1, end)
      } else {
        Nil
      }
    }
    println(generateListRange(start, end))
    // RESULT: List(4, 5, 6, 7, 8, 9)

    /**
      * Solution 4
      * Phil Gold
      * tail recursive
      */

    def rangeTailRecursive(start: Int, end: Int): List[Int] = {
      def rangeR(end: Int, result: List[Int]): List[Int] = {
        if (end < start) result
        else rangeR(end - 1, end :: result)
      }
      rangeR(end, Nil)
    }
    println(rangeTailRecursive(start, end))
    // RESULT: List(4, 5, 6, 7, 8, 9)

    /**
      * Solution 5
      * Phil Gold
      * The classic functional approach would be to use 'unfoldr', which Scala doesn't have. So we'll write one
      * and then use it.
      */

    def unfoldRight[A, B](s: B)(f: B => Option[(A, B)]): List[A] =
    f(s) match {
      case None         => Nil
      case Some((r, n)) => r :: unfoldRight(n)(f)
    }
    def rangeFunctional(start: Int, end: Int): List[Int] =
      unfoldRight(start) { n =>
        if (n > end) None
        else Some((n, n + 1))
      }
    println(rangeFunctional(start, end))
    // RESULT: List(4, 5, 6, 7, 8, 9)
  }
}
