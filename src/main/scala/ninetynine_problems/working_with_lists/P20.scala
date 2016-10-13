package ninetynine_problems.working_with_lists

/**
  * Remove the Kth element from a list.
  */
object P20 {

  def main(args: Array[String]): Unit = {

    /**
      * P20: Remove the Kth element from a list.
      * Return the list and the removed element in a Tuple. Elements are numbered from 0.
      * removeAt(1, List('a, 'b, 'c, 'd))
      * should look like
      * (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)
      */

    val list = List('a, 'b, 'c, 'd)
    val elementAtIndexToRemove = 1

    /**
      * Solution 1
      * Petra
      * with built-ins
      * NOT the right solution, one list too much
      */

    def removeElement(list: List[Symbol], elementToRemove: Int) : List[Any] = {
      List(list.take(elementAtIndexToRemove) ++ list.takeRight(list.length - (elementAtIndexToRemove + 1))) :+ list(elementAtIndexToRemove)
    }

    println(removeElement(list, elementAtIndexToRemove))
    // RESULT: List(List('a, 'c, 'd), 'b)

    /**
      * Solution 2
      * Phil Gold
      */

    def removeAt[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
      case (Nil, _) if n < 0 => throw new NoSuchElementException
      case (pre, e :: post)  => (pre ::: post, e)
      case (pre, Nil)        => throw new NoSuchElementException
    }
    println(removeAt(elementAtIndexToRemove, list))
    // RESULT: (List('a, 'c, 'd),'b)

    /**
      * Solution 3
      * Phil Gold
      */

    def removeAt2[A](n: Int, ls: List[A]): (List[A], A) =
    if (n < 0) throw new NoSuchElementException
    else (n, ls) match {
      case (_, Nil) => throw new NoSuchElementException
      case (0, h :: tail) => (tail, h)
      case (_, h :: tail) => {
        val (t, e) = removeAt(n - 1, ls.tail)
        (ls.head :: t, e)
      }
    }
    println(removeAt2(elementAtIndexToRemove, list))
    // RESULT: (List('a, 'c, 'd),'b)

  }
}
