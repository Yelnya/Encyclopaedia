package lookup.higher_order_functions

// from: https://www.scala-exercises.org/std_lib/higher_order_functions
object HigherOrderFunctions {

  def main(args: Array[String]): Unit = {

    /*
    Meet lambda. Scala provides a relatively lightweight syntax for defining anonymous functions. Anonymous functions
    in source code are called function literals and at run time, function literals are instantiated into objects called
    function values.

    Scala supports first-class functions, which means you can express functions in function literal syntax, i.e.,
    (x: Int) => x + 1, and that functions can be represented by objects, which are called function values.
     */

    def lambda = { x: Int => x + 1 }
    def lambda2 = (x: Int) => x + 1
    def lambda3 = (x: Int) => x + 1
    val lambda4 = new Function[Int, Int] {
      def apply(v1: Int): Int = v1 + 1
    }
    def lambda5 (x: Int) = x + 1

    println(lambda(3))
    // RESULT: 4
    println(lambda2(3))
    // RESULT: 4
    println(lambda3(3))
    // RESULT: 4
    println(lambda4(3))
    // RESULT: 4
    println(lambda5(3))
    // RESULT: 4

    /*
    An anonymous function can also take on a different look by taking out the brackets
    */
    def lambda6 = (x: Int) => x + 1
    println(lambda6(5))
    // RESULT: 6

    /*
    Here the only variable used in the function body, i * 10, is i, which is defined as a parameter to the function
    */
    val multiplier = (i: Int) => i * 10
    println(multiplier(5))
    // RESULT: 50

    var incrementer = 1
    def closure = {
      x: Int => x + incrementer
    }

    println(closure(1))
    // RESULT: 2

    /*
    We can take that closure and throw into a method and it will still hold the environment
    */

    //TODO: nicht verstanden!
    def summation(x: Int, y: Int => Int) = y(x)
    incrementer = 3
    def closure2 = (x: Int) => x + incrementer

    println(summation(10, closure2))
    // RESULT: 13

    /*
    Function returning another function
    */

    def addWithoutSyntaxSugar(x: Int) = {
      new Function1[Int, Int]() {
        def apply(y: Int): Int = x + y
      }
    }

    println(addWithoutSyntaxSugar(1))
    // RESULT: <function1>
    println(addWithoutSyntaxSugar(2)(3))
    // RESULT: 5

    def fiveAdder = addWithoutSyntaxSugar(5)
    println(fiveAdder(5))
    // RESULT: 10

    /*
    Function returning another function using an anonymous function
    */

    def addWithSyntaxSugar(x: Int) = (y: Int) => x + y

    println(addWithSyntaxSugar(3))
    // RESULT: <function1>
    println(addWithSyntaxSugar(3)(2))
    // RESULT: 5

    /*
    Function taking another function as parameter. Helps in composing functions.
    Hint: a map method applies the function to each element of a list
    */

    def makeUpper(xs: List[String]) = xs map {
      _.toUpperCase
    }

    def makeWhatEverYouLike(xs: List[String], sideEffect: String ⇒ String) = {
      xs map sideEffect
    }

    println(makeUpper(List("abc", "xyz", "123")))
    // RESULT: List(ABC, XYZ, 123)
    println(makeWhatEverYouLike(List("ABC", "XYZ", "123"), x => x.toLowerCase))
    // RESULT: List(abc, xyz, 123)

    /*
    using it inline
    */

    println(List("Scala", "Erlang", "Clojure") map {
      _.length
    })
    // RESULT: List(5, 6, 7)
  }
}
