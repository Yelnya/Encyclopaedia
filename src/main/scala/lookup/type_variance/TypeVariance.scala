package lookup.type_variance

/**
  * from: https://www.scala-exercises.org/std_lib/type_variance
  */
object TypeVariance {

  def main(args: Array[String]): Unit = {

    /**
      * A traditional objection to static typing is that it has much syntactic overhead. Scala alleviates
      * this by providing *type inference*.
      *
      * The classic method for type inference in functional programming languages is *Hindley-Milner*, and
      * it was first employed in ML.
      *
      * Scala's type inference system works a little differently, but it's similar in spirit: infer
      * constraints, and attempt to unify a type.
      *
      * Using type inference the type that you instantiate it will be the val or var reference type:
      */

//    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
//      def contents = manifest.runtimeClass.getSimpleName
//    }
//
//    val fruitBasket = new MyContainer(new Orange())
//    println(fruitBasket.contents)

    /**
      * You can explicitly declare the type variable of the object during instantiation
      */

//    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
//      def contents = manifest.runtimeClass.getSimpleName
//    }
//
//    val fruitBasket = new MyContainer[Fruit](new Orange())
//    println(fruitBasket.contents)

    /**
      * You can coerce your object to a type
      */

//    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
//      def contents = manifest.runtimeClass.getSimpleName
//    }
//
//    val fruitBasket: MyContainer[Fruit] = new MyContainer(new Orange())
//    println(fruitBasket.contents)

    /**
      * Scala's type system has to account for class hierarchies together with polymorphism. Class hierarchies
      * allow the expression of subtype relationships. A central question that comes up when mixing OO with
      * polymorphism is: if T' is a subclass of T, is Container[T'] considered a subclass of Container[T]?
      * Variance annotations allow you to express the following relationships between class hierarchies &
      * polymorphic types:
      *
      * ####Covariant: - C[T'] is a subclass of C[T] - Scala notation: [+T]
      * ####Contravariant: - C[T] is a subclass of C[T'] - Scala notation: [-T]
      * ####Invariant: - C[T] and C[T'] are not related - Scala notation: [T]
      *
      * That one probably blew your mind. Now if you assign a type to the instantiation that is different to the
      * variable type, you'll have problems. You may want to take time after this koan to compare and contrast
      * with the previous one.
      */

//    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
//      def contents = manifest.runtimeClass.getSimpleName
//    }
//
//    val fruitBasket:MyContainer[Fruit] = new MyContainer[Orange](new Orange())

    /**
      * So, how do we get to set a Fruit basket to an Orange basket? You make it covariant using +. This will allow
      * you to set the container to either a variable with the same type or parent type. In other words, you can
      * assign MyContainer[Fruit] or MyContainer[Citrus].
      */

//    class MyContainer[+A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
//      def contents = manifest.runtimeClass.getSimpleName
//    }
//
//    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
//    println(fruitBasket.contents)

    /**
      * The problem with covariance is that you can't mutate, set or change the object since it has to guarantee
      * that what you put into it is a valid type. In other words the reference is a fruit basket, but we still
      * have to make sure that no other fruit can be placed in our orange basket:
      */

//    class MyContainer[+A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
//      def contents = manifest.runtimeClass.getSimpleName
//    }
//
//    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
//    fruitBasket.contents should be()
//
//    class NavelOrange extends Orange //Creating a subtype to prove a point
//    val navelOrangeBasket: MyContainer[NavelOrange] = new MyContainer[Orange](new Orange()) //Bad!
//    val tangeloBasket: MyContainer[Tangelo] = new MyContainer[Orange](new Orange()) //Bad!

    /**
      * Declaring neither -/+, indicates invariance variance. You cannot use a superclass variable reference
      * (\"contravariant\" position) or a subclass variable reference (\"covariant\" position) of that type.
      * In our example, this means that if you create a citrus basket you can only reference that citrus basket with
      * a citrus variable only.

      * Invariance means you need to specify the type exactly:
      */

//    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
//      def contents = manifest.runtimeClass.getSimpleName
//    }
//
//    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
//    println(citrusBasket.contents)

  }
}
