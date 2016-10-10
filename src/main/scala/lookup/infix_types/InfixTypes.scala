package lookup.infix_types

/**
  * from: https://www.scala-exercises.org/std_lib/infix_types
  */
object InfixTypes {

  def main(args: Array[String]): Unit = {

    /**
      * An infix type T1 op T2 consists of an infix operator op which gets applied to two type operands T1
      * and T2. The type is equivalent to the type application op[T1,T2].
      * The infix operator op may be an arbitrary identifier, except for *, which is reserved as a postfix
      * modifier denoting a repeated parameter type.

      * We can make a type infix, meaning that the type can be displayed in complement between two types in
      * order to make a readable declaration:
      */

    case class Person(name: String)

    class Loves[A, B] (val a: A, val b: B)

    def announceCouple(couple: Person Loves Person) = {
      //Notice our type: Person loves Person!
      couple.a.name + " is in love with " + couple.b.name
    }

    val romeo = new Person("Romeo")
    val juliet = new Person("Juliet")

    println(announceCouple(new Loves(romeo, juliet)))
    // RESULT: Romeo is in love with Juliet

    /**
      * Of course we can make this a bit more elegant by creating an infix operator method to use with our infix type
      */

    case class Person1(name: String) {
      def loves1(person1: Person1) = new Loves1(this, person1)
    }

    class Loves1[A, B](val a: A, val b: B)

    def announceCouple1(couple1: Person1 Loves1 Person1) = {
      // Notice our Type: Person loves Person!
      couple1.a.name + " is in love with " + couple1.b.name
    }

    val romeo1 = new Person1("Romeo")
    val juliet1 = new Person1("Juliet")

    println(announceCouple1(romeo1 loves1 juliet1))
    // RESULT: Romeo is in love with Juliet
  }
}
