package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P27 {

  def main(args: Array[String]): Unit = {

    /**
      * P27: Group the elements of a set into disjoint subsets.
      * a) In how many ways can a group of 9 people work in 3 disjoint subgroups of 2, 3 and 4 persons? Write a
      * function that generates all the possibilities.
      * group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
      * should look like:
      * List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David, Evi), List(Flip, Gary, Hugo, Ida)), ...
      */


    val list = List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida")

    /**
      * Helpers
      */

    // TODO find solution!!

    /**
      * All Combinations of Two
      */
    val combinationElements = 2

    def flatMapSublists[A, B](ls: List[A])(f: (List[A]) => List[B]): List[B] =
      ls match {
        case Nil => Nil
        case sublist@(_ :: tail) => f(sublist) ::: flatMapSublists(tail)(f)
      }

    def takeAllCombinationsOfTwo[A](n: Int, ls: List[A]): List[List[A]] = {
      if (n == 0) List(Nil)
      else flatMapSublists(ls) { sl =>
        takeAllCombinationsOfTwo(n - 1, sl.tail) map {
          sl.head :: _
        }
      }
    }
    println(takeAllCombinationsOfTwo(combinationElements, list))
  }

}
