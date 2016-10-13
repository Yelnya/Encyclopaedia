package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P11 {

  def main(args: Array[String]): Unit = {

    /**
      * Modified run-length encoding.
      * Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied
      * into the result list. Only elements with duplicates are transferred as (N, E) terms.
      * List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
      * should be
      * List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
      */

    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    /**
      * Solution 1
      * Petra
      * using method "makeTuples" from P10
      */

    def makeTuples(list: List[Symbol]): List[(Any)] = list match {
      case Nil => List() // end of list
      case head :: tail =>
        if (head == tail.head) {    // if current and next elements are matching...
          val (firstTuple, secondTuple) = tail.span(_ == head) // make two tuple lists from span
          (firstTuple.size + 1, head) :: makeTuples(secondTuple) // formatting print, and +1 because head is element, too
        } else {  // if current and next elements are NOT matching
          head +: makeTuples(tail) // ONLY add head element (without list) to final list
        }
    }
    println(makeTuples(list))
    // RESULT: List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))

    /**
      * Solution 2
      * Phil Gold
      * using method "encode" from P10 and method "pack" from P09
      */

    def pack[A](ls: List[A]): List[List[A]] = {
      if (ls.isEmpty) List(List())
      else {
        val (packed, next) = ls span { _ == ls.head }
        if (next == Nil) List(packed)
        else packed :: pack(next)
      }
    }

    def encode[A](ls: List[A]): List[(Int, A)] = pack(ls) map {
      e => (e.length, e.head)
    }

    def encodeModified[A](ls: List[A]): List[Any] = encode(ls) map {
      t => if (t._1 == 1) t._2 else t
    }
    println(encodeModified(list))
    // RESULT: List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))

    // Just for fun, here's a more typesafe version.
    def encodeModified2[A](ls: List[A]): List[Either[A, (Int, A)]] = encode(ls) map
      {
        t => if (t._1 == 1) Left(t._2) else Right(t)
      }
    println(encodeModified2(list))
    // RESULT: List(Right((4,'a)), Left('b), Right((2,'c)), Right((2,'a)), Left('d), Right((4,'e)))

  }
}
