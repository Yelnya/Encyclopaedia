package lookup.case_classes

/**
  * from: https://www.scala-exercises.org/std_lib/case_classes
  */
object CaseClasses {

  def main(args: Array[String]): Unit = {

    /**
      * Scala supports the notion of case classes. Case classes are regular classes which export their
      * constructor parameters and which provide a recursive decomposition mechanism via pattern matching.
      * Here is an example for a class hierarchy which consists of an abstract super class Term and three
      * concrete case classes Var, Fun, and App.
      */

    abstract class Term
    case class Var(name: String) extends Term
    case class Fun(arg: String, body: Term) extends Term
    case class App(f: Term, v: Term) extends Term

    /**
      * This class hierarchy can be used to represent terms of the untyped lambda calculus. To facilitate
      * the construction of case class instances, Scala does not require that the new primitive is used.
      * One can simply use the class name as a function.
      * Here is an example:
      */

    Fun("x", Fun("y", App(Var("x"), Var("y"))))

    // The constructor parameters of case classes are treated as public values and can be accessed directly.

    val x = Var("x")
    Console.println(x.name)
    // RESULT: x

    /**
      * For every case class the Scala compiler generates equals method which implements structural equality
      * and atoString method. For instance:
      */

    val x1 = Var("x")
    val x2 = Var("x")
    val y1 = Var("y")
    println("" + x1 + " == " + x2 + " => " + (x1 == x2))
    // RESULT: Var(x) == Var(x) => true
    println("" + x1 + " == " + y1 + " => " + (x1 == y1))
    // RESULT: Var(x) == Var(y) => false

    /**
      * It only makes sense to define case classes if pattern matching is used to decompose data structures.
      * The following object defines a pretty printer function for our lambda calculus representation
      *
      * In our example, the function print is expressed as a pattern matching statement starting with the
      * match keyword and consisting of sequences of case Pattern => Body clauses.

      * The program above also defines a function isIdentityFun which checks if a given term corresponds
      * to a simple identity function. This example uses deep patterns and guards. After matching a pattern
      * with a given value, the guard (defined after the keyword if) is evaluated. If it returns true, the
      * match succeeds; otherwise, it fails and the next pattern will be tried.
      */

    object TermTest {

      def printTerm(term: Term) {
        term match {
          case Var(n) =>
            print(n)
          case Fun(x, b) =>
            print("^" + x + ".")
            printTerm(b)
          case App(f, v) =>
            Console.print("(")
            printTerm(f)
            print(" ")
            printTerm(v)
            print(")")
        }
      }
      def isIdentityFun(term: Term): Boolean = term match {
        case Fun(x, Var(y)) if x == y => true
        case _ => false
      }
    }

    val id = Fun("x", Var("x"))
    val t = Fun("x", Fun("y", App(Var("x"), Var("y"))))
    TermTest.printTerm(t)
    println
    println(TermTest.isIdentityFun(id))
    println(TermTest.isIdentityFun(t))
    /* RESULT:
    ^x.^y.(x y)
    true
    false
    */

    /**
      * Case classes have an automatic equals method that works
      */

    case class Person(first: String, last: String)

    val p1 = new Person("Fred", "Jones")
    val p2 = new Person("Shaggy", "Rogers")
    val p3 = new Person("Fred", "Jones")

    println(p1 == p2)
    // RESULT: false
    println(p1 == p3)
    // RESULT: true
    println(p1 eq p2)
    // RESULT: false
    println(p1 eq p3)
    // RESULT: false

    /**
      * Case classes have an automatic hashcode method that works
      */

    println(p1.hashCode == p2.hashCode)
    // RESULT: false
    println(p1.hashCode == p3.hashCode)
    // RESULT: true

    /**
      * Case classes can be created in a convenient way
      */

    case class Dog(name: String, breed: String)
    val d1 = Dog("Scooby", "Doberman")
    println(d1.toString)
    // RESULT: Dog(Scooby,Doberman)

    /**
      * Case classes have automatic properties
      */

    println(d1.name)
    // RESULT: Scooby
    println(d1.breed)
    // RESULT: Doberman

    /**
      * There are safer alternatives for altering case classes
      */

    val d1_1 = Dog("Scooby", "Doberman")

    val d1_2 = d1_1.copy(name = "Scooby Doo") // copy the case class but change the name in the copy

    println(d1_1.name) // original left alone
    // RESULT: Scooby
    println(d1_1.breed)
    // RESULT: Doberman
    println(d1_2.name)
    // RESULT: Scooby Doo
    println(d1_2.breed)
    // RESULT: Doberman

    /**
      * Case classes can have default and named parameters
      */

    case class Person2(first: String, last: String, age: Int = 0, ssn: String = "")
    val pers1 = Person2("Fred", "Jones", 23, "111-22-3333")
    val pers2 = Person2("Samantha", "Jones") // note missing age and ssn
    val pers3 = Person2(last = "Jones", first = "Fred", ssn = "111-22-3333") // note the order can change, and missing age
    val pers4 = pers3.copy(age = 23)

    println(pers1.first)
    // RESULT: Fred
    println(pers1.last)
    // RESULT: Jones
    println(pers1.age)
    // RESULT: 23
    println(pers1.ssn)
    // RESULT: 111-22-3333

    println(pers2.first)
    // RESULT: Samantha
    println(pers2.last)
    // RESULT: Jones
    println(pers2.age)
    // RESULT:
    println(pers2.ssn)
    // RESULT: 0
    println(pers3.first)
    // RESULT: Fred
    println(pers3.last)
    // RESULT: Jones
    println(pers3.age)
    // RESULT: 0
    println(pers3.ssn)
    // RESULT: 111-22-3333

    println(pers1 == pers4)
    // RESULT: true

    /**
      * Case classes can be disassembled to their constituent parts as a tuple
      */

    val person1 = Person2("Fred", "Jones", 23, "111-22-3333")
    val parts = Person2.unapply(person1).get // this seems weird, but it's critical to other features of Scala

    println(parts._1)
    // RESULT: Fred
    println(parts._2)
    // RESULT: Jones
    println(parts._3)
    // RESULT: 23
    println(parts._4)
    // RESULT: 111-22-3333

    /**
      * Case classes are Serializable
      */

    case class PersonCC(firstName: String, lastName: String)
    val indy = PersonCC("Indiana", "Jones")
    println(indy.isInstanceOf[Serializable])
    // RESULT: true

    class Person3(firstName: String, lastName: String)
    val junior = new Person3("Indiana", "Jones")
    println(junior.isInstanceOf[Serializable])
    // RESULT: false
  }
}
