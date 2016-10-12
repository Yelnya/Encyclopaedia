package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P08 {

  def main(args: Array[String]): Unit = {

    /**
      * Eliminate consecutive duplicates of list elements.
      * If a list contains repeated elements they should be replaced with a single copy of the element.
      * The order of the elements should not be changed.
      * List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
      */

    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    /**
      * Solution 1
      * Petra, without method templates
      */

    def eliminateCopies(list: List[Symbol], listFinal: List[Symbol]): List[Symbol] = list match {
      case head :: Nil => listFinal :+ head       // if this is last Element
      case head :: tail => if (head == tail.head) {   // if first two elements do match
        eliminateCopies(tail, listFinal)  // cut first element, leave listFinal the same
      } else {
        eliminateCopies(tail, listFinal :+ head)  // cut first element, but add head to finalList
      }
      case Nil => throw new NoSuchElementException   // if list is empty
      }

    println(eliminateCopies(list, Nil))
    // RESULT: List('a, 'b, 'c, 'a, 'd, 'e)

    /**
      * Solution 1-2
      * Petra, without method templates, and only with one list
      */


    def eliminateCopies2(list: List[Symbol]): List[Symbol] = list match {
      case head :: Nil => list       // if this is last Element
      case head :: tail => if (head == tail.head) {   // if first two elements do match
        eliminateCopies2(tail)  // cut first element
      } else {
        head +: eliminateCopies2(tail)  // add head to tail, because no copy
      }
      case Nil => throw new NoSuchElementException   // if list is empty
    }

    println(eliminateCopies2(list))
    // RESULT: List('a, 'b, 'c, 'a, 'd, 'e)

    /**
      * Solution 2
      * standard recursive
      */

    def compressRecursive(list: List[Symbol]): List[Symbol] = list match {
      case Nil => Nil   // adds an empty list to result and returns
      case h :: tail => h :: compressRecursive(tail.dropWhile(_ == h))
      }

    println(compressRecursive(list))
    // RESULT: List('a, 'b, 'c, 'a, 'd, 'e)

    /**
      * Solution 3
      * tail recursive
      */

    def compressTailRecursive[A](list: List[A]): List[A] = {
      def compressR(result: List[A], curList: List[A]): List[A] = curList match {
        case h :: tail => compressR(h :: result, tail.dropWhile(_ == h))
        case Nil => result.reverse
      }
      compressR(Nil, list)
    }

    println(compressTailRecursive(list))
    // RESULT: List('a, 'b, 'c, 'a, 'd, 'e)

    /**
      * Solution 4
      * Functional
      */

    def compressFunctional[A](list: List[A]): List[A] = list.foldRight(List[A]()) {
      (h, r) => if (r.isEmpty || r.head != h){
        h :: r
      } else {
        r
      }
    }

    println(compressFunctional(list))
    // RESULT: List('a, 'b, 'c, 'a, 'd, 'e)

  }
}
