package ninetynine_problems.problem1

/**
  * Created by PJ on 08.10.2016.
  */
class Problem_1 {

  def main(args: Array[String]): Unit = {

    // TEST CHANGE
    object NinetynineProblems {

      def main(args: Array[String]): Unit = {
        println("Opened the Encyclopaedia of Knowledge")

        // following examples taken from http://aperiodic.net/phil/scala/s-99/
        // solutions by PJ

        // Exercise P01 (*) Find the last element of a list.
        // Solution P01-1 PJ:
        val defaultList = List(1, 1, 2, 3, 5, 8)
        println("P01: Last Element of List = " + getLastOfList(defaultList))

        def getLastOfList(x : List[Int]) : Int = {
          return x.last
        }
      }
    }
  }
}
