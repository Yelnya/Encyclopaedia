package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P10 {

  def main(args: Array[String]): Unit = {

    /**
      * P10: Run-length encoding of a list.
      * Use the result of problem P09 to implement the so-called run-length encoding data compression method.
      * Consecutive duplicates of elements are encoded as tuples (N, E) where N is the number of duplicates
      * of the element E.
      * List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
      * Should be:
      * List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
      */

    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    /**
      * Solution 1
      * Petra
      */

    //    println(list.span(_ =='a))  // for testing what span does
    // RESULT: (List('a, 'a, 'a, 'a),List('b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))

    def makeTuples(list: List[Symbol]): List[(Int, Symbol)] = list match {
      case Nil => List() // end of list
      case head :: tail =>
        val (firstTuple, secondTuple) = tail.span(_ == head) // make two tuple lists from span
        (firstTuple.size + 1, head) :: makeTuples(secondTuple) // formatting print, and +1 because head is element, too
    }
    println(makeTuples(list))
    // RESULT: List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

    /**
      * Solution 2
      * Phil Gold
      * working with solution from P09, using the result of the "pack" method
      */

    def pack[A](ls: List[A]): List[List[A]] = {
      if (ls.isEmpty) List(List())
      else {
        val (packed, next) = ls span { _ == ls.head }
        if (next == Nil) List(packed)
        else packed :: pack(next)
      }
    }

    def encode[A](ls: List[A]): List[(Int, A)] =
      pack(ls) map {
          e => (e.length, e.head)
        }

    println(encode(list))
    // RESULT: List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

  }
}
