package lookup.tuples

import java.util.Date

// from: https://www.scala-exercises.org/std_lib/tuples
object Tuples {

  def main(args: Array[String]): Unit = {

    /*
    Scala tuple combines a fixed number of items together so that they can be passed around as a whole.
    They are one indexed. Unlike an array or list, a tuple can hold objects with different types but they are also
    immutable. Here is an example of a tuple holding an integer, a string, and the console:
     */

    val t = (1, "hello", Console)
    /*
    Which is syntactic sugar (short cut) for the following
    val t = new Tuple3(1, "hello", Console)
    */

    println(t)
    // RESULT: (1,hello,scala.Console$@6bdf28bb)

    /*
    Tuples can be created easily
    */
    val tuple = ("apple", "dog")
    val fruit = tuple._1
    val animal = tuple._2

    println(fruit)
    // RESULT: apple
    println(animal)
    // RESULT: dog

    /*
    Tuples may be of mixed type
    */
    val tuple5 = ("a", 1, 2.2, new Date(), "five")

    println(tuple5._1)
    // RESULT: a
    println(tuple5._2)
    // RESULT: 1
    println(tuple5._3)
    // RESULT: 2.2
    println(tuple5._4)
    // RESULT: Fri Oct 07 11:23:34 CEST 2016
    println(tuple5._5)
    // RESULT: five

    /*
    You can assign multiple variables at once using tuples
    */
    val student = ("Sean Rogers", 21, 3.5)
    val (name, age, gpa) = student

    println(name)
    // RESULT: Sean Rogers
    println(age)
    // RESULT: 21
    println(gpa)
    // RESULT: 3.5

    /*
    Tuples items can be swapped on a Tuple 2
    */
    val tuple1 = ("apple", 3)
    val tuple2 = tuple1.swap

    println(tuple1)
    // RESULT: (apple,3)
    println(tuple2)
    // RESULT: (3,apple)
  }
}
