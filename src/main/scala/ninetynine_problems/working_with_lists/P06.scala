package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P06 {

  def main(args: Array[String]): Unit = {

    /**
      * Find out whether a list is a palindrome.
      * List(1, 2, 3, 2, 1)
      */

    val list = List(1, 2, 3, 2, 1)

    /**
      * Solution 1
      */

    // take first and last, look if same, then cut both and look again
    // elements of a palindrome can be even OR odd !!

    def lookIfPalindrome(list: List[Int], isPalindrome: Boolean): Boolean = list match {
      case (p :: tail) => if (p == list.last) {
        lookIfPalindrome(tail.dropRight(1), true)   // remove last element of tail
      } else {
        false
      }
      case (_ :: Nil) => isPalindrome   // if this is last element
      case (Nil) => isPalindrome    // if list is empty
    }

    println(lookIfPalindrome(list, false))  // initially boolean is false
    // RESULT: true

    /**
      * Solution 2
      */

    // use of reverse method

    def lookIfPalindromeWithReverse(list: List[Int]) : Boolean = {
      val listReverse = list.reverse
      if (list == listReverse) {
        true
      } else {
        false
      }
    }

    println(lookIfPalindromeWithReverse(list))

    /**
      * Solution 3
      */

    def lookIfPalindromeWithReverseShorter(list: List[Int]) : Boolean = {
      list == list.reverse
    }

    println(lookIfPalindromeWithReverseShorter(list))

  }
}
