# scala-exercise-note


https://www.scala-exercises.org/cats


http://typelevel.org/cats/tut/functor.html


#Semigroup
 ((a combine b) combine c) === (a combine (b combine c))

 뭘 먼저 해도 상관없으니.. 병렬 처리 할수 있다!

 
#Monoid
  Semigroup의 확장, empty 장착
  
  (combine(x, empty) == combine(empty, x) == x)
  
  

#Functor

