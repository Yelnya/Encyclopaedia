package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P34 {

  def main(args: Array[String]): Unit = {

    /**
      * P34: Calculate Euler's totient function phi(m).
      * Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r <= m)
      * that are coprime to m.
      * Example:
      * 10.totient
      * should be
      * Int = 4
      */

    /**
      * Solution 1
      * Petra
      * with help of method "isCoprime" from P33
      */

    val m = 10
    val list = List.range(1, m)

    def isCoprime(a: Int, b: Int): Boolean = {
      val mod = a % b
      // end of calculation loop reached
      if (mod == 0) {
        b == 1 // return true if b == 1, return false if not.
      } else {
        isCoprime(b, mod)
      }
    }

    def loopList(list: List[Int], cnt: Int): Int = {
      list match {
        case Nil => cnt
        // look for each if isComprime
        case head :: tail =>
          if (isCoprime(head, m)) loopList(tail, cnt + 1) else loopList(tail, cnt)
      }
    }

    // loop through all Integers of a list of 1 to m
    println(loopList(list, 0))
    // RESULT: 4

    /**
      * Solution 2
      * Phil Gold
      * with help of "isComprime" from P33
      * BUT does not compile!
      */

//    def totient: Int = (1 to m) filter { m.isCoprime(_) } length
//    println(totient)


  }
}
