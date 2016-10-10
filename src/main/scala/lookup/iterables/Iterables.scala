package lookup.iterables

/**
  * https://www.scala-exercises.org/std_lib/iterables
  */
object Iterables {

  def main(args: Array[String]): Unit = {

    /**
      * The next trait from the top in the collections hierarchy is Iterable. All methods in this trait are
      * defined in terms of an abstract method, iterator, which yields the collection's elements one by
      * one. The foreach method from trait Traversable is implemented in Iterable in terms of iterator.
      * Here is the actual implementation:

      * def foreach[U](f: Elem => U): Unit = { val it = iterator while (it.hasNext) f(it.next()) }

      * Quite a few subclasses of Iterable override this standard implementation of foreach in Iterable,
      * because they can provide a more efficient implementation. Remember that foreach is the basis of the
      * implementation of all operations in Traversable, so its performance matters.

      * Some known iterables are *Sets*, *Lists*, *Vectors*, *Stacks*, and *Streams*. Iterator has two
      * important methods: hasNext, which answers whether the iterator has another element available. next
      * which will return the next element in the iterator.
      */

    val list = List(3, 5, 9, 11, 15, 19, 21)
    val it = list.iterator
    if (it.hasNext) {
      println(it.next())  // iterator.next always starts at first element
      // RESULT: 3
      println(it.next())  // moves iterator one to right
      // RESULT: 5
    }

    /**
      * grouped will return fixed sized Iterable chucks of an Iterable
      */

    val list2 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it2 = list2 grouped 3
    println(it2.next())
    // RESULT: List(3, 5, 9)
    println(it2.next())
    // RESULT: List(11, 15, 19)
    println(it2.next())
    // RESULT: List(21, 24, 32)

    /**
      * sliding will return an Iterable that shows a sliding window of an Iterable
      */

    val list3 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it3 = list3 sliding 3
    println(it3.next())
    // RESULT: List(3, 5, 9)
    println(it3.next())
    // RESULT: List(5, 9, 11)
    println(it3.next())
    // RESULT: List(9, 11, 15)

    /**
      * sliding can take the size of the window as well the size of the step during each iteration
      */

    val list4 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it4 = list4 sliding (3, 3)
    println(it4.next())
    // RESULT: List(3, 5, 9)
    println(it4.next())
    // RESULT: List(11, 15, 19)
    println(it4.next())
    // RESULT: List(21, 24, 32)

    /**
      * takeRight is the opposite of 'take' in Traversable. It retrieves the last elements of an Iterable
      */

    val list5 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    println((list5 takeRight(3)))
    // RESULT: List(21, 24, 32)

    /**
      * dropRight will drop the number of elements from the right
      */

    val list6 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    println((list6 dropRight(3)))
    // RESULT: List(3, 5, 9, 11, 15, 19)

    /**
      * zip will stitch two iterables into an iterable of pairs of corresponding elements from both iterables
      * E.g. Iterable(x1, x2, x3) zip Iterable(y1, y2, y3) will return ((x1,y1), (x2, y2), (x3, y3))
      */

    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann", "Stella")
    println(xs zip ys)
    // RESULT: List((3,Bob), (5,Ann), (9,Stella))

    /**
      * If two Iterables aren't the same size, then zip will only zip what can only be paired.
      * E.g. Iterable(x1, x2, x3) zip Iterable(y1, y2) will return ((x1,y1), (x2, y2))
      */

    val xs1 = List(3, 5, 9)
    val ys1 = List("Bob", "Ann")
    println(xs1 zip ys1)
    // RESULT: List((3,Bob), (5,Ann))

    /**
      * If two Iterables aren't the same size, then zipAll can provide fillers for what it couldn't find
      * a complement for
      * E.g. Iterable(x1, x2, x3) zipAll (Iterable(y1, y2), x, y) will return ((x1,y1), (x2, y2), (x3, y)))
      */

    val xs2 = List(3, 5, 9)
    val ys2 = List("Bob", "Ann")
    println(xs2 zipAll(ys2, -1, "?"))
    // RESULT: List((3,Bob), (5,Ann), (9,?))

    val xt2 = List(3, 5)
    val yt2 = List("Bob", "Ann", "Stella")
    println(xt2 zipAll(yt2, -1, "?"))
    // RESULT: List((3,Bob), (5,Ann), (-1,Stella))

    /**
      * zipWithIndex will zip an Iterable with it's integer index
      */

    val xs3 = List("Manny", "Moe", "Jack")
    println(xs3.zipWithIndex)
    // RESULT: List((Manny,0), (Moe,1), (Jack,2))


    /**
      * sameElements will return true if the two iterables produce the same elements in the same order
      */

    val xs4 = List("Manny", "Moe", "Jack")
    val ys4 = List("Manny", "Moe", "Jack")
    println(xs4.sameElements(ys4))
    // RESULT: true

    val xt4 = List("Manny", "Moe", "Jack")
    val yt4 = List("Manny", "Jack", "Moe")
    println(xt4.sameElements(yt4))
    // RESULT: false

    val xs5 = Set(3, 2, 1, 4, 5, 6, 7)
    val ys5 = Set(7, 2, 1, 4, 5, 6, 3)
    println(xs5.sameElements(ys5))
    // RESULT: true
    // Set orders its elements

    val xt5 = Set(1, 2, 3)
    val yt5 = Set(3, 2, 1)
    println(xt5.sameElements(yt5))
    // RESULT: false
    // When a Set has 4 elements or less, the order is maintained
  }
}
