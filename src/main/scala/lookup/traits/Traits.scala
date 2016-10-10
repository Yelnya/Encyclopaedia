package lookup.traits

/**
  * from: https://www.scala-exercises.org/std_lib/traits
  */
object Traits {

  def main(args: Array[String]): Unit = {

    /**
      * Similar to *interfaces* in Java, traits are used to define object types by specifying
      * the signature of the supported methods. Unlike Java, Scala allows traits to be partially
      * implemented; i.e. it is possible to define default implementations for some methods.
      * In contrast to classes, traits may not have constructor parameters.
      * This trait consists of two methods isSimilar and isNotSimilar. While isSimilar does not
      * provide a concrete method implementation (it is abstract in the terminology of Java), method
      * isNotSimilar defines a concrete implementation. Consequently, classes that integrate this trait
      * only have to provide a concrete implementation for isSimilar. The behavior for isNotSimilar
      * gets inherited directly from the trait.
      */

    trait Similarity {
      def isSimilar(x: Any) : Boolean
      def isNotSimilar(x: Any) : Boolean = !isSimilar(x)
    }

    /**
      * A class uses the extends keyword to mixin a trait if it is the only relationship the class inherits
      */

    case class Event(name: String)

    trait EventListener {
      def listen(event: Event) : String
    }

    class MyListener extends EventListener {
      def listen(event: Event): String = {
        event match {
          case Event ("Moose Stampede") => "An unfortunate moose stampede occurred"
          case _ => "Nothing of importance occured"
        }
      }
    }

    val evt = Event ("Moose Stampede")
    val myListener = new MyListener
    println(myListener.listen(evt))
    // RESULT: An unfortunate moose stampede occurred

    /**
      * A class can only extend from one class or trait, any subsequent extension should use the keyword with
      */

    case class Event2(name: String)

    trait EventListener2 {
      def listen(event: Event2) : String
    }

    class OurListener

    class MyListener2 extends OurListener with EventListener2 {
      def listen(event: Event2): String = {
        event match {
          case Event2("Woodchuck Stampede") ⇒ "An unfortunate woodchuck stampede occurred"
          case _ ⇒ "Nothing of importance occurred"
        }
      }
    }

    val evt2 = Event2("Woodchuck Stampede")
    val myListener2 = new MyListener2
    println(myListener2.listen(evt2))
    // RESULT: An unfortunate woodchuck stampede occurred

    /**
      * Traits are polymorphic. Any type can be referred to by another type if related by extension
      */

    case class Event3(name: String)

    trait EventListener3 {
      def listen(event: Event3): String
    }

    class MyListener3 extends EventListener3 {
      def listen(event: Event3): String = {
        event match {
          case Event3("Moose Stampede") ⇒ "An unfortunate moose stampede occurred"
          case _ ⇒ "Nothing of importance occurred"
        }
      }
    }

    val myListener3 = new MyListener3

    println(myListener3.isInstanceOf[MyListener3])
    // RESULT: true
    println(myListener3.isInstanceOf[EventListener3])
    // RESULT: true
    println(myListener3.isInstanceOf[Any])
    // RESULT: true
    println(myListener3.isInstanceOf[AnyRef])
    // RESULT: true


  }
}
