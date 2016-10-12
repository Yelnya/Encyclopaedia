package ninetynine_problems.working_with_lists

object P01 {

  def main(args: Array[String]): Unit = {

    /**
      * Working with lists
      *
      * In Scala, lists are objects of type List[A], where A can be any type. Lists are effective for many
      * recursive algorithms, because it's easy to add elements to the head of a list, and to get the tail of the
      * list, which is everything but the first element.
      */

    /**
      * P01: Find the last element of a list.
      * List(1, 1, 2, 3, 5, 8)
      */

    val list = List(1, 1, 2, 3, 5, 8)

    /**
      * Solution 1
      */

    println(list.last)
    // RESULT: 8

    /**
      * Solution 2
      */

    val sizeOfList = list.size
    println(list(sizeOfList-1))
    // RESULT: 8
  }
}
