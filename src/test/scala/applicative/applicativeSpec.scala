package applicative

import cats._
import cats.std.all._
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by juliet on 2016. 8. 4..
  */
class applicativeSpec extends WordSpec with Matchers {
  "map test" in {
    /**
      * Applicative는 Apply의 확장이다 simgle method pure가 추가됐다
      * 이 메서드는 임의의 값을 취해서 Functor의 context의 value로 반환한다
    **/
    Applicative[Option].pure(1) shouldBe Some(1)
    Applicative[List].pure(1) shouldBe List(1)
    // Functor , Apply, Applicative
    (Applicative[List] compose Applicative[Option]).pure(1) shouldBe List(Some(1))
  }

  "APPLICATIVE FUNCTORS & MONADS test" in {
    Monad[Option].pure(1) shouldBe Option(1)
    Applicative[Option].pure(1) shouldBe Option(1)
  }
}
