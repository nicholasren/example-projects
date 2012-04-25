package org.nicholasren.scala

object MethodAreFunctionalValue {

  def exists[T](xs: Array[T], p: T => Boolean): Boolean = {
    var i: Int = 0
    while (i < xs.length && !p(xs(i)))
      i = i + 1;

    return i < xs.length
  }

  def forall[T](xs: Array[T], p: T => Boolean): Boolean = {
    return !exists(xs, (x: T) => !p(x))
  }

  //    def hasZeroRow(matrix: Array[Array[Int]]) : Boolean = {
  //      return exists(matrix, (row: Array[Int]) => forall(row, 0== ))
  //    }

  def _hasZeroRow(matrix: Array[Array[Int]]) = {
    matrix.exists(row => row.forall(0 ==))
  }

  /**
   * 返回xs中所有非负数的平方根
   */
  def sqrts(xs: List[Double]): List[Double] = {
    //首先用filter过滤出所有非负数，
    //在对这些非负数调用Math.sqrt计算其平方根
    //在把这些平方根构造成一个列表返回
    return (xs.filter(0 <=)).map(Math.sqrt)
  }

  def sqrts1(xs: List[Double]): List[Double] = {
    for (val x <- xs; 0 <= x) yield Math.sqrt(x)
  }
  
  //Variance-不一致性
  //covariant 协变的
  //对于可变变量 mutable variable, 协变性是不好的。
  //而对于不可变变量，immutable variable, 协变性是很自然的。

  
  
  

}