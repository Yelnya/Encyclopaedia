package ninetynine_problems.working_with_lists

import scala.collection.mutable.ListBuffer

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P09 {

  def main(args: Array[String]): Unit = {

    /**
      * P09: Pack consecutive duplicates of list elements into sublists.
      * List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e) should be
      * List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)
      */

    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    /**
      * Solution 1
      * Petra
      */

    var sublist = new ListBuffer[Any]
    var finishList = false

    def packList(list: List[Any], finalList: List[Any]): List[Any] = {
      if (finishList) {
        if (!sublist.isEmpty) {
          sublist.clear()
        }
        finishList = !finishList
      }
      list match {
        case head :: Nil =>
          finishList = true
          sublist += head
          //            println("Head :: Nil : subList: " + sublist + ", finalList: " + finalList :: sublist.toList)
          finalList :+ sublist.toList

        case head :: tail => if (head == tail.head) {
          finishList = false
          sublist += head
          //          println("Head == Tail.Head: Tail: " + tail + ", subList: " + sublist + ", finalList: " + finalList)
          packList(tail, finalList)
        } else {
          finishList = true
          sublist += head
          //            println("Head != Tail.Head: Tail: " + tail + ", subList: " + sublist + ", finalList: " + finalList :: sublist.toList)
          packList(tail, finalList :+ sublist.toList)
        }
      }
    }

    println(packList(list, Nil))

    /**
      * Solution 2
      * Phil Gold
      * Comment PJ: Don't understand
      */

    def pack[A](ls: List[A]): List[List[A]] = {
      if (ls.isEmpty) List(List())
      else {
        val (packed, next) = ls span { _ == ls.head }
        if (next == Nil) List(packed)
        else packed :: pack(next)
      }
    }

    println(pack(list))
  }
}
