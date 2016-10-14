package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P24 {

  def main(args: Array[String]): Unit = {

    /**
      * P24: Lotto: Draw N different random numbers from the set 1..M.
      * lotto(6, 49)
      * should be
      * List[Int] = List(23, 1, 17, 33, 21, 37)
      */

    val list = (1 to 49).toList
    val numberOfRandoms = 6

    /**
      * Helpers
      */

    val r = new scala.util.Random
    val rangeMin = 0
    val rangeMax = list.length - 1

    def generateARandomNumberBetween(rangeMin: Int, rangeMax: Int) : Int = {
      rangeMin + r.nextInt((rangeMax - rangeMin) + 1)
    }

    def randomList(numberOfRandoms: Int, list: List[Int], resultList: List[Int]) : List[Int] = {
      if (numberOfRandoms > 0) {
        randomList(numberOfRandoms -1, list, resultList :+ list(generateARandomNumberBetween(rangeMin, rangeMax)))
      } else {
        resultList
      }
    }
    println(randomList(numberOfRandoms, list, Nil))
    // RESULT: example: List(28, 24, 25, 4, 20, 25)


    /**
      * Solution 2
      * Phil Gold
      * with help of method "randomSelect" from P23 and "removeAt" from P20
      * This algorithm is O(n up 2), but it makes up for that in simplicity of implementation
      */

    def removeAt[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
      case (Nil, _) if n < 0 => throw new NoSuchElementException
      case (pre, e :: post)  => (pre ::: post, e)
      case (pre, Nil)        => throw new NoSuchElementException
    }

    def randomSelect[A](n: Int, ls: List[A]): List[A] = {
      def randomSelectR(n: Int, ls: List[A], r: util.Random): List[A] =
        if (n <= 0) Nil
        else {
          val (rest, e) = removeAt(r.nextInt(ls.length), ls)
          e :: randomSelectR(n - 1, rest, r)
        }
      randomSelectR(n, ls, new util.Random)
    }

    def lotto(count: Int, max: Int): List[Int] = randomSelect(count, List.range(1, max + 1))
    println(lotto(numberOfRandoms, rangeMax))
    // RESULT: example: List(31, 9, 39, 44, 18, 34)

  }
}
