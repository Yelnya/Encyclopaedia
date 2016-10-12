package ninetynine_problems.working_with_lists

/**
  * from: http://aperiodic.net/phil/scala/s-99/
  */
object P09 {

  def main(args: Array[String]): Unit = {

    /**
      * Pack consecutive duplicates of list elements into sublists.
      * List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e) should be
      * List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)
      */

    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    /**
      * Solution 1
      * Petra
      */

    def packList(list: List[Any]): List[Any] = {
      println("PACKLIST = List: " + list)
      list match {
        case head :: Nil => println("PACKLIST = Last Element: " + list);
          list // if this is last Element

        case head :: tail => if (head == tail.head) {
//          packList(tail, Nil) // cut first element
          println("PACKLIST = head == tail.head: " + list)
          println(list.dropWhile(_ == head) :: addToSubList(list, Nil))
          addToSubList(list, Nil) :: list.dropWhile(_ == head)
//          list :: addToSubList(list, Nil)

        } else {
          println("PACKLIST = head != tail.head, " + list)
          head +: packList(tail) // add head to tail, because no copy
        }
        case Nil => throw new NoSuchElementException // if list is empty
      }
    }

    def addToSubList(list: List[Any], subList: List[Any]) : List[Any] = {
      println("SUBLIST = List: " + list)
      list match {
        case head :: tail => if (head == tail.head) {
          println("SUBLIST: Tail == Head")
          addToSubList(tail, subList :+ head)
        } else {
          println("SUBLIST: Tail != Head")
//          packList(tail, subList :+ head)
          println("SUBLIST = " + (subList :+ head));
          subList :+ head
        }
      }
    }

    println(packList(list))


//    val list1 = List(1, 2)
//    val list2 = List(2, 3)
//    val list3 = List(4, 5)
//    val result = list1 :+ (list2, list3)
//
//    println(result)

  }
}
