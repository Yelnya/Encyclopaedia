package ninetynine_problems.working_with_lists

/**
  * http://aperiodic.net/phil/scala/s-99/
  */
object P26 {

  def main(args: Array[String]): Unit = {

    /**
      * P26: Generate the combinations of K distinct objects chosen from the N elements of a list.
      * In how many ways can a committee of 3 be chosen from a group of 12 people? We all know that there
      * are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient). For pure
      * mathematicians, this result may be great. But we want to really generate all the possibilities.
      * combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
      * should be
      * List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...
      */

    val list = List('a, 'b, 'c, 'd, 'e, 'f)
    val combinationElements = 3

    /**
      * Solution 1
      * Petra
      * BUT result is not exactly as wished, some combinations are missing - refining necessary
      */

    def combinations(list: List[Symbol], finalList: List[Any]): List[Any] = {

      def fillBufferList(headSymbol: Symbol, tempList: List[Symbol], finalList: List[Symbol]): List[Symbol] = tempList match {
        case Nil => headSymbol +: finalList
        case head :: tail => fillBufferList(headSymbol, tail, finalList :+ head)
      }
      // RESULT: List('a, 'b, 'c)
      def fillLineList(headSymbol: Symbol, tempList: List[Symbol], finalList: List[Symbol]): List[Any] = tempList match {
        case head :: Nil => finalList
        case head :: tail => finalList ++ (fillBufferList(headSymbol, tempList.take(combinationElements - 1), Nil) :: fillLineList(headSymbol, tail, finalList))
      }
      // RESULT: List(List('a, 'b, 'c), List('a, 'c, 'd), List('a, 'd, 'e), List('a, 'e, 'f))
      list match {
        case head :: Nil => finalList
        case head :: tail =>
          finalList ++ combinations(tail, fillLineList(head, tail, Nil))
      }
    }
    println(combinations(list, Nil))
    // RESULT: List(List('a, 'b, 'c), List('a, 'c, 'd), List('a, 'd, 'e), List('a, 'e, 'f), List('b, 'c, 'd),
    // List('b, 'd, 'e), List('b, 'e, 'f), List('c, 'd, 'e), List('c, 'e, 'f), List('d, 'e, 'f))


    /**
      * Solution 2
      * Phil Gold
      * flatMapSublists is like list.flatMap, but instead of passing each element, to the function, it
      * passes successive sublists of L.
      */

    def flatMapSublists[A,B](ls: List[A])(f: (List[A]) => List[B]): List[B] =
    ls match {
      case Nil => Nil
      case sublist@(_ :: tail) => f(sublist) ::: flatMapSublists(tail)(f)
    }
    // TODO: what means sublist@ ??

    def listCombinations[A](n: Int, ls: List[A]): List[List[A]] =
      if (n == 0) List(Nil)
      else flatMapSublists(ls) { sl =>
        listCombinations(n - 1, sl.tail) map {sl.head :: _}
      }

    println(listCombinations(combinationElements, list))
    // RESULT: List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), List('a, 'b, 'f), List('a, 'c, 'd),
    // List('a, 'c, 'e), List('a, 'c, 'f), List('a, 'd, 'e), List('a, 'd, 'f), List('a, 'e, 'f), List('b, 'c, 'd),
    // List('b, 'c, 'e), List('b, 'c, 'f), List('b, 'd, 'e), List('b, 'd, 'f), List('b, 'e, 'f), List('c, 'd, 'e),
    // List('c, 'd, 'f), List('c, 'e, 'f), List('d, 'e, 'f))

  }
}
