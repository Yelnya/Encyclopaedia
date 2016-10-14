package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P21 {

  def main(args: Array[String]): Unit = {

    /**
      * P21: Insert an element at a given position into a list.
      * insertAt('new, 1, List('a, 'b, 'c, 'd))
      * should look like
      * List[Symbol] = List('a, 'new, 'b, 'c, 'd)
      */

    val list = List('a, 'b, 'c, 'd)
    val positionToInsert = 1
    val valueToInsert = 'new

    /**
      * Solution 1
      * Petra
      */

    def insertValueAtPosition(list: List[Symbol], positionToInsert: Int, valueToInsert: Symbol): List[Symbol] = {
      if (positionToInsert < list.length) {
        list match {
          case Nil => Nil
          case head :: tail =>
            if (positionToInsert == 0) {
              valueToInsert :: head :: tail
            } else {
              head :: insertValueAtPosition(tail, positionToInsert - 1, valueToInsert)
            }
        }
      } else {
        throw new IndexOutOfBoundsException
      }
    }
    println(insertValueAtPosition(list, positionToInsert, valueToInsert))
    // RESULT: List('a, 'new, 'b, 'c, 'd)

    /**
      * Solution 2
      * Phil Gold
      */

    def insertAt[A](e: A, n: Int, ls: List[A]): List[A] = ls.splitAt(n) match {
      case (pre, post) => pre ::: e :: post
    }
    println(insertAt(valueToInsert, positionToInsert, list))
    // RESULT: List('a, 'new, 'b, 'c, 'd)

  }
}
