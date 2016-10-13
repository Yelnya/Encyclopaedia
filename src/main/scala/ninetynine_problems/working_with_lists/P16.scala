package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P16 {

  def main(args: Array[String]): Unit = {

    /**
      * P16: Drop every Nth element from a list.
      * drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
      * should look like
      * List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
      */

    val list = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
//    val list = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l, 'm, 'n, 'o, 'p)

    /**
      * Solution 1
      * Petra
      * with error handling because of NoSuchElementException
      * very un-elegant and not suitable for higher ranges
      */

    def dropEveryThirdElementFromList(list: List[Symbol]) : List[Symbol] = list match {
      case Nil => Nil
      case head :: tail =>
        if (list.size > 2) {
          head :: tail.head :: dropEveryThirdElementFromList(list.drop(3))
        } else if (list.size == 2) {
          head :: tail.head :: dropEveryThirdElementFromList(Nil)
        } else {
          head :: dropEveryThirdElementFromList(Nil)
        }
    }

    println(dropEveryThirdElementFromList(list))
    // RESULT: List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)


    /**
      * Solution 2
      * Petra
      * with error handling because of NoSuchElementException
      */

    def dropEveryThirdElementFromList2(list: List[Symbol]) : List[Symbol] = list match {
      case Nil => Nil
      case head :: tail =>
        if (list.size > 2) {
          list.take(2) ++ dropEveryThirdElementFromList2(list.drop(3))
        } else {
          list.take(list.size) ++ dropEveryThirdElementFromList2(Nil)
        }
    }

    println(dropEveryThirdElementFromList2(list))
    // RESULT: List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

    /**
      * Solution 3
      * Petra
      * with error handling because of NoSuchElementException
      * element to chop is not 3 but can be chosen by user
      */

    def dropChosenElementFromList(list: List[Symbol], elementToChop: Int) : List[Symbol] = list match {
      case Nil => Nil
      case head :: tail =>
        if (list.size > elementToChop - 1) {
          list.take(elementToChop - 1) ++ dropEveryThirdElementFromList(list.drop(elementToChop))
        } else {
          list.take(list.size) ++ dropEveryThirdElementFromList(Nil)
        }
    }

    println(dropChosenElementFromList(list, 3))   // every fifth element should be chopped
    // RESULT: List('a, 'b, 'c, 'd, 'f, 'g, 'i, 'j)

    /**
      * Solution 4
      * Phil Gold
      * simple recursion
      */

    def dropRecursive[A](n: Int, ls: List[A]): List[A] = {
      def dropR(c: Int, curList: List[A]): List[A] = (c, curList) match {
        case (_, Nil)       => Nil
        case (1, _ :: tail) => dropR(n, tail)
        case (_, h :: tail) => h :: dropR(c - 1, tail)
      }
      dropR(n, ls)
    }
    println(dropRecursive(3, list))
    // RESULT: List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

    /**
      * Solution 5
      * Phil Gold
      * Tail recursive
      */

    def dropTailRecursive[A](n: Int, ls: List[A]): List[A] = {
      def dropR(c: Int, curList: List[A], result: List[A]): List[A] = (c, curList) match {
        case (_, Nil)       => result.reverse
        case (1, _ :: tail) => dropR(n, tail, result)
        case (_, h :: tail) => dropR(c - 1, tail, h :: result)
      }
      dropR(n, ls, Nil)
    }

    println(dropTailRecursive(3, list))
    // RESULT: List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

    /**
      * Solution 6
      * Phil Gold
      * Functional
      */

    def dropFunctional[A](n: Int, ls: List[A]): List[A] =
    ls.zipWithIndex filter { v => (v._2 + 1) % n != 0 } map { _._1 }

    println(dropFunctional(3, list))
    // RESULT: List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)


  }
}
