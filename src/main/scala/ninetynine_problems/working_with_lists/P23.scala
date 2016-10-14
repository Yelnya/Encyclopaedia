package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P23 {

  def main(args: Array[String]): Unit = {

    /**
      * P23: Extract a given number of randomly selected elements from a list.
      * randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h))
      * should be
      * List[Symbol] = List('e, 'd, 'a)
      */

    val list = List('a, 'b, 'c, 'd, 'f, 'g, 'h)
    val numberOfRandoms = 3

    /**
      * Helpers
      */

    val r = new scala.util.Random
    val rangeMin = 0
    val rangeMax = list.length - 1
    // generate new Random Number between 0 and list.size - 1 (because of index)

    def generateARandomNumberBetween(rangeMin: Int, rangeMax: Int) : Int = {
      rangeMin + r.nextInt((rangeMax - rangeMin) + 1)
    }

    /**
      * Solution 1
      * Petra
      */

    def randomList(numberOfRandoms: Int, list: List[Symbol], resultList: List[Symbol]) : List[Symbol] = {
      if (numberOfRandoms > 0) {
        randomList(numberOfRandoms -1, list, resultList :+ list(generateARandomNumberBetween(rangeMin, rangeMax)))
      } else {
        resultList
      }
    }
    println(randomList(numberOfRandoms, list, Nil))   // Nil: start with empty resultList
    // RESULT: example: List('f, 'g, 'b)

    /**
      * Solution 2
      * Phil Gold
      * with help of method "removeAt" from P20
      */

    def removeAt[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
      case (Nil, _) if n < 0 => throw new NoSuchElementException
      case (pre, e :: post)  => (pre ::: post, e)
      case (pre, Nil)        => throw new NoSuchElementException
    }

    def randomSelect1[A](n: Int, ls: List[A]): List[A] =
      if (n <= 0) Nil
      else {
        val (rest, e) = removeAt((new util.Random).nextInt(ls.length), ls)
        e :: randomSelect1(n - 1, rest)
      }
    println(randomSelect1(numberOfRandoms, list))
    // RESULT: example: List('c, 'b, 'g)

    /**
      * Solution 3
      * Phil Gold
      * with help of method "removeAt" from P20
      * It can be expensive to create a new Random instance every time, so let's only do it once.
      */

    def randomSelect[A](n: Int, ls: List[A]): List[A] = {
      def randomSelectR(n: Int, ls: List[A], r: util.Random): List[A] =
        if (n <= 0) Nil
        else {
          val (rest, e) = removeAt(r.nextInt(ls.length), ls)
          e :: randomSelectR(n - 1, rest, r)
        }
      randomSelectR(n, ls, new util.Random)
    }
    println(randomSelect(numberOfRandoms, list))
  }
}
