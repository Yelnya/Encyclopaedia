package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P33 {

  def main(args: Array[String]): Unit = {

    /**
      * P33: Determine whether two positive integer numbers are coprime.
      * Two numbers are coprime if their greatest common divisor equals 1.
      *  35.isCoprimeTo(64)
      * should be
      * Boolean = true
      */

    /**
      * Solution 1
      * Petra
      * with help of method "findGDC" from P32
      */

    //    val a = 63
    //    val b = 36
    val a = 64
    val b = 35

    /**
      * Result 1
      * Petra
      */

    def isCoprime(a: Int, b: Int): Boolean = {
      val mod = a % b
      if (mod == 0) { // end of calculation loop reached
        b == 1 // return true if b == 1, return false if not.
      } else {
        isCoprime(b, mod)
      }
    }
    println(isCoprime(a, b))
    // RESULT: true


    /**
      * Solution 2
      * Phil Gold
      * with help of method "gdc" from P32
      */

    def gcd(m: Int, n: Int): Int = if (n == 0) m else gcd(n, m % n)

    def isCoprimeTo(a: Int, b: Int): Boolean = gcd(a, b) == 1

    println(isCoprimeTo(a, b))
    // RESULT: true

  }
}
