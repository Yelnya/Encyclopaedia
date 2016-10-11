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

    /**
      * A repeated parameter can accept a collection as the last parameter but will be considered a single object
      */

    println(repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol")))
    // RESULT: 3 eggs can give you List(a delicious sandwich, protein, high cholesterol)

    /**
      * A repeated parameter can accept a collection,and if you want it expanded, add :_*
      */

    println(repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol"): _*))
    // RESULT: 3 eggs can give you a delicious sandwich, protein, high cholesterol

  }
}
