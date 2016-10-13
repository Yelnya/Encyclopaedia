package ninetynine_problems.working_with_lists

import scala.collection.mutable.ListBuffer

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P14 {

  def main(args: Array[String]): Unit = {

    /**
      * P14: Duplicate the elements of a list.
      * List('a, 'b, 'c, 'c, 'd)
      * should be
      * List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
      */

    val list = List('a, 'b, 'c, 'c, 'd)

    /**
      * Solution 1
      * Petra
      */

    def duplicateElementsOfList(list: List[Symbol]) : List[Symbol] = list match {
      case Nil => List()
      case head :: tail => head :: head :: duplicateElementsOfList(tail)
    }

    println(duplicateElementsOfList(list))
    // RESULT: List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)


    /**
      * Solution 2
      * Petra
      */

    println(list.flatMap( e => List(e,e)))
    // RESULT: List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

    /**
      * Solution 3
      * Petra
      * for x multiplication
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

    println(multiplicateElementsOfList(list, 4))  // set here wished number of times to multiplicate values of list
    // RESULT: List(List('a, 'a, 'a, 'a), List('b, 'b, 'b, 'b), List('c, 'c, 'c, 'c), List('c, 'c, 'c, 'c), List('d, 'd, 'd, 'd))


    /**
      * Solution 4
      * Petra
      * for x multiplication, and result without subLists
      */

    def solutionWithFlatMap(list: List[Symbol], times: Int) : List[Symbol] = list.flatMap {
      e => List.fill(times)(e)
    }

    println(solutionWithFlatMap(list, 4))  // set here wished number of times to multiplicate values of list
    // RESULT: List('a, 'a, 'a, 'a, 'b, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd, 'd)

  }
}
