package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P13 {

  def main(args: Array[String]): Unit = {

    /**
      * P13: Run-length encoding of a list (direct solution).
      * Implement the so-called run-length encoding data compression method directly. I.e. don't use other methods
      * you've written (like P09's pack); do all the work directly.
      * List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
      * should look like
      * List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
      */

    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    /**
      * Solution 1
      * Petra
      */

    def encodeList(list: List[Symbol]) : List[(Int, Symbol)] = list match {
      case Nil => List()
      case head :: tail =>
        val (firstTuple, secondTuple) = tail.span(_ == head)
        (firstTuple.size + 1, head) :: encodeList(secondTuple)
      }

    println(encodeList(list))
    // RESULT: List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

    /**
      * Solution 2
      * Phil Gold
      */

    def encodeDirect[A](ls: List[A]): List[(Int, A)] =
    if (ls.isEmpty) Nil
    else {
      val (packed, next) = ls span { _ == ls.head }
      (packed.length, packed.head) :: encodeDirect(next)
    }
    println(encodeDirect(list))
    // RESULT: List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

  }
}
