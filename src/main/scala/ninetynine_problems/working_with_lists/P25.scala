package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P25 {

  def main(args: Array[String]): Unit = {

    /**
      * P25: Generate a random permutation of the elements of a list.
      * Hint: Use the solution of problem P23.
      * randomPermute(List('a, 'b, 'c, 'd, 'e, 'f))
      * should be
      * List[Symbol] = List('b, 'a, 'd, 'c, 'e, 'f)
      */

    val list = List('a, 'b, 'c, 'd, 'e, 'f)

    /**
      * Helpers
      */

    // from: P23
    val r = new scala.util.Random
    val rangeMin = 0
    val rangeMax = list.length - 1
    // generate new Random Number between 0 and list.size - 1 (because of index)

    def generateARandomNumberBetween(rangeMin: Int, rangeMax: Int): Int = {
      rangeMin + r.nextInt((rangeMax - rangeMin) + 1)
    }

    // from: P20
    def removeAt[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
      case (Nil, _) if n < 0 => throw new NoSuchElementException
      case (pre, e :: post) => (pre ::: post, e)
      case (pre, Nil) => throw new NoSuchElementException
    }
    //    println(removeAt(3, list))
    // returns: (List('a, 'b, 'c, 'e, 'f),'d)

    // from: P20, but modified to only return a list
    def removeAt1(n: Int, ls: List[Symbol]): List[Symbol] = ls.splitAt(n) match {
      case (Nil, _) if n < 0 => throw new NoSuchElementException
      case (pre, e :: post) => (pre ++ post)
      case (pre, Nil) => throw new NoSuchElementException
    }
    //    println(removeAt1(3, list))
    // returns: List('a, 'b, 'c, 'e, 'f)

    /**
      * Solution 1
      * Petra
      */

    def permutateList(list: List[Symbol], finalList: List[Symbol]): List[Symbol] = {
      if (list.length > 0) {
        val position = generateARandomNumberBetween(rangeMin, list.length - 1)
        permutateList(removeAt1(position, list), finalList :+ list(position))
      } else {
        finalList
      }
    }
    println(permutateList(list, Nil))
    // RESULT: example: List('d, 'a, 'e, 'b, 'c, 'f)

    /**
      * Solution 2
      * Phil Gold
      * with help of method "randomSelect" from P23 and "removeAt" from P20
      * This algorithm is O(n up 2), but it makes up for that in simplicity of implementation
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

    def randomPermute1[A](ls: List[A]): List[A] = randomSelect(ls.length, ls)
    println(randomPermute1(list))
    // RESULT: example: List('f, 'd, 'a, 'e, 'c, 'b)

    /**
      * Solution 3
      * Phil Gold
      * The canonical way to shuffle imperatively is Fisher-Yates.  It requires a mutable array.  This is O(n).
      * BUT throws an error (?)
      */

//    def randomPermute[A](ls: List[A]): List[A] = {
//      val rand = new util.Random
//      val a = ls.toArray
//      for (i <- a.length - 1 to 1 by -1) {
//        val i1 = rand.nextInt(i + 1)
//        val t = a(i)
//        a.update(i, a(i1))
//        a.update(i1, t)
//      }
//      a.toList
//    }
//    println(randomPermute(list))
    // RESULT = error

  }
}
