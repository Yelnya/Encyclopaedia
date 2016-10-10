package lookup.implicits

/**
  * from: https://www.scala-exercises.org/std_lib/implicits
  */
object Implicits {

  def main(args: Array[String]): Unit = {

    /**
      * The actual arguments that are eligible to be passed to an implicit parameter fall into two categories:

      * - First, eligible are all identifiers x that can be accessed at the point of the method call without a prefix
      * and that denote an implicit definition or an implicit parameter. - Second, eligible are also all members of
      * companion modules of the implicit parameter's type that are labeled implicit.

      * In the following example we define a method sum which computes the sum of a list of
      * elements using the monoid's add and unit operations. Please note that implicit values can
      * not be top-level, they have to be members of a template.
      */

    abstract class SemiGroup[A] {
      def add (x: A, y: A) : A
    }

    abstract class Monoid[A] extends SemiGroup[A] {
      def unit: A
    }

    implicit object StringMonoid extends Monoid[String] {
      def add(x: String, y: String) : String = x concat y
      def unit: String = ""
    }
    implicit object IntMonoid extends Monoid[Int] {
      def add(x: Int, y: Int) : Int = x + y
      def unit: Int = 0
    }
    def sum[A] (xs: List[A])(implicit m: Monoid[A]) : A =
      if (xs.isEmpty) m.unit
      else m.add (xs.head, sum(xs.tail))
    println(sum(List(1, 2, 3)))
    // RESULT: 6
    println(sum(List("a", "b", "c")))
    // RESULT: abc

    /**
      * Implicits wrap around existing classes to provide extra functionality. This is similar to *monkey patching* in **Ruby**, and *Meta-Programming* in **Groovy**.
      * Creating a method isOdd for Int, which doesn't exist:
      */

//    class KoanIntWrapper1(val original: Int) {
//      def isOdd = original % 2 != 0
//    }
//
//    implicit def thisMethodNameIsIrrelevant1(value: Int) = new KoanIntWrapper1(value)
//    println(19.isOdd)
//    // RESULT: true
//    println(20.isOdd)
//    // RESULT: false

    /**
      * Implicits rules can be imported into your scope with an import:
      */

    object MyPredef {
      class KoanIntWrapper2(val original: Int) {
        def isOdd = original % 2 != 0
        def isEven = !isOdd
      }
      implicit def thisMethodNameIsIrrelevant2(value: Int) = new KoanIntWrapper2(value)
    }

    import MyPredef._
    println(19.isOdd)
    // RESULT: true
    println(20.isOdd)
    // RESULT: false

    /**
      * convertTypeImplicits
      * Implicits can be used to automatically convert a value's type to another:
      */

    // TODO: Didn't understand: when is implicid needed and when not? if implicit is not used here, the result is the same!

    import java.math.BigInteger
    implicit def Int2BigIntegerConvert(value: Int) : BigInteger = new BigInteger(value.toString)

    def add(a: BigInteger, b: BigInteger) = a.add(b)

    println(add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9))
    // RESULT: true
    println(add(3, 6) == 9)
    // RESULT: false
    println(add(3, 6) == Int2BigIntegerConvert(9))
    // RESULT: true
    println(add(3, 6) == (9: BigInteger))
    // RESULT: true
    println(add(3, 6).intValue == 9)
    // RESULT: true

    /**
      * Implicits can be used to declare a value to be provided as a default as long as an implicit value is set
      * with in the scope. These are called Implicit Function Parameters
      */

//    def howMuchCanIMake_?(hours: Int)(implicit dollarsPerHour: BigDecimal) = dollarsPerHour * hours
//    implicit val hourlyRate = BigDecimal(34.00)
//    println(howMuchCanIMake_?(30))
    // RESULT: 1020.0

    /**
      * Implicit Function Parameters can contain a list of implicits
      */

    def howMuchCanIMake2_?(hours: Int)(implicit amount: BigDecimal, currencyName: String) =
    (amount * hours).toString() + " " + currencyName

    implicit val hourlyRate2 = BigDecimal(34.00)
    implicit val currencyName2 = "Dollars"

    println(howMuchCanIMake2_?(30))
    // RESULT: 1020.0 Dollars

    /**
      * Default arguments, though, are preferred to Implicit Function Parameters
      */

    def howMuchCanIMake3_?(hours: Int, amount: BigDecimal = 34, currencyName: String = "Dollars") =
    (amount * hours).toString() + " " + currencyName

    println(howMuchCanIMake3_?(30))
    // RESULT: 1020 Dollars
    println(howMuchCanIMake3_?(30, 95))
    // RESULT: 2850 Dollars
    println(howMuchCanIMake3_?(30, 95, "EURO"))
    // RESULT: 2850 EURO

  }
}
