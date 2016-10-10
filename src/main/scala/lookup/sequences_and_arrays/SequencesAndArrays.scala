package lookup.sequences_and_arrays

/**
  * from: https://www.scala-exercises.org/std_lib/sequences_and_arrays
  */
object SequencesAndArrays {

  def main(args: Array[String]): Unit = {

    /**
      * Scala provides a data structure, the array, which stores a fixed-size sequential collection of
      * elements of the same type. An array is used to store a collection of data, but it is often more useful
      * to think of an array as a collection of variables of the same type.

      * A list can be converted to an array
      */

    val l = List(1, 2, 3)
    val a = l.toArray
    println(a)
    // RESULT: [I@1cd072a9

    /**
      * Sequences are special cases of iterable collections of class Iterable. Unlike iterables,
      * sequences always have a defined order of elements
      * Any sequence can be converted to a list
      */

    val a1 = Array(1, 2, 3)
    val s1 = a1.toSeq
    val l1 = s1.toList
    println(a1)
    // RESULT: [I@7c30a502
    println(s1)
    // RESULT: WrappedArray(1, 2, 3)
    println(l1)
    // RESULT: List(1, 2, 3)

    /**
      * You can create a sequence from a for comprehension
      */

    val s2 = for {
      v <- 1 to 4
    } yield v
    println(s2.toList)
    // RESULT: List(1, 2, 3, 4)

    /**
      * You can create a sequence from a for comprehension with a condition
      */

    val s3 = for {
      v <- 1 to 10
      if (v % 3 == 0)
    } yield v
    println(s3.toList)
    // RESULT: List(3, 6, 9)

    /**
      * You can filter any sequence based on a predicate
      */

    val s4 = Seq("hello", "to", "you")
    val filtered = s4.filter(_.length > 2)
    println(filtered)
    // RESULT: List(hello, you)

    /**
      * You can also filter Arrays in the same way
      */

    val a5 = Array("hello", "to", "you", "again")
    val filtered2 = a5.filter(_.length > 3)
    println(filtered2(0) + ", " + filtered2(1))
    // RESULT: hello, again

    /**
      * You can map values in a sequence through a function
      */

    val s6 = Seq("hello", "world")
    val r6 = s6.map {
      _.reverse
    }
    println(r6)
    // RESULT: List(olleh, dlrow)

  }
}
