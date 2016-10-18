package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P31 {

  def main(args: Array[String]): Unit = {

    /**
      * P31: Determine whether a given integer number is prime.
      * given value = 7
      * should
      * return Boolean
      */

    val value = 7

    /**
      * Solution 1
      * Petra
      * wrote mathematical algorithm to determine a prime number into working code
      */

    def checkPrime(value: Int): Boolean = {
      if (value <= 1) {
        // everything below 2 is no prime (according to definition)
        false
      } else if (value <= 3) {
        // 2 and 3 are primes!
        true
      } else if (value % 2 == 0) {
        // even values are never primes!
        false
      } else {
        var i = 5 // starting at 5 ...
        while (i * i <= value) {
          // ... continuing as long as square root is below value ...
          if ((value % i == 0) || value % (i + 2) == 0) {
            // ... testing modulo's for NOT-prime validation
            return false
          }
          i = i + 6 // if nothing has been found, enhance by one and test again
        }
        true // if nothing has been found, it IS a prime!
      }
    }
    println(checkPrime(value))


    /**
      * Solution 2
      * Petra
      * checking value against a list of primes: first getting a list of primes including value, then checking if
      * value is contained in list
      */

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

    val primesList = primesUnder(value + 1) // value should be included in list if prime

    println(primesList)
    // RESULT: List(2, 3, 5, 7)

    println(primesList.contains(value))
    // RESULT: true
  }

  /**
    * Solution 3
    * Phil Gold
    * BUT cannot compile (?)
    */

//  class S99Int(val start: Int) {
//    def isPrime: Boolean =
//      (start > 1) && (primes takeWhile { _ <= Math.sqrt(start) } forall { start % _ != 0 })
//  }
//
//  object S99Int {
//    val primes = Stream.cons(2, Stream.from(3, 2) filter { _.isPrime })
//  }

}


