package ninetynine_problems.working_with_lists

import scala.collection.immutable._

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P36 {

  def main(args: Array[String]): Unit = {

    /**
      * P36: Determine the prime factors of a given positive integer (2).
      * Construct a list containing the prime factors and their multiplicity.
      * 315.primeFactorMultiplicity
      * should result in:
      * List[(Int, Int)] = List((3,2), (5,1), (7,1))
      */

    val value = 315

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

    val primesList = primesUnder(value + 1)

    def getPrimeFactorsOfGivenNumber(list: List[Int], n: Int, finalList: List[Int]): List[Int] = {
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
    val primeFactorsList = getPrimeFactorsOfGivenNumber(primesList, value, Nil)
    // RESULT: List(3, 3, 5, 7)

    /**
      * Solution 1
      * Petra
      * with help of methods "prime", "primesUnder" and "getPrimeFactorsOfGivenNumber" FROM P35
      * and method "makeTuples" from P10
      */

    def makeTuples(list: List[Int]): List[(Int, Int)] = list match {
      case Nil => List() // end of list
      case head :: tail =>
        val (firstTuple, secondTuple) = tail.span(_ == head) // make two tuple lists from span
        (firstTuple.size + 1, head) :: makeTuples(secondTuple) // formatting print, and +1 because head is element, too
    }
    println(makeTuples(primeFactorsList))
    // RESULT: List((2,3), (1,5), (1,7))

    /**
      * Alternately, use a Map for the result.
      * 315.primeFactorMultiplicity
      * should give
      * Map[Int,Int] = Map(3 -> 2, 5 -> 1, 7 -> 1)
      */

    val counts = primeFactorsList.groupBy(w => w).mapValues(_.size)
    println(counts)
    // RESULT: Map(5 -> 1, 7 -> 1, 3 -> 2)
    val countsSorted = ListMap(counts.toSeq.sortBy(_._1):_*)
    println(countsSorted)
    // RESULT: Map(3 -> 2, 5 -> 1, 7 -> 1)

  }
}
