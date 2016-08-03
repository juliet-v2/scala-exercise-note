package semigroup

import org.scalatest.{Matchers, WordSpec}

/**
  * Created by juliet on 2016. 7. 20..
  */
class MySemi extends WordSpec with  Matchers {
  "my semi group"  should {
    "in" in {
      import cats.Semigroup
      case class Foo(a:Int, b:String)
      implicit val fooSemigroup = new Semigroup[Foo]{
        override def combine(x: Foo, y: Foo): Foo = Foo(x.a + y.a, x.b + y.b  )
      }

      val foo1= Foo(1, "a")
      val foo2 = Foo(2, "b")
      Semigroup[Foo].combine(foo1, foo2) shouldBe  Foo(3, "ab")

    }

  }

}
