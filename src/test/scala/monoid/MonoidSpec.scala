package monoid

import org.scalatest.{Matchers, WordSpec}
import cats._
import cats.std.all._
import cats.syntax.all._
/**
  * Created by hyeyoungkang on 2016. 7. 21..
  */
class MonoidSpec extends WordSpec with Matchers{
  "monoid spec test" should {

    /*
    Monoid extends the Semigroup type class,
    adding an empty method to semigroup's combine.
    The empty method must return a value that when
    combined with any other instance of that type
    returns the other instance, i.e.
    (combine(x, empty) == combine(empty, x) == x)

     */

    "test 1 " in {
      import cats.implicits._
      Monoid[String].empty should be( "" )
      Monoid[String].combineAll(List("a", "b", "c")) should be( "abc" )
      Monoid[String].combineAll(List()) should be( "" )
    }

    "complex type monoid test" in {
      Monoid[Map[String, Int]].combineAll(List(Map("a" → 1, "b" → 2), Map("a" → 3))) should be( Map("a"->4, "b" ->2 ) )
      Monoid[Map[String, Int]].combineAll(List()) should be( Map() )
    }

    "fold map test" in {
      val l = List(1, 2, 3, 4, 5)
      l.foldMap(identity) should be( 15 )
      l.foldMap(i ⇒ i.toString) should be( "12345" )
    }

    "tuple test" in {
      val l = List(1, 2, 3, 4, 5)
      l.foldMap(i ⇒ (i, i.toString)) shouldBe( (15,"12345") )
    }
  }

}
