package lookup.lists

// from: https://www.scala-exercises.org/std_lib/lists
object Lists {

  def main(args: Array[String]): Unit = {

    /*
    99 exercises
    */
    val defaultList = List(1, 1, 2, 3, 5, 8)
    println("Get last element of a list:")
    println(defaultList.last)
    // RESULT: 8

    val a = List(1, 2, 3)
    val b = List(1, 2, 3)

    val flagEquals : Boolean = a == b
    println(flagEquals)
    // RESULT: true

    val flagIsSame : Boolean = a eq(b)
    println(flagIsSame)
    // RESULT: false

    /*
    Nil lists are identical, even of different types
    */
    val c: List[String] = Nil
    val d: List[Int] = Nil

    println(c eq(d))
    // RESULT: true

    /*
    Lists are easily created
    */
    val f = List(1, 2, 3)

    /*
    Lists can be accessed via head, headOption and tail. Accessing List via head is unsafe and may result in a
    IndexOutOfBoundsException
    */

    println(f.head)
    // RESULT: 1
    println(f.headOption)
    // RESULT: Some(1)
    println(f.headOption.get)
    // RESULT: 1
    println(f.tail)
    // RESULT: List(2, 3)
    println(f(0))
    // RESULT: 1
    println(f(1))
    // RESULT: 2
    println(f(2))
    // RESULT: 3

    /*
    Lists are immutable
    */

    val g = List(1, 3, 5, 7, 9)
    val h = g.filterNot(v => v == 5) // remove where value is 5

    println(h)
    // RESULT: List(1, 3, 7, 9)

    /*
    Lists have many useful methods
    */

    println(h.length)                    // get the length of the list
    // RESULT: 4
    println(h.reverse)                   // reverse the list
    // RESULT: List(9, 7, 3, 1)
    println(h.map(v => v * 2))           // map a function to double the numbers over the list
    // RESULT: List(2, 6, 14, 18)
    println(h.filter{ v => v % 3 == 0 }) // filter any values divisible by 3 in the list
    // RESULT: List(3, 9)

    /*
    Functions over lists can use _ as shorthand
    */

    println(g.map { _ * 2 })
    // RESULT: List(2, 6, 10, 14, 18)

    /*
    Lists can be *reduced* with a mathematical operation
    */

    println(g.reduceLeft(_ + _))
    // RESULT: 25
    println(g.reduceLeft(_ * _))
    // RESULT: 945

    /*
    Foldleft is like reduce, but with an explicit starting value, NOTE: foldLeft uses a form called currying
    */

    println(g.foldLeft(0)(_ + _))  // 0 is default
    // RESULT: 25

    /*
    You can create a list from a range
    */
    val listFromRange = (1 to 5).toList
    println(listFromRange)
    // RESULT: List(1, 2, 3, 4, 5)

    /*
    Lists reuse their tails
    */
    val d1 = Nil
    val c1 = 3 :: d1
    val b1 = 2 :: c1
    val a1 = 1 :: b1

    println(a1)
    // RESULT: List(1, 2, 3)

  }
}
