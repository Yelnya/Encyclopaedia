package ninetynine_problems.working_with_lists

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

  }
}
