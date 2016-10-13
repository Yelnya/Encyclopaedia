package ninetynine_problems.working_with_lists

import scala.collection.mutable.ListBuffer

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */

object P12 {

  def main(args: Array[String]): Unit = {

    /**
      * P12: Decode a run-length encoded list.
      * Given a run-length code list generated as specified in problem P10, construct its uncompressed version.
      * List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
      * should be
      * List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
      */

    val list = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))

    /**
      * Solution 1
      * Petra
      */

    var finalList = new ListBuffer[Symbol]  // mutable list

    def flattenList(list: List[(Int, Symbol)]) : List[Symbol] = list match {
      case Nil => finalList.toList
      case head :: tail =>    // looks like this: head = (4,'a), tail = List((1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
       for (i <- 1 to head._1) {
         finalList += head._2   // number of elements in tuple will be written into finalList
       }
      flattenList(tail)
      }
    println(flattenList(list))
    // RESULT: List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    /**
      * Solution 2
      * Phil Gold
      * can't compile Phil's solution!
      */

//    def decode[Symbol](list: List[(Int, Symbol)]): List[Symbol] = {
//      list.flatMap {
//        e => List.make(e._1, e._2)
//      }
//    }
//    println(decode(list))

  }
}
