package apply
import cats._

import org.scalatest.{Matchers, WordSpec}

/**
  * Created by juliet on 2016. 8. 2..
  */
class applySpec extends WordSpec with Matchers {
  implicit val optionApply:Apply[Option] = new Apply[Option]{

    def ap[A,B](f:Option[A=>B])(fa:Option[A]):Option[B] =
      fa.flatMap(a => f.map(ff =>ff(a)))

    def map[A, B](fa: Option[A])(f:A=>B):Option[B] = fa map f

  }

  implicit val listApply: Apply[List] = new Apply[List] {
    def ap[A, B](f: List[(A) => B])(fa: List[A]): List[B] =
      fa.flatMap(a => f.map(ff=>ff(a)))

    def map[A, B](fa: List[A])(f: (A) => B): List[B] = fa map f
  }
  // Apply에는 int param을 넣을수 없다
  // Apply[int].map(1) = X요렇게 쓸수 없다

  "map test" in {
    /**
     Apply는 Functor의 확장이며 Functor로 부터 받은 map 메소드를 사용할수 있다
    **/
    val intToString:Int =>String = _.toString
    val double:Int => Int = _*2
    val addTwo: Int => Int =_+2

    Apply[Option].map(Some(1))(intToString) shouldBe Some("1")
    Apply[Option].map(Some(1))(double) shouldBe Some(2)
    Apply[Option].map(None)(double) shouldBe None
  }

  "compose test" in {
    val listOpt = Apply[List] compose Apply[Option]

    val plusOne = (x:Int) => x+1

    listOpt.ap(List(Some(plusOne)))(List(Some(1), None, Some(3))) shouldBe List(Some(2), None, Some(4))

  }

  "Ap test" in {
    // ap 메서드는 functor가 갖고 있지 않은 메서드다
    val intToString:Int =>String = _.toString
    val double:Int => Int = _*2
    val addTwo: Int => Int =_+2

    Apply[Option].ap(Some(intToString))(Some(1)) shouldBe Some("1")
    Apply[Option].ap(Some(double))(Some(1)) should be( Some(2) )
    Apply[Option].ap(Some(double))(None) should be( None )
    Apply[Option].ap(None)(Some(1)) should be( None )
    Apply[Option].ap(None)(None) should be( None )
  }

  val addArity2 = (a: Int, b: Int) ⇒ a + b
  val addArity3 = (a: Int, b: Int, c: Int) ⇒ a + b + c
  "apN test" in {
    Apply[Option].ap2(Some(addArity2))(Some(1), Some(2)) should be( Some(3) )
    Apply[Option].ap2(Some(addArity2))(Some(1), None) should be( None )
    Apply[Option].ap3(Some(addArity3))(Some(1), Some(2), Some(3)) should be( Some(6) )
  }

  "MapN also available " in {
    Apply[Option].map2(Some(1), Some(2))(addArity2) shouldBe Some(3)
    Apply[Option].map3(Some(1), Some(2), Some(3))(addArity3) shouldBe Some(6)
  }

  "tupleN test" in {
    Apply[Option].tuple2(Some(1), Some(2)) shouldBe Some(1, 2)
    Apply[Option].tuple3(Some(1), Some(2), Some(3)) shouldBe Some(1, 2, 3)
  }

  "apply builder syntax" in {
    // All instances created by |@| have map, ap, and tupled methods of the appropriate arity:
    import cats.implicits._
    val option2 = Option(1) |@| Option(2)
    val option3 = option2 |@| Option.empty[Int]

    option2.map(addArity2) shouldBe Option(3)
    option3.map(addArity3) shouldBe None

    option2.apWith(Some(addArity2)) shouldBe Some(3)
    option3.apWith(Some(addArity3)) shouldBe None

    option2.tupled shouldBe Option(1,2)
    option3.tupled shouldBe None
  }
}
