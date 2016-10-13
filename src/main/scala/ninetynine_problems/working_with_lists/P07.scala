package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P07 {

  def main(args: Array[String]): Unit = {

    /**
      * P07: Flatten a nested list structure.
      * List(List(1, 1), 2, List(3, List(5, 8)))
      */

    val list = List(List(1, 1), 2, List(3, List(5, 8)))

    // for ONE Nested List, val result = list.flatten will do.
    // for multiple Flattened Lists:

    /**
      * Solution 1
      * Phil Gold
      */

    def flattenMultipleLists(list: List[Any]): List[Any] = list.flatMap {
      case i: List[_] => flattenMultipleLists(i)
      case n => List(n)
    }

    println(flattenMultipleLists(list))
    // RESULT: List(1, 1, 2, 3, 5, 8)

  }
}
