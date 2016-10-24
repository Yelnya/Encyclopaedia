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

    // first get list of primes smaller/equal than/to given number
    val primesList = primesUnder(value + 1) // value should be included in list if prime

    /**
      * Explanation: test division of given number by the primes of primesList. start with 2, if modulo == 0, 2 is one
      * of the prime factors and should be added to restult list. Continue with 2 and so on.
      * If modulo % 2 != 0, then 2 is NO prime factor. Continue with next Prime Number (3) and so on
      */

    def getPrimeFactorsOfGivenNumber(list: List[Int], n: Int, finalList: List[Int]): List[Any] = {
      // test division of given number by the primes of the list
      list match {
        case head :: Nil => finalList
        case head :: tail =>
          if (n % head == 0) {
            if (n == 1) finalList else getPrimeFactorsOfGivenNumber(list, n / head, finalList :+ head)
          } else {
            getPrimeFactorsOfGivenNumber(tail, n, finalList)
          }
      }
    }
    println(getPrimeFactorsOfGivenNumber(primesList, value, Nil))
    // RESULT: List(3, 3, 5, 7)


    /**
      * Solution 2
      * Phil Gold
      * with help of "primes" and method "isPrime" from P31
      * BUT cannot compile!
      */

    //    def isPrime: Boolean =
    //    (start > 1) && (primes takeWhile {
    //      _ <= Math.sqrt(start)
    //    } forall {
    //      start % _ != 0
    //    })
    //  }
    //
    //    val primes = Stream.cons(2, Stream.from(3, 2) filter {
    //      _.isPrime
    //    })
    //
    //  def primeFactors: List[Int] = {
    //    def primeFactorsR(n: Int, ps: Stream[Int]): List[Int] =
    //      if (n.isPrime) List(n)
    //      else if (n % ps.head == 0) ps.head :: primeFactorsR(n / ps.head, ps)
    //      else primeFactorsR(n, ps.tail)
    //    primeFactorsR(start, primes)
    //  }
  }
}
