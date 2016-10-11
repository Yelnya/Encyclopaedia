package lookup.uniform_access_principle

/**
  * from: https://www.scala-exercises.org/std_lib/uniform_access_principle
  */
object UniformAccessPrinciple {

  def main(args: Array[String]): Unit = {

    /**
      * The Scala language implements a programming concept known as the
      * [Uniform Access Principle](http://en.wikipedia.org/wiki/Uniform_access_principle) which was first put forth
      * by Bertrand Meyer, inventor of the Eiffel programming language.
      *
      * This principle states that variables and parameterless functions should be accessed using the same syntax.
      * Scala supports this principle by allowing parentheses to not be placed at call sites of parameterless
      * functions. As a result, a parameterless function definition can be changed to a val, or vice versa,
      * without affecting client code.
      */

    class Test1 (val age: Int = 10)
    class Test2 (_age: Int) {
      def age: Int = _age
    }

    println(new Test1(10).age)
    // RESULT: 10
    println(new Test2(11).age)
    // RESULT: 11




  }

}
