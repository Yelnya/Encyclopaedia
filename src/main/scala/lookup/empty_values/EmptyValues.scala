package lookup.empty_values

/**
  * from: https://www.scala-exercises.org/std_lib/empty_values
  */
object EmptyValues {

  def main(args: Array[String]): Unit = {

    /**
      * null
      * Scala's null is the same as in Java. Any reference type can be null, like Strings, Objects, or your own
      * classes. Also just like Java, value types like Ints can't be null.
      */

    /**
      * Null
      * Null is a trait whose only instance is null. It is a subtype of all reference types, but not of value
      * types. It purpose in existing is to make it so reference types can be assigned null and value types can't.
      */

    /**
      * Nothing
      * Nothing is a trait that is guaranteed to have _zero_ instances. It is a subtype of all other
      * types. It has two main reasons for existing: to provide a return type for methods that **never** return
      * normally (i.e. a method that always throws an exception). The other reason is to provide a type for
      * Nil (explained below).
      */

    /**
      * Unit
      * Unit in Scala is the equivalent of void in Java. It's used in a function's signature when that function
      * doesn't return a value.
      */

    /**
      * Nil
      * Nil is just an empty list, exactly like the result of List(). It is of type List[Nothing]. And since we
      * know there are no instances of Nothing, we now have a list that is statically verifiable as empty. Nice to have.
      */

    /**
      * An empty list can be represented by another nothing value: Nil
      */

    println(List() == Nil)
    // RESULT: true

    /**
      * None is the counterpart to Some, used when you're using Scala's Option class to help avoid null references.
      * None equals None:
      */

    println(None == None)
    // RESULT: true

    /**
      * None should be identical to None:
      */

    println(None eq None)
    // RESULT: true

    /**
      * None can be converted to a *String*:
      */

    println(None.toString)
    // RESULT: None

    /**
      * None can be converted to an empty list
      */

    println(None.toList == Nil)
    // RESULT: true

    /**
      * None is considered empty
      */

    println(None.isEmpty)
    // RESULT: true

    /**
      * None can be cast Any, AnyRef or AnyVal
      */

    println(None.asInstanceOf[Any] == None)
    // RESULT: true
    println(None.asInstanceOf[AnyRef] == None)
    // RESULT: true
    println(None.asInstanceOf[AnyVal] == None)
    // RESULT: true

    /**
      * None can be used with Option instead of null references
      */

    val optional: Option[String] = None
    println(optional.isEmpty)
    // RESULT: true
    println(optional)
    // RESULT: None

    /**
      * Some is the opposite of None for Option types
      */

    val optional1: Option[String] = Some("Some Value")
    println(optional1 == None)
    // RESULT: false
    println(optional1.isEmpty)
    // RESULT: false

    /**
      * Option.getOrElse can be used to provide a default in the case of None
      */

    val optional2: Option[String] = Some("Some Value")
    val optional3: Option[String] = None
    println(optional2.getOrElse("No Value"))
    // RESULT: Some Value
    println(optional3.getOrElse("No Value"))
    // RESULT: No Value


  }

}
