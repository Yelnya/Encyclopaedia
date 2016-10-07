package lookup.options

// from: https://www.scala-exercises.org/std_lib/options
object Options {

  def main(args: Array[String]): Unit = {

    /*
    If you have worked with Java at all in the past, it is very likely that you have come across a NullPointerException
    at some time (other languages will throw similarly named errors in such a case). Usually this happens because some
    method returns null when you were not expecting it and thus not dealing with that possibility in your client code.
    A value of null is often abused to represent an absent optional value.

    Scala tries to solve the problem by getting rid of null values altogether and providing its own type for representing
    optional values, i.e. values that may be present or not: the Option[A] trait.

    Option[A] is a container for an optional value of type A. If the value of type A is present, the Option[A] is an
    instance of Some[A], containing the present value of type A. If the value is absent, the Option[A] is the object None.
     */

    val someValue: Option[String] = Some("I am wrapped in something")
    println(someValue)
    // RESULT: Some(I am wrapped in something)

    val emptyValue: Option[String] = None
    println(emptyValue)
    // RESULT: None

    /*
    Let's write a function that may or not give us a string, thus returning Option[String]
    */

    def maybeItWillReturnSomething (flag: Boolean): Option[String] = {
      if (flag) {
        Some("Found Value")
      } else {
        None
      }
    }
    println(maybeItWillReturnSomething(true))
    // RESULT: Some(Found Value)
    println(maybeItWillReturnSomething(false))
    // RESULT: None

    /*
    Using getOrElse we can provide a default value ("No value") when the optional argument (None) does not exist.
    */

    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)

    println(value1 getOrElse ("NoValue"))
    // RESULT: Found Value
    println(value2 getOrElse("NoValue"))
    // RESULT: NoValue
    println(value2 getOrElse("default function"))
    // RESULT: default function

    /*
    Checking whether option has value:
    */

    println(value1.isEmpty)
    // RESULT: false
    println(value2.isEmpty)
    // RESULT: true

    /*
    Option can also be used with pattern matching:
    */

    val doubleValue: Option[Double] = Some(20.0)
    val doubleValueMatch = doubleValue match {
      case Some(v) => v
      case None => 0.0
    }
    println(doubleValueMatch)
    // RESULT: 20.0

    val noValue: Option[Double] = None
    val doubleValueMatch2 = noValue match {
      case Some(v) => v
      case None => 0.0
    }
    println(doubleValueMatch2)
    // RESULT: 0.0

    /*
    An alternative for pattern matching is performing collection style operations. This is possible because an option
    could be looked at as a collection with either one or zero elements.

    One of these operations is map. this operation allows to map the inner value to a different type while preserving the option
    */

    val number: Option[Int] = Some(3)
    val noNumber: Option[Int] = None
    val result1_1 = number.map(_ * 1.5)
    val result1_2 = noNumber.map(_ * 1.5)

    println(result1_1)
    // RESULT: Some(4.5)
    println(result1_2)
    // RESULT: None

    /*
    Another operation is fold. this operation will extract the value from the option, or provide a default if the value is None
     */

    val result2_1 = number.fold(0)(_ * 3)
    val result2_2 = noNumber.fold(0)(_ * 3) //0 is default value when value is None

    println(result2_1)
    // RESULT: 9
    println(result2_2)
    // RESULT: 0
  }
}
