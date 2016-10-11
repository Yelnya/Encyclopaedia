package lookup.type_signatures

/**
  * from: https://www.scala-exercises.org/std_lib/type_signatures
  */
object TypeSignatures {

  def main(args: Array[String]): Unit = {

    /**
      * A method's *type signature* comprises its name, the number, order, and types of its
      * parameters, if any, and its result type. The type signature of a class, trait, or singleton object
      * comprises its name, the type signatures of all of its members and constructors, and its declared
      * inheritance and mixin relations.
      * In Java you declare a generic type within a <>, in Scala it is []
      */

    val z: List[String] = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" :: Nil

    /**
      * Most of the time, Scala will infer the type and [] are optional:
      */

    val z1 = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" :: Nil
    //Infers that the list assigned to variable is of type List[String]

    /**
      * A trait can be declared containing a type, where a concrete implementer will satisfy the type:
      */

    trait Randomizer[A] {
      def draw(): A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw() = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    println(intRand.draw < Int.MaxValue)
    // RESULT: true

    /**
      * Class meta-information can be retrieved by class name by using classOf[className]
      */

    println(classOf[String].getCanonicalName)
    // RESULT: java.lang.String
    println(classOf[String].getSimpleName)
    // RESULT: String

    /**
      * Class meta-information can be derived from an object reference using getClass()
      */

    val zoom = "zoom"
    println(zoom.isInstanceOf[String])
    // RESULT: true
    println(zoom.getClass.getCanonicalName)
    // RESULT: java.lang.String
    println(zoom.getClass.getSimpleName)
    // RESULT: String

    /**
      * isInstanceOf[className] is used to determine the if an object reference is an instance of given class:
      */

    trait Randomizer2[A] {
      def draw(): A
    }

    class IntRandomizer2 extends Randomizer2[Int] {
      def draw() = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand2 = new IntRandomizer2
    println(intRand2.draw().isInstanceOf[Int])
    // RESULT: true

  }

}
