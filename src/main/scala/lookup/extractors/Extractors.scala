package lookup.extractors

/**
  * from: https://www.scala-exercises.org/std_lib/extractors
  */
object Extractors {

  def main(args: Array[String]): Unit = {

    /**
      * In Scala, patterns can be defined independently of case classes. To this end, a method named unapply is
      * defined to yield a so-called extractor.
      * For instance, the following code defines an extractor object Twice.
      *
      * There are two syntactic conventions at work here:

      * - The pattern case Twice(n) will cause an invocation of Twice.unapply, which is used to match even number; the
      * return value of the unapply signals whether the argument has matched or not, and any sub-values that can be
      * used for further matching. Here, the sub-value is z/2 - The apply method is not necessary for pattern
      * matching. It is only used to mimick a constructor. val x = Twice(21) expands to val x = Twice.apply(21).
      */

    object Twice {
      def apply(x: Int) : Int = x * 2
      def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z / 2) else None
    }

    object TwiceTest {
      val x = Twice(21)
      x match {
        case Twice(n) => Console.println(n)   // prints 21
      }
    }

    /**
      * The code in the preceding example would be expanded as follows
      */

    object TwiceTest2 {
      val x = Twice.apply(21)
      Twice.unapply(x) match {
        case Some(n) => Console.println(n)    // prints 21
      }
    }

    /**
      * The return type of an unapply should be chosen as follows:
      * - If it is just a test, return a Boolean. For instance case even() - If it returns a single
      * sub-value of type T, return a Option[T] - If you want to return several sub-values T1,...,Tn, group
      * them in an optional tuple Option[(T1,...,Tn)].
      * Sometimes, the number of sub-values is fixed and we would like to return a sequence. For this reason,
      * you can also define patterns through unapplySeq. The last sub-value type Tn has to be Seq[S]. This
      * mechanism is used for instance in pattern case List(x1, ..., xn).
      * When you create a case class, it automatically can be used with pattern matching since it has an extractor:
      */

    case class Employee(firstName: String, lastName: String)

    val rob = new Employee ("Robin", "Williams")
    val result = rob match {
      case Employee("Robin",_) => "Where's Batman?"
      case _ => "No Batman Joke For You"
    }
    println(result)
    // RESULT: "Where's Batman?"

    /**
      * What's an extractor? In Scala it's a method in any object called unapply, and that method is
      * used to disassemble the object given by returning a tuple wrapped in an option. Extractors can be
      * used to assign values:
      */

    class Car(val make: String, val model: String, val year: Short, val topSpeed: Short)

    object ChopShop {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
    }

    val ChopShop(a, b, c, d) = new Car("Chevy", "Camaro", 1978, 120)

    println(a)
    // RESULT: Chevy
    println(b)
    // RESULT: Camaro
    println(c)
    // RESULT: 1978
    println(d)
    // RESULT: 120

    /**
      * Of course an extractor can be used in pattern matching...
      */

    class Car1(val make: String, val model: String, val year: Short, val topSpeed: Short)

    object ChopShop1 {
      def unapply(x: Car1) = Some(x.make, x.model, x.year, x.topSpeed)
    }

    val x = new Car1("Chevy", "Camaro", 1978, 120) match {
      case ChopShop1(s, t, u, v) => (s, t)
      case _ => ("Ford", "Edsel")
    }

    println(x._1)
    // RESULT: Chevy
    println(x._2)
    // RESULT: Camaro

    /**
      * Since we aren't really using u and v in the previous pattern matching with can replace them with _.
      */

    class Car2(val make: String, val model: String, val year: Short, val topSpeed: Short)

    object ChopShop2 {
      def unapply(x: Car2) = Some (x.make, x.model, x.year, x.topSpeed)
    }
    val x2 = new Car2("Chevy", "Camaro", 1978, 120) match {
      case ChopShop2(s, t, _, _) => (s, t)
      case _ => ("Ford", "Edsel")
    }

    println(x._1)
    // RESULT: Chevy
    println(x._2)
    // RESULT: Camaro

    /**
      * As long as the method signatures aren't the same, you can have as many unapply methods as you want
      */

    class Car3(val make: String, val model: String, val year: Short, val topSpeed: Short)
    class Employee3(val firstName: String, val middleName: Option[String], val lastName: String)

    object Tokenizer3 {
      def unapply(x: Car3) = Some(x.make, x.model, x.year, x.topSpeed)
      def unapply(x: Employee3) = Some(x.firstName, x.lastName)
    }

    val result3 = new Employee3("Kurt", None, "Vonnegut") match {
      case Tokenizer3(c, d) => "c: %s, d: %s".format(c, d)
      case _ => "Not found"
    }
    println(result3)
    // RESULT: c: Kurt, d: Vonnegut

    /**
      * An extractor can be any stable object, including instantiated classes with an unapply method
      */

    class Car4(val make: String, val model: String, val year: Short, val topSpeed: Short) {
      def unapply(x: Car4) = Some(x.make, x.model)
    }
    val camaro4 = new Car4("Chevy", "Camaro", 1978, 122)

    val result4 = camaro4 match {
      case camaro4(make, model) => "make: %s, model: %s".format(make, model)
      case _ => "unknown"
    }

    println(result4)
    // RESULT: make: Chevy, model: Camaro

    /**
      * What is typical is to create a custom extractor in the companion object of the class. In this exercise,
      * we use it as an assignment
      */

    class Employee5(val firstName: String, val middleName: Option[String], val lastName: String)

    object Employee5 {
      // factory methods, extractors, apply
      // Extractor: Create tokens that represent your object
      def unapply(x: Employee5) = Some(x.lastName, x.middleName, x.firstName)
    }

    val singri = new Employee5("Singri", None, "Keerthi")

    val Employee5(a1, b1, c1) = singri
    println(a1)
    // RESULT: Keerthi
    println(b1)
    // RESULT: None
    println(c1)
    // RESULT: Singri

    /**
      * In this exercise we use the unapply for pattern matching employee objects
      */

    class Employee6(val firstName: String, val middleName: Option[String], val lastName: String)

    object Employee6 {
      //factory methods, extractors, apply
      //Extractor: Create tokens that represent your object
      def unapply(x: Employee6) =
      Some(x.lastName, x.middleName, x.firstName)
    }

    val singri6 = new Employee6("Singri", None, "Keerthi")

    val result6 = singri6 match {
      case Employee6("Singri", None, x) => "Yay, Singri %s! with no middle name!".format(x)
      case Employee6("Singri", Some(x), _) => "Yay, Singri with a middle name of %s".format(x)
      case _ => "I don't care, going on break"
    }

    println(result6)
    // RESULT: I don't care, going on break

  }
}
