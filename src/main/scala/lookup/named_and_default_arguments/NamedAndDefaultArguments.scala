package lookup.named_and_default_arguments

/**
  * from: https://www.scala-exercises.org/std_lib/named_and_default_arguments
  */
object NamedAndDefaultArguments {

  def main(args: Array[String]): Unit = {

    /**
      * When calling methods and functions, you can use the name of the variables explicitly in the call, like so
      */

    def printName(first: String, last: String) = {
      println(first + " " + last)
    }

    printName("John", "Smith")
    // RESULT: John Smith
    printName(first = "John", last = "Smith")
    // RESULT: John Smith
    printName(last = "Smith", first = "John")
    // RESULT: John Smith

    /**
      * Note that once you are using parameter names in your calls, the order doesn't matter,
      * so long as all parameters are named. This feature works well with default parameter values
      */

    def printName2(first : String = "John", last: String = "Smith") = {
      println (first + " " + last)
    }
    printName2(last = "Jones")
    // RESULT: John Jones

    /**
      * Given classes below
      */

    class WithoutClassParameters() {
      def addColors(red: Int, green: Int, blue: Int) = {
        (red, green, blue)
      }

      def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
        (red, green, blue)
      }
    }

    class WithClassParameters(val defaultRed: Int, val defaultGreen: Int, val defaultBlue: Int) {
      def addColors(red: Int, green: Int, blue: Int) = {
        (red + defaultRed, green + defaultGreen, blue + defaultBlue)
      }

      def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
        (red + defaultRed, green + defaultGreen, blue + defaultBlue)
      }
    }

    class WithClassParametersInClassDefinition(val defaultRed: Int = 0, val defaultGreen: Int = 255, val defaultBlue: Int = 100) {
      def addColors(red: Int, green: Int, blue: Int) = {
        (red + defaultRed, green + defaultGreen, blue + defaultBlue)
      }

      def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
        (red + defaultRed, green + defaultGreen, blue + defaultBlue)
      }
    }

    /**
      * Can specify arguments in any order if you use their names
      */

    val me = new WithoutClassParameters()
    // what happens if you change the order of these parameters (nothing)
    val myColor = me.addColors(green = 0, red = 255, blue = 0)
    println(myColor)
    // RESULT: (255, 0, 0)

    /**
      * Can default arguments if you leave them off
      */

    val me2 = new WithoutClassParameters()
    val myColor2 = me2.addColorsWithDefaults(green = 255)
    println(myColor2)
    // RESULT: (0,255,0)

    /**
      * Can access class parameters and specify arguments in any order if you use their names
      */

    val me3 = new WithClassParameters(40, 50, 60)
    val myColor3 = me3.addColors(green = 50, red = 60, blue = 40)
    println(myColor3)
    // RESULT: (100,100,100)

    /**
      * Can access class parameters and default arguments if you leave them off
      */

    val me4 = new WithClassParameters(10, 20, 30)
    val myColor4 = me4.addColorsWithDefaults(green = 70)
    println(myColor4)
    // RESULT: (10,90,30)

    /**
      * Can default class parameters and have default arguments too
      */

    val me5 = new WithClassParametersInClassDefinition()
    val myColor5 = me5.addColorsWithDefaults(green = 70)
    println(myColor5)
    // RESULT: (0,325,100)

    /**
      * Default parameters can be functional too
      */

    def reduce(a: Int, f: (Int, Int) => Int = _ + _): Int = f(a, a)
    println(reduce(5))
    // RESULT: 10
    println(reduce(5, _ * _))
    // RESULT: 25
  }
}
