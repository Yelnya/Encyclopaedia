package westheide

import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global

/**
  * from: http://danielwestheide.com/blog/2013/01/16/the-neophytes-guide-to-scala-part-9-promises-and-futures-in-practice.html
  */

object E9_Promises_And_Futures_In_Practice {

  def main(args: Array[String]): Unit = {

    /**
      * Promises
      * A Promise instance is always linked to exactly one instance of Future. If you call the apply method of
      * Future again in the REPL, you will indeed notice that the Future returned is a Promise, too
      */

    import concurrent.Promise
    case class TaxCut(reduction: Int)
    // either give the type as a type parameter to the factory method:
    val taxcut = Promise[TaxCut]()
    // or give the compiler a hint by specifying the type of your val:
    val taxcut2: Promise[TaxCut] = Promise()

    val taxcutF: Future[TaxCut] = taxcut.future


    /**
      * Completing a Promise
      */

    taxcut.success(TaxCut(20))

    object Government {
      def redeemCampaignPledge(): Future[TaxCut] = {
        val p = Promise[TaxCut]()
        Future {
          println("Starting the new legislative period.")
          Thread.sleep(2000)
          p.success(TaxCut(20))
          println("We reduced the taxes! You must reelect us!!!!1111")
        }
        p.future
      }
    }

    import scala.util.{Success, Failure}
    val taxCutF: Future[TaxCut] = Government.redeemCampaignPledge()
    println("Now that they're elected, let's see if they remember their promises...")
    taxCutF.onComplete {
      case Success(TaxCut(reduction)) =>
        println(s"A miracle! They really cut our taxes by $reduction percentage points!")
      case Failure(ex) =>
        println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
    }

    // RESULT:
    // Now that they're elected, let's see if they remember their promises...
    // Starting the new legislative period.


    /**
      * Breaking Promises like a sir
      */

    case class LameExcuse(msg: String) extends Exception(msg)
    object Government2 {
      def redeemCampaignPledge(): Future[TaxCut] = {
        val p = Promise[TaxCut]()
        Future {
          println("Starting the new legislative period.")
          Thread.sleep(2000)
          p.failure(LameExcuse("global economy crisis"))
          println("We didn't fulfill our promises, but surely they'll understand.")
        }
        p.future
      }
    }

    val taxCutF2: Future[TaxCut] = Government2.redeemCampaignPledge()
    println("Now that they're elected, let's see if they remember their promises...")
    taxCutF2.onComplete {
      case Success(TaxCut(reduction)) =>
        println(s"A miracle! They really cut our taxes by $reduction percentage points!")
      case Failure(ex) =>
        println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
    }

    // RESULT:
    // Now that they're elected, let's see if they remember their promises...
    // Starting the new legislative period.



  }
}
