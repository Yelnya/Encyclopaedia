package lookup.traversables

/**
  * from: https://www.scala-exercises.org/std_lib/traversables
  */
object Traversables {

  def main(args: Array[String]): Unit = {

    /**
      * At the top of the collection hierarchy is trait *Traversable*. Its only abstract operation is foreach
      * def foreach[U](f: Elem => U)
      *
      * Collection classes that implement *Traversable* just need to define this method; all other methods can be
      * inherited from *Traversable*.

      * The foreach method is meant to traverse all elements of the collection, and apply the given operation,
      * f, to each element. The type of the operation is Elem => U, where Elem is the type of the collection's
      * elements and U is an arbitrary result type. The invocation of f is done for its side effect only; in
      * fact any function result of f is discarded by foreach.

      * Traversables are the superclass of *Lists*, *Arrays*, *Maps*, *Sets*, *Streams*, and more. The methods
      * involved can be applied to each other in a different type. ++ appends two Traversables together
      */

    val set = Set(1, 9, 10 ,22)
    val list = List(3, 4, 5, 10)
    val result = set ++ list
    println(result.size)
    // RESULT: 7

    val result1 = list ++ set
    println(result1.size)
    // RESULT: 8

    /**
      * map will apply the given function on all elements of a *Traversable* and return a new collection of the result
      */

    val set2 = Set(1, 3, 4, 6)
    val result2 = set2.map(_ * 4)
    println(result2.lastOption)
    // RESULT: Some(24)   // 6 * 4

    /**
      * flatten will smash all child *Traversables* within a *Traversable
      */

    val list3 = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    println(list3.flatten)
    // RESULT: List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    /**
      * flatMap will not only apply the given function on all elements of a *Traversable*, but all elements
      * within the elements and flatten the results
      */

    val list4 = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    val result4 = list4.flatMap(_.map(_ * 4))
    println(result4)
    // RESULT: List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40)

    /**
      * flatMap of Options will filter out all Nones and Keep the Somes
      */

    val list5 = List(1, 2, 3, 4, 5)
    val result5 = list5.flatMap{
      it => if (it % 2 == 0) {Some(it)} else None
    }
    println(result5)
    // RESULT: List(2, 4)

    /**
      * collect will apply a partial function to all elements of a *Traversable* and will return a different
      * collection. In this exercise, a case fragment is a partial function
      */

    val list6 = List(4, 6, 7, 8, 9, 13, 14)
    val result6 = list6.collect {
      case x: Int
        if (x % 2 == 0) => x * 3
    }
    println(result6)
    // RESULT: List(12, 18, 24, 42)

    /**
      * collect will apply a partial function to all elements of a *Traversable* and will return a different
      * collection. In this exercise, two case fragments are chained to create a more robust result
      */

    val list7 = List(4, 6, 7, 8, 9, 13, 14)
    val partialFunction1: PartialFunction[Int, Int] = {
      case x: Int if x % 2 == 0 => x * 3
    }
    val partialFunction2: PartialFunction[Int, Int] = {
      case y: Int if y % 2 != 0 => y * 4
    }
    val result7 = list7.collect(partialFunction1 orElse partialFunction2)
    println(result7)
    // RESULT: List(12, 12, 20, 30)

    /**
      * foreach will apply a function to all elements of a *Traversable*, but unlike the map function, it
      * will not return anything since the return type is Unit, which is like a void return type in *Java*, *C++*
      */

    val list8 = List(4, 6, 7, 8, 9, 13, 14)
    list8.foreach(num => println(num * 4))
    println(list8)
    // RESULT: List(4, 6, 7, 8, 9, 13, 14)

    /**
      * toArray will convert any *Traversable* to an Array, which is a special wrapper around a primitive *Java* array
      */

    val set9 = Set(4, 6, 7, 8, 9, 13, 14)
    val result9 = set9.toArray
    println(result9)
    // RESULT: [I@5cc7c2a6
    println(result9.isInstanceOf[Array[Int]])

    /**
      * toList will convert any *Traversable* to a List
      */

    val set10 = Set(4, 6, 7, 8, 9, 13, 14)
    val result10 = set10.toList
    println(result10.isInstanceOf[List[_]])
    // RESULT: true

    /**
      * toList, as well as other conversion methods like toSet, toArray, will not convert if the collection
      * type is the same
      */

    val list11 = List(5, 6, 7, 8, 9)
    val result11 = list11.toList
    println(result11.eq(list11))
    // RESULT: true

    /**
      * toIterable will convert any *Traversable* to an *Iterable*. This is a base *trait* for all Scala
      * collections that define an iterator method to step through one-by-one the collection's elements
      */

    val set12 = Set(4, 6, 7, 8, 9, 13, 14)
    val result12 = set12.toIterable
    println(result12.isInstanceOf[Iterable[_]])
    // RESULT: true

    /**
      * toSeq will convert any *Traversable* to a *Seq* which is an ordered Iterable and is the superclass
      * to *List*, *Queues*, and *Vectors*. *Sequences* provide a method apply for indexing. Indices range
      * from 0 up the length of a sequence
      */

    val set13 = Set(4, 6, 7, 8, 9, 13, 14)
    val result13 = set13.toSeq
    println(result13.isInstanceOf[Seq[_]])
    // RESULT: true

    /**
      * toIndexedSeq will convert any *Traversable* to an *IndexedSeq* which is an indexed sequence used
      * in *Vectors* and *Strings*
      */

    val set14 = Set(4, 6, 7, 8, 9, 13, 14)
    val result14 = set14.toIndexedSeq
    println(result14.isInstanceOf[IndexedSeq[_]])
    // RESULT: true

    /**
      * toStream will convert any *Traversable* to a Stream which is a lazy list where elements are evaluated
      * as they are needed
      */

    val list15 = List(4, 6, 7, 8, 9, 13, 14)
    val result15 = list15.toStream
    println(result15.isInstanceOf[Stream[_]])
    // RESULT: true
    println(result15.take(3))
    // RESULT: Stream(4, ?)
    // RESULT for exercise: Stream(4, 6, 7)

    /**
      * toSet will convert any *Traversable* to a *Set* which is a collection of unordered, unique values
      */

    val list16 = List(4, 6, 7, 8, 9, 13, 14)
    val result16 = list16.toSet
    println(result16.isInstanceOf[Set[_]])
    // RESULT: true

    /**
      * toMap will convert any *Traversable* to a *Map*. How it's used depends on the original collection; if
      * it's a *List* or *Seq*, it should be of parameterized type *Tuple2*
      */

    val list17 = List("Phoenix" → "Arizona", "Austin" → "Texas")
    val result17 = list17.toMap
    println(result17.isInstanceOf[Map[_,_]])
    // RESULT: true

    /**
      * toMap will convert a *Set* to a *Map*, it should be of parameterized type *Tuple2*
      */

    val set18 = Set("Phoenix" → "Arizona", "Austin" → "Texas")
    val result18 = set18.toMap
    println(result18.isInstanceOf[Map[_,_]])
    // RESULT: true

    /**
      * isEmpty is pretty self evident
      */

    val map19 = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    println(map19.isEmpty)
    // RESULT: false
    val set19 = Set()
    println(set19.isEmpty)
    // RESULT: true

    /**
      * nonEmpty is pretty self evident too
      */

    val map20 = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    println(map20.nonEmpty)
    // RESULT: true
    val set20 = Set()
    println(set20.nonEmpty)
    // RESULT: false

    


  }
}
