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

    def takeAllCombinationsOfFour[A](n: Int, ls: List[A]): List[List[A]] = {
      if (n == 0) List(Nil)
      else flatMapSublists(ls) { sl =>
        takeAllCombinationsOfFour(n - 1, sl.tail) map {
          sl.head :: _
        }
      }
    }

    def takeAllCombinationsOfThree[A](n: Int, ls: List[A]): List[List[A]] = {
      if (n == 0) List(Nil)
      else flatMapSublists(ls) { sl =>
        takeAllCombinationsOfThree(n - 1, sl.tail) map {
          sl.head :: _
        }
      }
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
    // SOLUTION: List(List(Aldo, Beat), List(Aldo, Carla), List(Aldo, David), List(Aldo, Evi), List(Aldo, Flip),
    // List(Aldo, Gary), List(Aldo, Hugo), List(Aldo, Ida), List(Beat, Carla), List(Beat, David), List(Beat, Evi),
    // List(Beat, Flip), List(Beat, Gary), List(Beat, Hugo), List(Beat, Ida), List(Carla, David), List(Carla, Evi),
    // List(Carla, Flip), List(Carla, Gary), List(Carla, Hugo), List(Carla, Ida), List(David, Evi), List(David, Flip),
    // List(David, Gary), List(David, Hugo), List(David, Ida), List(Evi, Flip), List(Evi, Gary), List(Evi, Hugo),
    // List(Evi, Ida), List(Flip, Gary), List(Flip, Hugo), List(Flip, Ida), List(Gary, Hugo), List(Gary, Ida),
    // List(Hugo, Ida))


    /**
      * Solution 2
      * Phil Gold
      * with help of method "ListCombinations" from P26
      * BUT cannot compile
      */

//    def listCombinations[A](n: Int, ls: List[A]): List[List[A]] =
//    if (n == 0) List(Nil)
//    else flatMapSublists(ls) { sl =>
//      listCombinations(n - 1, sl.tail) map {
//        sl.head :: _
//      }
//    }
//
//    def group3[A](ls: List[A]): List[List[List[A]]] =
//      for {
//        a <- listCombinations(2, ls)
//        noA = ls -- a
//        b <- listCombinations(3, noA)
//      } yield List(a, b, noA -- b)
//


  /**
    * P27: Group the elements of a set into disjoint subsets.
    * b) Generalize the above predicate in a way that we can specify a list of group sizes and the predicate
    * will return a list of groups.
    * group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
    * should look like:
    * List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David), List(Evi, Flip, Gary, Hugo, Ida)), ...
    */

    // TODO find solution


    /**
      * Solution 2
      * Phil Gold
      */

    //    def group[A](ns: List[Int], ls: List[A]): List[List[List[A]]] = ns match {
    //      case Nil     => List(Nil)
    //      case n :: ns => listCombinations(n, ls) flatMap { c =>
    //        group(ns, ls -- c) map {c :: _}
    //      }
    //    }


  }
}
