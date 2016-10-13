package ninetynine_problems.working_with_lists

import scala.collection.mutable.ListBuffer

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P15 {

  def main(args: Array[String]): Unit = {

    /**
      * P15: Duplicate the elements of a list a given number of times.
      * duplicateN(3, List('a, 'b, 'c, 'c, 'd))
      * should look like
      * List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
      */

    val list = List('a, 'b, 'c, 'c, 'd)

    /**
      * Solution 1
      * Petra
      * for x multiplication
      * BUT with sublists!!! -> not wished as solution for this example
      */

    var subList = new ListBuffer[Any]  // mutable list

    def multiplicateElementsOfList(list: List[Any], times: Int) : List[Any] = {
      subList.clear()
      list match {
        case Nil => List()
        case head :: tail =>
          var i = 0
          while (i < times) {
            subList += head
            i += 1
          }
          subList.toList +: multiplicateElementsOfList(tail, times)
      }
    }

    println(multiplicateElementsOfList(list, 3))  // set here wished number of times to multiplicate values of list
    // RESULT: List(List('a, 'a, 'a), List('b, 'b, 'b), List('c, 'c, 'c), List('c, 'c, 'c), List('d, 'd, 'd))

    /**
      * Solution 2
      * Petra
      * for x multiplication, and result without subLists
      */

    def solutionWithFlatMap(list: List[Symbol], times: Int) : List[Symbol] = list.flatMap {
      e => List.fill(times)(e)
    }

    println(solutionWithFlatMap(list, 3))  // set here wished number of times to multiplicate values of list
    // RESULT: List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)

    /**
      * Solution 3
      * Phil Gold
      * BUT cannot compile this solution!!
      */

//    def duplicateN[A](n: Int, ls: List[A]): List[A] = ls flatMap {
//      List.make(n, _)
//    }
//
//    println(duplicateN(3, list))

  }
}
