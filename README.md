# scala-exercise-note


https://www.scala-exercises.org/cats


http://typelevel.org/cats/tut/functor.html


semigroup
 (a+b) +c === a+(b+c)

 1+2+3+4
 (((1+2)+3)+4) == (1+2) + (3+4)
 로 해도 동일한 결과다
 ((a combine b) combine c)
 must be the same as
 (a combine (b combine c))



 병렬 처리 할수 있다!

 monoid는 병렬처리 할수 있는 특성을 가지고 있다라고 설명할수 있음

 Functor

