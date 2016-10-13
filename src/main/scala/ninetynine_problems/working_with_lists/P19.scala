package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P19 {

  def main(args: Array[String]): Unit = {

    /**
      * Rotate a list N places to the left.
      * rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
      * should be
      * List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
      *
      * rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
      * should be
      * List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
      */

    val list = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val rotatePosition = -2

    /**
      * Solution 1
      * Petra
      */

    def rotateListWithSplit(list: List[Symbol], rotatePosition: Int): List[Any] = {
      if (rotatePosition < 0) {
        val (firstList, secondList) = list.splitAt(list.length + rotatePosition)
        secondList ++ firstList
      } else if (rotatePosition < list.size) {
        throw new ArrayIndexOutOfBoundsException
      } else {
        val (firstList, secondList) = list.splitAt(rotatePosition)
        secondList ++ firstList
      }
    }
    println(rotateListWithSplit(list, rotatePosition))

    // RESULT (rotatePosition = 3): List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
    // RESULT (rotatePosition = -2): List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)

    /**
      * Solution 2
      * Phil Gold
      */

    def rotate[A](n: Int, ls: List[A]): List[A] = {
      val nBounded = if (ls.isEmpty) 0 else n % ls.length
      if (nBounded < 0) rotate(nBounded + ls.length, ls)
      else (ls drop nBounded) ::: (ls take nBounded)
    }

    println(rotate(rotatePosition, list))
    // RESULT (rotatePosition = 3): List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
    // RESULT (rotatePosition = -2): List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)

  }
}
