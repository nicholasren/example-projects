package org.nicholasren.scala.patternmatch

object Evaluator {

  //类型名 Environment 可以作为“从 String 转成 Int”这一类函数的别名
  type Env = String => Int

  def eval(t: Tree, env: Env): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Multiply(l, r) => eval(l, env) * eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }

  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l, r) => Sum(derive(l, v), derive(r, v))
    case Multiply(l, r) => Sum(Multiply(l, derive(r, v)), Multiply(derive(l, v), r))
    case Var(n) if (v == n) => Const(1)
    case _ => Const(0)
  }
  
  //TODO 实现一个化简函数
//  def  simplification(t : Tree) : Tree = t match
//  {
//	  case Sum(l, r) => if(l == 0) {r} if(r == 0){l}
//	  case Multiply(l, r) => if(l == 0 || r == 0) Const(0)
//  } 


  def main(args: Array[String]) {
    val exp: Tree = Sum(Sum(Var("x"), Var("x")), Multiply(Const(7), Var("y")))
    val env: Env = { case "x" => 5 case "y" => 7 }
    println("Expression: " + exp)
    println("Evaluation with x=5, y=7: " + eval(exp, env))
    println("Derivative relative to x:\n " + derive(exp, "x"))
    println("Derivative relative to y:\n " + derive(exp, "y"))
  }

}