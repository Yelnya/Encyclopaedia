package lookup.traversables

import scala.collection.immutable.Stream.cons

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

    /**
      * size provides the size of the traversable
      */

    val map21 = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    println(map21.size)
    // RESULT: 2

    /**
      * hasDefiniteSize will return true if there is traversable that has a finite end, otherwise false
      */

    val map22 = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    println(map22.hasDefiniteSize)
    // RESULT: true

    val stream22 = cons(0, cons(1, Stream.empty))
    println(stream22.hasDefiniteSize)
    // RESULT: false

    /**
      * head will return the first element of an ordered collection, or some random element if order is not
      * defined like in a *Set* or *Map*
      */

    val list23 = List(10, 19, 45, 1, 22)
    println(list23.head)
    // RESULT: 10

    /**
      * headOption will return the first element as an *Option* of an ordered collection, or some random
      * element if order is not defined. If a first element is not available, then *None* is returned
      */

    val list24 = List(10, 19, 45, 1, 22)
    println(list24.headOption)
    // RESULT: Some(10)

    val list25 = List()
    println(list25.headOption)
    // RESULT: None

    /**
      * last will return the last element of an ordered collection, or some random element if order is not
      * defined like in a *Set* or *Map*.
      */

    val list26 = List(10, 19, 45, 1, 22)
    println(list26.last)
    // RESULT: 22

    /**
      * lastOption will return the last element as an *Option* of an ordered collection, or some random
      * element if order is not defined. If a last element is not available, then None is returned
      */

    val list27 = List(10, 19, 45, 1, 22)
    println(list27.lastOption)
    // RESULT: Some(22)

    val list28 = List()
    println(list28.lastOption)
    // RESULT: None

    /**
      * find will locate the first item that matches a predicate p as *Some* or *None* if an element is not found
      */

    val list29 = List(10, 19, 45, 1, 22)
    println(list29.find(_ % 2 != 0))
    // RESULT: Some(19)

    val list30 = List(4, 8, 16)
    println(list30.find(_ % 2 != 0))
    // RESULT: None

    /**
      * tail will return the rest of the collection without the head
      */

    val list31 = List(10, 19, 45, 1, 22)
    println(list31.tail)
    // RESULT: List(19, 45, 1, 22)

    /**
      * init will return the rest of the collection without the last
      */

    val list32 = List(10, 19, 45, 1, 22)
    println(list32.init)
    // RESULT: List(10, 19, 45, 1)

    /**
      * Given a from index, and a to index, slice will return the part of the collection including from, and
      * excluding to
      */

    val list33 = List(10, 19, 45, 1, 22)
    println(list33.slice(1, 3))   // beginning from pos 0!!! including from 1st -> excluding to 3rd
    // RESULT: List(19, 45)

    /**
      * take will return the first number of elements given
      */

    val list34 = List(10, 19, 45, 1, 22)
    println(list34.take(3))   // take the first 3 elements from the list
    // RESULT: List(10, 19, 45)

    /**
      * take is used often with *Streams*, and *Streams* after all are *Traversable*
      */

    def streamer35(v: Int): Stream[Int] = cons(v, streamer35(v + 1))
    val a35 = streamer35(2)   // streamer starts at 2 and increments by one
    println((a35.take(3).toList))   // takes first 3 elements of list
    // RESULT: List(2, 3, 4)

    /**
      * drop will take the rest of the *Traversable* except the number of elements given
      */

    def streamer36(v: Int): Stream[Int] = cons(v, streamer36(v + 1))
    val a36 = streamer36(2)
    println(a36.drop(6).take(3).toList) //drop everything from list except for starting at position 6 taking next 3 elements
    // RESULT: List(8, 9, 10)

    /**
      * takeWhile will continually accumulate elements until a predicate is no longer satisfied
      */

    val list37 = List(87, 44, 5, 4, 200, 10, 39, 100)
    println(list37.takeWhile(_ < 100))  // stops when an element >= 100 is reached
    // RESULT: List(87, 44, 5, 4)

    /**
      * dropWhile will continually drop elements until a predicate is no longer satisfied
      */

    val list38 = List(87, 44, 5, 4, 200, 10, 39, 100)
    println(list38.dropWhile(_ < 100))  // drops ALL elements meeting the condition
    // RESULT: List(200, 10, 39, 100)

    /**
      * filter will take out all elements that don't satisfy a predicate. An *Array* is also *Traversable*
      */

    val array39 = Array(87, 44, 5, 4, 200, 10, 39, 100)
    println(array39.filter(_ < 100))
    // RESULT: [I@13deb50e with elements 87, 44, 5, 4, 10, 39

    /**
      * filterNot will take out all elements that satisfy a predicate. An *Array* is also *Traversable*
      */

    val array40 = Array(87, 44, 5, 4, 200, 10, 39, 100)
    println(array40.filterNot(_ < 100))
    // RESULT: [I@239963d8 with elements List(200, 100)

    /**
      * splitAt will split a *Traversable* at a position, returning a 2 product *Tuple*. splitAt is also
      * defined as (xs take n, xs drop n)
      */

    val array41 = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result41 = array41.splitAt(3)
    println(result41._1.toList)
    // RESULT: Array(87, 44, 5)
    println(result41._2.toList)
    // RESULT: Array(4, 200, 10, 39, 100)

    /**
      * span will split a *Traversable* according to predicate, returning a 2 product *Tuple*. span is also
      * defined as (xs takeWhile p, xs dropWhile p)
      */

    val array42 = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result42 = array42.span(_ < 100)
    println(result42._1)
    // RESULT: Array(87, 44, 5, 4)
    println(result42._2)
    // RESULT: Array(200, 10, 39, 100)

    /**
      * partition will split a *Traversable* according to predicate, returning a
      * 2 product *Tuple*. The left hand side contains the elements satisfied by the predicate whereas the right
      * hand side contains those that don't. *Array* is *Traversable*, partition is also defined
      * as (xs filter p, xs filterNot p)
      */

    val array43 = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result43 = array43.partition(_ < 100)
    println(result43._1.toList)
    // RESULT: Array(87, 44, 5, 4, 10, 39)
    println(result43._2.toList)
    // RESULT: Array(200, 100)

    /**
      * groupBy will categorize a *Traversable* according to a given function, and return a map with the
      * results. This exercise uses *Partial Function* chaining
      */

    val array44 = Array(87, 44, 5, 4, 200, 10, 39, 100)

    val oddAndSmallPartial44: PartialFunction[Int, String] = {
      case x: Int if x % 2 != 0 && x < 100 => "Odd and less than 100"
    }

    val evenAndSmallPartial44: PartialFunction[Int, String] = {
      case x: Int if x != 0 && x % 2 == 0 && x < 100 => "Even and less than 100"
    }

    val negativePartial44: PartialFunction[Int, String] = {
      case x: Int if x < 0 => "Negative Number"
    }

    val largePartial44: PartialFunction[Int, String] = {
      case x: Int if x > 99 => "Large Number"
    }

    val zeroPartial44: PartialFunction[Int, String] = {
      case x: Int if x == 0 => "Zero"
    }

    val result44 = array44.groupBy {
      oddAndSmallPartial44 orElse(
        evenAndSmallPartial44 orElse(
          negativePartial44 orElse(
            largePartial44 orElse(
              zeroPartial44
              )
            )
          )
        )
    }
    println(result44)
    // RESULT: Map(Even and less than 100 -> Array(44, 4, 10), Odd and less than 100 -> Array(87, 5, 39), Large Number -> Array(200, 100))
    println(result44("Even and less than 100").size)
    // RESULT: 3
    println(result44("Large Number").size)
    // RESULT: 2

    /**
      * forall will determine if a predicate is valid for all members of a *Traversable*
      */

    val list45 = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result45 = list45.forall(_ < 100)
    println(result45)
    // RESULT: false

    /**
      * exists will determine if a predicate is valid for some members of a *Traversable*
      */

    val list46 = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result46 = list46 exists(_ < 100)
    println(result46)
    // RESULT: true

    /**
      * count will count the number of elements that satisfy a predicate in a *Traversable*
      */

    val list47 = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result47 = list47.count(_ < 100)
    println(result47)
    // RESULT: 6

    /**
      * /: or foldLeft will combine an operation starting with a seed and combining from the left.
      * *Fold Left* is defined as (seed /: list), where seed is the initial value. Once the fold is
      * established, you provide a function that takes two arguments. The first argument is the running total of
      * the operation, and the second element is the next element of the list.

      * Given a Traversable (x1, x2, x3, x4), an initial value of init, an operation op, foldLeft is
      * defined as: (((init op x1) op x2) op x3) op x4)
      */

    val list48 = List(5, 4, 3, 2, 1)
    val result48 = (0 /: list48) {    // beginning at 0...
      (`running total`, `next element`) => `running total` - `next element` // subtracts each element sequentially
    }
    println(result48)
    // RESULT: -15

    val result49 = list48.foldLeft(0) {
      (`running total`, `next element`) => `running total` - `next element`
    }
    println(result49)
    // RESULT: -15

    val result50 = (0 /: list48)(_ - _)
    println(result50)
    // RESULT: -15

    val result51 = list48.foldLeft(0)(_ - _)
    println(result51)
    // RESULT: -15

    println(((((0-5)-4)-3)-2)-1)
    // RESULT: -15

    /**
      * :\ or foldRight will combine an operation starting with a seed and combining from the right. Fold right is
      * defined as (list :\ seed), where seed is the initial value. Once the fold is established, you provide a
      * function that takes two elements. The first is the next element of the list, and the second element is
      * the running total of the operation.

      * Given a Traversable (x1, x2, x3, x4), an initial value of init, an operation op, foldRight is defined
      * as: x1 op (x2 op (x3 op (x4 op init)))
      */

    val list52 = List(5, 4, 3, 2, 1)
    val result52 = (list52 :\ 0) {
      (`next element`, `running total`) ⇒ `next element` - `running total`
    }
    println(result52)
    // RESULT: 3

    val result53 = list52.foldRight(0) {
      (`next element`, `running total`) ⇒ `next element` - `running total`
    }
    println(result53)
    // RESULT: 3

    val result54 = (list52 :\ 0)(_ - _) //Short hand
    println(result54)
    // RESULT: 3

    val result55 = list52.foldRight(0)(_ - _)
    println(result55)
    // RESULT: 3

    println((5 - (4 - (3 - (2 - (1 - 0))))))
    // RESULT: 3

    /**
      * reduceLeft is the similar to *foldLeft*, except that the seed is the head value
      */

    val intList56 = List(5, 4, 3, 2, 1)
    println(intList56.reduceLeft(_ + _))
    // RESULT: 15

    val stringList56 = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
    println(stringList56.reduceLeft(_ + _))
    // RESULT: DoReMeFaSoLaTeDo

    /**
      * reduceRight is the similar to *foldRight*, except that the seed is the last value
      */

    val intList57 = List(5, 4, 3, 2, 1)
    println(intList57.reduceRight(_ + _))
    // RESULT: 15

    val stringList57 = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
    println(stringList57.reduceRight(_ + _))
    // RESULT: DoReMeFaSoLaTeDo

    /**
      * There are some methods that take much of the folding work out by providing basic functionality. sum
      * will add all the elements, product will multiply, min would determine the smallest element, and max the largest
      */

    val intList58 = List(5, 4, 3, 2, 1)
    println(intList58.sum)
    // RESULT: 15
    println(intList58.product)
    // RESULT: 120
    println(intList58.max)
    // RESULT: 5
    println(intList58.min)
    // RESULT: 1

    /**
      * You would choose *foldLeft/reduceLeft* or *foldRight/reduceRight* based on your mathematical goal. One
      * other reason for deciding is performance. foldLeft is more performant since it uses tail recursion and is
      * optimized. This exercise will either work or you will receive a *StackOverflowError*
      */

    val MAX_SIZE = 1000000
    val reduceLeftStartTime = new java.util.Date
    (1 to MAX_SIZE) reduceLeft (_ + _)
    val reduceLeftEndTime = new java.util.Date

    val reduceRightStartTime = new java.util.Date
    (1 to MAX_SIZE) reduceRight (_ + _)
    val reduceRightEndTime = new java.util.Date

    val totalReduceLeftTime = reduceLeftEndTime.getTime - reduceLeftStartTime.getTime
    val totalReduceRightTime = reduceRightEndTime.getTime - reduceRightStartTime.getTime

    println(totalReduceRightTime > totalReduceLeftTime)
    // RESULT: true

    /**
      * transpose will take a traversable of traversables and group them by their position in it's own traversable.
      * E.g.: ((x1, x2),(y1, y2)).transpose = (x1, y1), (x2, y2) or ((x1, x2, x3),(y1, y2, y3),(z1, z2, z3)).transpose = ((x1, y1, z1), (x2, y2, z2), (x3, y3, z3))
      */

    val list59 = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    println(list59.transpose)
    // RESULT: List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9))

    val list60 = List(List(1), List(4))
    println(list60.transpose)
    // RESULT: List(List(1, 4))

    /**
      * mkString will format a *Traversable* using a given string as the delimiter.
      */

    val list61 = List(1, 2, 3, 4, 5)
    println(list61.mkString(","))
    // RESULT: 1,2,3,4,5

    /**
      * mkString will also take a beginning and ending string to surround the list
      */

    val list62 = List(1, 2, 3, 4, 5)
    println(list62.mkString(">", ",", "<"))
    // RESULT: >1,2,3,4,5<

    /**
      * addString will take a StringBuilder to add the contents of list into the builder
      */

    val stringBuilder = new StringBuilder()
    val list63 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    stringBuilder.append("I want all numbers 6-12: ")
    list63.filter(it => it > 5 && it < 13).addString(stringBuilder, ",")
    println(stringBuilder.mkString)




  }
}
