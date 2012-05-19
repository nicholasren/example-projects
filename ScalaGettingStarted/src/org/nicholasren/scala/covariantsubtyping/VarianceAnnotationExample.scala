package org.nicholasren.scala.covariantsubtyping

object VarianceAnnotationExample {

	def main(args: Array[String]) {
  
		var s = new DefaultStack[AnyRef]();
//		s = new DefaultStack[String]();  //should report a type mismatch error
		
		var covariantStack = new CovariantStack[AnyRef]();
		covariantStack = new CovariantStack[String](); //this is legal
		
		//逆变的泛型类型的 父子关系和类型参数父子关系相反:  对于Stack[-A] 如果 S <: T ，那么 Stack[T] <: Stack[S]
		var contravariantStack = new ContravariantStack[String]();
		contravariantStack = new ContravariantStack[AnyRef](); // 因为String <: AnyRef， 所以 ContravariantStack[AnyRef] <: ContravariantStack[String]
		
		
		
		//
		val f: (AnyRef => Int) = x => x.hashCode()
        val g: (String => Int) = f
        
        print(g("abc"))

    }
	
}

//默认的 stack
class DefaultStack[A]{}

//协变的 stack
class CovariantStack[+A]{}

//逆变的 stack
class ContravariantStack[-A]{}
