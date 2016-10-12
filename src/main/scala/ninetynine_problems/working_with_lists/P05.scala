package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P05 {

  def main(args: Array[String]): Unit = {

    /**
      * P05: Reverse a list
      * List(1, 1, 2, 3, 5, 8)
      */

    var list = List(1, 1, 2, 3, 5, 8)

    /**
      * Solution 1
      */

    println(list.reverse)
    // RESULT: List(8, 5, 3, 2, 1, 1)

    /**
      * Solution 2
      */

    def reverseListFunction1 (list: List[Int], reverseList: List[Int]) : List[Int] = {
      if (list.size > 0) {
        reverseListFunction1(list.dropRight(1), reverseList :+ list.last)
      } else {
        reverseList
      }
    }

    println(reverseListFunction1(list, Nil))
    // RESULT: List(8, 5, 3, 2, 1, 1)

    /**
      * Solution 3
      */

    def reverseListFunction2 (list: List[Int], reverseList: List[Int]) : List[Int] = list match {
      case head :: Nil => head +: reverseList // end of list
      case head :: tail => reverseListFunction2(tail, head +:reverseList)
      case Nil => throw new NoSuchElementException
      }

    println(reverseListFunction2(list, Nil))
    // RESULT: List(8, 5, 3, 2, 1, 1)

    /**
      * Solution 4
      * Purely Functional
      */

    def reverseFunctional[A](list: List[Int]): List[Int] = {
      list.foldLeft(List[Int]()) {
        (r, h) => h :: r
      }
    }

    println(reverseFunctional(list))
    // RESULT: List(8, 5, 3, 2, 1, 1)

  }
}
