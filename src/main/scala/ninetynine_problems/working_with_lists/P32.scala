package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P32 {

  def main(args: Array[String]): Unit = {

    /**
      * Determine the greatest common divisor of two positive integer numbers.
      * Use Euclid's algorithm.
      * gcd(36, 63)
      * should be
      * Int = 9
      */

    /**
      * Helper
      * https://en.wikipedia.org/wiki/Euclidean_algorithm#Procedure
      * Worked example:
      * For illustration, the Euclidean algorithm can be used to find the greatest common divisor of
      * a = 1071 and b = 462. To begin, multiples of 462 are subtracted from 1071 until the remainder is less
      * than 462. Two such multiples can be subtracted (q0 = 2), leaving a remainder of 147:
      * *
      * 1071 = 2 × 462 + 147.
      * *
      * Then multiples of 147 are subtracted from 462 until the remainder is less than 147. Three multiples can be
      * subtracted (q1 = 3), leaving a remainder of 21:
      * *
      * 462 = 3 × 147 + 21.
      * *
      * Then multiples of 21 are subtracted from 147 until the remainder is less than 21. Seven multiples can be
      * subtracted (q2 = 7), leaving no remainder:
      * *
      * 147 = 7 × 21 + 0.
      * *
      * Since the last remainder is zero, the algorithm ends with 21 as the greatest common divisor of 1071 and 462.
      * This agrees with the gcd(1071, 462) found by prime factorization above. In tabular form, the steps are
      * *
      * Step k 	      Equation 	              Quotient and remainder
      * 0 	          1071 = q0 462 + r0 	    q0 = 2 and r0 = 147
      * 1 	          462 = q1 147 + r1 	    q1 = 3 and r1 = 21
      * 2 	          147 = q2 21 + r2 	      q2 = 7 and r2 = 0; algorithm ends
      */

    /**
      * Euclidean_algorithm calculation steps for this example
      * k0    63 = q0 36 + r0     q0 = 1 and r0 = 27
      * k1    36 = q1 27 + r1     q1 = 1 and r1 = 9
      * k2    27 = q2 9 + r2      q2 = 3 and r2 = 0
      * ---> GCD = 9
      */

    val a = 63
    val b = 36

    /**
      * Result 1
      * Petra
      */

    def findGCD(a: Int, b: Int) : Int = {
      val mod = a % b
      if (mod == 0) {
        b
      } else {
        findGCD(b, mod)
      }
    }
    println(findGCD(a, b))
    // RESULT: 9



    /**
      * Solution 2
      * Phil Gold
      * seems to basically be the same as my result, only more beautiful... (Petra)
      */

    def gcd(m: Int, n: Int): Int = if (n == 0) m else gcd(n, m % n)
    println(gcd(a, b))
    // RESULT: 9
  }
}
