package lookup.partially_applied_functions

/**
  * from: https://www.scala-exercises.org/std_lib/partially_applied_functions
  */
object PartiallyAppliedFunctions {

  def main(args: Array[String]): Unit = {

    /**
      * A partially applied function is a function that you do not apply any or all the arguments,
      * creating another function. This partially applied function doesn't apply any arguments.
      */

    def sum(a: Int, b: Int, c: Int) = a + b + c
    val sum3 = sum _
    println(sum3(1, 9, 7))
    // RESULT: 17
    println(sum(4, 5, 6))
    // RESULT: 15


    /**
      * Partially applied functions can replace any number of arguments
      */

    val sumC = sum(1, 10, _: Int)
    println(sumC(4))
    // RESULT: 15
    println(sum(4, 5, 6))
    // RESULT: 15

    /**
      * Currying is a technique to transform function with multiple parameters into multiple functions
      * which each take one parameter
      */

    def multiply(x: Int, y: Int) = x * y
    println((multiply _).isInstanceOf[Function2[_, _, _]])
    // RESULT: true
    val multiplyCurried = (multiply _).curried
    println(multiply(4, 5))
    // RESULT: 20
    println(multiplyCurried(3)(2))
    // RESULT: 6
    val multiplyCurriedFour = multiplyCurried(4)
    println(multiplyCurriedFour(2))
    // RESULT: 8
    println(multiplyCurriedFour(4))
    // RESULT: 16

    /**
      * Currying allows you to create specialized version of generalized function
      */

    def customFilter(f: Int ⇒ Boolean)(xs: List[Int]) = {
      xs filter f
    }
    def onlyEven(x: Int) = x % 2 == 0
    val xs = List(12, 11, 5, 20, 3, 13, 2)
    println(customFilter(onlyEven)(xs))
    // RESULT: List(12, 20, 2)

    val onlyEvenFilter = customFilter(onlyEven) _
    println(onlyEvenFilter(xs))
    // RESULT: List(12, 20, 2)

  }

}
