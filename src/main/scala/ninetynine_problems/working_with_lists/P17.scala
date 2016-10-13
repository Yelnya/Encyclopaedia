package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P17 {

  def main(args: Array[String]): Unit = {

    /**
      * P17: Split a list into two parts.
      * The length of the first part is given. Use a Tuple for your result.
      * split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
      * should look like
      * (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
      */

    val list = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val positionToSplit = 3

    /**
      * Solution 1
      * Petra
      */

    println(list.splitAt(positionToSplit))
    // RESULT: (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))


    /**
      * Solution 2
      * Phil Gold
      * simple recursion
      */

    def splitRecursive[A](n: Int, ls: List[A]): (List[A], List[A]) = (n, ls) match {
      case (_, Nil)       => (Nil, Nil)
      case (0, list)      => (Nil, list)
      case (n, h :: tail) => {
        val (pre, post) = splitRecursive(n - 1, tail)
        (h :: pre, post)
      }
    }
    println(splitRecursive(positionToSplit, list))
    // RESULT: (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

    /**
      * Solution 3
      * Phil Gold
      * tail recursive
      */

    def splitTailRecursive[A](n: Int, ls: List[A]): (List[A], List[A]) = {
      def splitR(curN: Int, curL: List[A], pre: List[A]): (List[A], List[A]) =
        (curN, curL) match {
          case (_, Nil)       => (pre.reverse, Nil)
          case (0, list)      => (pre.reverse, list)
          case (n, h :: tail) => splitR(n - 1, tail, h :: pre)
        }
      splitR(n, ls, Nil)
    }
    println(splitTailRecursive(positionToSplit, list))
    // RESULT: (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

    /**
      * Solution 4
      * Phil Gold
      * functional
      */

    def splitFunctional[A](n: Int, ls: List[A]): (List[A], List[A]) = (ls.take(n), ls.drop(n))

    println(splitFunctional(positionToSplit, list))
    // RESULT: (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

  }
}
