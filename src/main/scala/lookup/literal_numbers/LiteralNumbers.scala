package lookup.literal_numbers

/**
  * from: https://www.scala-exercises.org/std_lib/literal_numbers
  */
object LiteralNumbers {

  def main(args: Array[String]): Unit = {

    /**
      * Integer Literals are 32-bit and can be created from decimal, hexadecimal
      */

    val a = 2
    val b = 31
    val c = 0x30F
    val e = 0
    val f = -2
    val g = -31
    val h = -0x30F
    println(a)
    // RESULT: 2
    println(b)
    // RESULT: 31
    println(c)
    // RESULT: 783
    println(e)
    // RESULT: 0
    println(f)
    // RESULT: -2
    println(g)
    // RESULT: -31
    println(h)
    // RESULT: -783

    /**
      * Long Literals are 64 bit, are specified by appending an L or l at the end
      */

    val a1 = 2L
    val b1 = 31L
    val c1 = 0x30FL
    val e1 = 0L
    val f1 = -2l
    val g1 = -31L
    val h1 = -0x30FL

    println(a1)
    // RESULT: 2
    println(b1)
    // RESULT: 31
    println(c1)
    // RESULT: 783
    println(e1)
    // RESULT: 0
    println(f1)
    // RESULT: -2
    println(g1)
    // RESULT: -31
    println(h1)
    // RESULT: -783

    /**
      * Float and Double Literals are IEEE 754 for specific, Float are 32-bit length, Doubles are 64-bit.
      * Floats can be coerced using a f or F suffix, and Doubles can be coerced using a d or D suffix.
      * Exponent are specified using e or E.
      */

    val a2 = 3.0
    val b2 = 3.00
    val c2 = 2.73
    val d2 = 3f
    val e2 = 3.22d
    val f2 = 93e-9
    val g2 = 93E-9
    val h2 = 0.0
    val i2 = 9.23E-9D

    println(a2)
    // RESULT: 3
    println(b2)
    // RESULT: 3
    println(c2)
    // RESULT: 2.73D
    println(d2)
    // RESULT: 3
    println(e2)
    // RESULT: 3.22
    println(f2)
    // RESULT: 93E-9
    println(g2)
    // RESULT: 93e-9
    println(h2)
    // RESULT: 0
    println(i2)
    // RESULT: 9.23-9d

  }
}