package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P02 {

  def main(args: Array[String]): Unit = {

    /**
      * P02: Find the last but one element of a list.
      * List(1, 1, 2, 3, 5, 8)
      */

    val list = List(1, 1, 2, 3, 5, 8)

    /**
      * Solution 1
      * Petra
      */

    if (!list.isEmpty) {
      val listSize = list.size
      println(list(listSize - 2))
    }
    // RESULT: 5

    /**
      * Solution 2
      * Petra
      */

    def getLastButOneElementOfList(list: List[Int]): Int = list match {
      case h :: tail => if (tail.size > 1) {
        // if size of tail > 1, re-call function
        getLastButOneElementOfList(tail)
      } else {
        // if tail size is 1, return current element
        h
      }
      case _ => -1 /*throw new NoSuchElementException*/
      // if List is empty
    }
    println(getLastButOneElementOfList(list))
    // RESULT: 5

    /**
      * Solution 3: Built-In
      * Phil Gold
      */

    def penultimateBuiltin(list: List[Int]): Int = {
      if (list.isEmpty) {
        -1
      } else {
        list.init.last
      }
    }
    println(penultimateBuiltin(list))
    // RESULT: 5

  }
}
