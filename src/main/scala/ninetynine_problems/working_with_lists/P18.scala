package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P18 {

  def main(args: Array[String]): Unit = {

    /**
      * Extract a slice from a list.
      * Given two indices, I and K, the slice is the list containing the elements from and including the Ith
      * element up to but not including the Kth element of the original list. Start counting the elements with 0.
      * slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
      * should look like
      * List[Symbol] = List('d, 'e, 'f, 'g)
      */

    val list = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val startPoint = 3
    val endPoint = 7

    /**
      * Solution 1
      * Petra
      */

    println(list.slice(startPoint, endPoint))
    // RESULT: List('d, 'e, 'f, 'g)

    /**
      * Solution 2
      * Petra
      */

    def sliceList(list: List[Symbol], startPoint: Int, stopPoint: Int): List[Symbol] = {
      val (firstTuple, secondTuple) = list.splitAt(startPoint)
      secondTuple.take(stopPoint - startPoint)
    }
    println(sliceList(list, startPoint, endPoint))
    // RESULT: List('d, 'e, 'f, 'g)

    /**
      * Solution 3
      * Phil Gold
      * simple recursive
      */

    def sliceRecursive[A](start: Int, end: Int, ls: List[A]): List[A] =
    (start, end, ls) match {
      case (_, _, Nil)                 => Nil
      case (_, e, _)         if e <= 0 => Nil
      case (s, e, h :: tail) if s <= 0 => h :: sliceRecursive(0, e - 1, tail)
      case (s, e, h :: tail)           => sliceRecursive(s - 1, e - 1, tail)
    }
    println(sliceRecursive(startPoint, endPoint, list))
    // RESULT: List('d, 'e, 'f, 'g)

    /**
      * Solution 4
      * Phil Gold
      * tail recursive, using pattern matching
      */

    def sliceTailRecursive[A](start: Int, end: Int, ls: List[A]): List[A] = {
      def sliceR(count: Int, curList: List[A], result: List[A]): List[A] =
        (count, curList) match {
          case (_, Nil)                     => result.reverse
          case (c, h :: tail) if end <= c   => result.reverse
          case (c, h :: tail) if start <= c => sliceR(c + 1, tail, h :: result)
          case (c, _ :: tail)               => sliceR(c + 1, tail, result)
        }
      sliceR(0, ls, Nil)
    }
    println(sliceTailRecursive(startPoint, endPoint, list))
    // RESULT: List('d, 'e, 'f, 'g)

    /**
      * Solution 5
      * Phil Gold
      * Since several of the patterns are similar, we can condense the tail recursive solution a little.
      */

    def sliceTailRecursive2[A](start: Int, end: Int, ls: List[A]): List[A] = {
      def sliceR(count: Int, curList: List[A], result: List[A]): List[A] = {
        if (curList.isEmpty || count >= end) result.reverse
        else sliceR(count + 1, curList.tail,
          if (count >= start) curList.head :: result
          else result)
      }
      sliceR(0, ls, Nil)
    }
    println(sliceTailRecursive2(startPoint, endPoint, list))
    // RESULT: List('d, 'e, 'f, 'g)

    /**
      * Solution 6
      * Phil Gold
      * Functional
      */

    def sliceFunctional[A](s: Int, e: Int, ls: List[A]): List[A] =
    ls drop s take (e - (s max 0))

    println(sliceFunctional(startPoint, endPoint, list))
    // RESULT: List('d, 'e, 'f, 'g)
  }
}
