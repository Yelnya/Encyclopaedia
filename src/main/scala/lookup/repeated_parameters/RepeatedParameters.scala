package lookup.repeated_parameters

/**
  * from: https://www.scala-exercises.org/std_lib/repeated_parameters
  */

object RepeatedParameters {

  def main(args: Array[String]): Unit = {

    /**
      * A repeated parameter must be the last parameter and this will let you add as many extra parameters
      * as needed.
      */

    // GIVEN:
    def repeatedParameterMethod(x: Int, y: String, z: Any*) = {
      "%d %ss can give you %s".format(x, y, z.mkString(", "))
    }

    // RESOLVE:
    println(repeatedParameterMethod(3, "egg", "a delicious sandwich", "protein", "high cholesterol"))
    // RESULT: 3 eggs can give you a delicious sandwich, protein, high cholesterol

  }

}
