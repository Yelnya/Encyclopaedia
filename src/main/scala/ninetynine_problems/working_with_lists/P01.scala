package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

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
      * Petra
      */

    if (!list.isEmpty) {
      println(list.last)
    }
    // RESULT: 8

    /**
      * Solution 2
      * Petra
      */

      if (!list.isEmpty) {
        val sizeOfList = list.size
        println(list(sizeOfList-1))
      }
      // RESULT: 8

    /**
      * Solution 3
      * Phil Gold
      * The standard functional approach is to recurse down the list until we hit the end.  Scala's
      * pattern matching makes this easy.
      */

    def lastRecursive(list: List[Int]): Int = list match {
      case head :: Nil => head  // no tail is left, so get last element
      case _ :: tail => lastRecursive(tail) // re-call function with new tail until there is no tail left
      case _ => -1 /*throw new NoSuchElementException*/    // if List is empty
    }

    println(lastRecursive(list))
    // RESULT: 8

  }
}
