# scala-exercise-note


https://www.scala-exercises.org/cats


http://typelevel.org/cats/tut/functor.html


#Semigroup
 (a+b) +c === a+(b+c)

 ex) 1+2+3+4
 (((1+2)+3)+4) == (1+2) + (3+4)
 로 해도 동일한 결과다


 ((a combine b) combine c) === (a combine (b combine c))

  뭘 먼저 해도 상관없으니.. 병렬 처리 할수 있다!

 > monoid는 병렬처리 할수 있는 특성을 가지고 있다

#Monoid
  Monoid extends the Semigroup type class, adding an empty method to semigroup's combine

#Functor

