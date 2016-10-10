package lookup.for_expressions

/**
  * from: https://www.scala-exercises.org/std_lib/for_expressions
  */
object ForExpressions {

  def main(args: Array[String]): Unit = {

    /**
      * For expressions can nest, with later generators varying more rapidly than earlier ones
      */

    val xValues = 1 to 4
    val yValues = 1 to 2
    val coordinates = for {
      x <- xValues
      y <- yValues
    } yield (x, y)

    println(coordinates(0))
    // RESULT: (1,1)
    println(coordinates(1))
    // RESULT: (1,2)
    println(coordinates(2))
    // RESULT: (2,1)
    println(coordinates(3))
    // RESULT: (2,2)
    println(coordinates(4))
    // RESULT: (3,1)
    println(coordinates(5))
    // RESULT: (3,2)
    println(coordinates(6))
    // RESULT: (4,1)
    println(coordinates(7))
    // RESULT: (4,2)
//    println(coordinates(8))
    // RESULT: IndexOutOfBoundsException

    /**
      * Using for we can make more readable code
      */

    val nums = List(List(1), List(2), List(3), List(4), List(5))

    val resultList = for {
      numList <- nums   // for each numList out of nums
      num <- numList    // get each number from this numList
      if (num % 2 == 0)   // if number is even, add to Result List with result numbers
    } yield (num)

    println(resultList)
    // RESULT: List(2, 4)

  }
}
