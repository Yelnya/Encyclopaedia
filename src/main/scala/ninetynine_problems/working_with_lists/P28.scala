package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P28 {

  def main(args: Array[String]): Unit = {

    /**
      * P28: Sorting a list of lists according to length of sublists.
      * a) We suppose that a list contains elements that are lists themselves. The objective is to sort the
      * elements of the list according to their length. E.g. short lists first, longer lists later, or vice versa.
      * lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
      * should look like:
      * List[List[Symbol]] = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))
      */

    val list: List[List[Symbol]] = List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))

    /**
      * Solution 1
      * Petra
      */

    def sortListsAccordingToLength(list: List[List[Symbol]]): List[List[Symbol]] = {
      list.sortWith(_.length <= _.length)
    }
    println(sortListsAccordingToLength(list))
    // RESULT: List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))

    /**
      * Solution 2
      * Phil Gold
      * using methods "encode" and "pack" from P10
      * BUT cannot compile!
      */

    def pack[A](ls: List[A]): List[List[A]] = {
      if (ls.isEmpty) List(List())
      else {
        val (packed, next) = ls span {
          _ == ls.head
        }
        if (next == Nil) List(packed)
        else packed :: pack(next)
      }
    }

    def encode[A](ls: List[A]): List[(Int, A)] =
      pack(ls) map {
        e => (e.length, e.head)
      }
    //
    //    def lsort[A](ls: List[List[A]]): List[List[A]] =
    //      sort { _.length < _.length }
    //    println(lsort(list))


    /**
      * P28: Sorting a list of lists according to length of sublists.
      * b) Again, we suppose that a list contains elements that are lists themselves. But this time the objective
      * is to sort the elements according to their length frequency; i.e. in the default, sorting is done
      * ascendingly, lists with rare lengths are placed, others with a more frequent length come later.
      * lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
      * should look like:
      * List[List[Symbol]] = List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))
      * Note that in the above example, the first two lists in the result have length 4 and 1 and both lengths
      * appear just once. The third and fourth lists have length 3 and there are two list of this length. Finally,
      * the last three lists have length 2. This is the most frequent length.
      */

    /**
      * Solution 1
      * Petra
      * with help of Google
      */

    def sortListOfListsAccordingToLength(list: List[List[List[Symbol]]]): List[List[List[Symbol]]] = {
      list.sortWith(_.length <= _.length)
    }
    def sortListsAccordingToFrequency(list: List[List[Symbol]]): List[List[Symbol]] = {
      sortListOfListsAccordingToLength(list.groupBy(_.size).values.toList).flatten
    }

    println(sortListsAccordingToFrequency(list))
    // RESULT: List(List('o), List('i, 'j, 'k, 'l), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))


    //SAME, BUT SHORTER:
    def sortListsAccordingToFrequency2(list: List[List[Symbol]]): List[List[Symbol]] = {
      list.groupBy(_.size).values.toList.sortWith(_.length <= _.length).flatten
    }

    println(sortListsAccordingToFrequency2(list))
    // RESULT: List(List('o), List('i, 'j, 'k, 'l), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))

    /**
      * Solution 2
      * Phil Gold
      * with help of methods "encode" and "pack"
      * BUT cannot compile!
      */

    //    def lsortFreq[A](ls: List[List[A]]): List[List[A]] = {
    //      val freqs = Map(encode(ls map { _.length } sort { _ < _ }) map { _.swap }:_*)
    //      ls sort { (e1, e2) => freqs(e1.length) < freqs(e2.length) }
    //    }

  }
}
