package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P35 {

  def main(args: Array[String]): Unit = {

    /**
      * P35: Determine the prime factors of a given positive integer.
      * Construct a flat list containing the prime factors in ascending order.
      * 315.primeFactors
      * solution should look like
      * List[Int] = List(3, 3, 5, 7)
      */

    /**
      * Solution
      * Petra
      * with help of method "primesUnder" from P31
      */

    def value = 315

    def prime(num: Int, factors: List[Int]): Boolean = factors.forall(num % _ != 0)

    def primesUnder(n: Int): List[Int] = {
      require(n >= 2)

      def rec(i: Int, primes: List[Int]): List[Int] = {
        if (i >= n) primes
        else if (prime(i, primes)) rec(i + 1, i :: primes)
        else rec(i + 1, primes)
      }
      rec(2, List()).reverse
    }

    // first get list of primes smaller than given number
    val primesList = primesUnder(value + 1) // value should be included in list if prime
    println(primesList)
    // RESULT: List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
    // 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
    // 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313)

    // then test division of given number by the primes of the list. start with 2, if modulo == 0, 2 is one of the
    // prime factors and should be added to result list. Continue with 2 and so on.
    // If modulo % 2 != 0, then 2 is NO prime factor. Continue with next Prime Number (2) and so on


      // TODO solution not right
    def getPrimeFactorsOfGivenNumber(list: List[Int], n: Int) : List[Int] = {
      // test division of given number by the primes of the list
      primesList match {
        case head :: Nil => Nil
        case head :: tail =>
          if (n % head == 0) {
            head :: getPrimeFactorsOfGivenNumber(list, n / head)
        } else {
            getPrimeFactorsOfGivenNumber(tail, n)
        }
      }
    }
    println(getPrimeFactorsOfGivenNumber(primesList, value))

  }
}
