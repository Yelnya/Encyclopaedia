package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P03 {

  def main(args: Array[String]): Unit = {

    /**
      * Find the Kth element of a list
      * List(1, 1, 2, 3, 5, 8)
      */

    val list = List(1, 1, 2, 3, 5, 8)
    val k = 3   // which element of the list?

    /**
      * Solution 1
      * Petra
      */

    def findKthElementOfTheList(list: List[Int], k : Int) : Int = {
      if (!list.isEmpty) {
        //take list size, look if k > list size or < 0 => error
        if (k > list.size || k < 1) {
          throw new IndexOutOfBoundsException
        } else {
          //if k <= listSize: get pos (k - 1) from list elements and return
          list(k - 1)
        }
      } else {
        throw new NoSuchElementException
      }
    }

    println(findKthElementOfTheList(list, k))
    // RESULT: 2

    /**
      * Solution 2
      * Phil Gold
      * BUT gives wrong result!
      */

    def findKWithPatternMatching(k: Int, list: List[Int]) : Int = (k, list) match {
      case (0, head :: _) => head   // if zero, searched element is found and value is returned
      case (k, _ :: tail) =>  findKWithPatternMatching(k - 1, tail) // not last element -> counting k back to zero
      case (_, Nil) => throw new NoSuchElementException  // if List is empty
    }

    val searchValue = findKWithPatternMatching(k, list)
    println(searchValue)
    // RESULT: 3
    // TODO: why wrong result??

  }
}
