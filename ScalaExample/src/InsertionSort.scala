/**
 * Created by IntelliJ IDEA.
 * User: nicholasren
 * Date: 11-7-9
 * Time: 下午2:54
 */

object InsertionSort {
//  def isort(xs: List[Int]): List[Int] = {
//    if (xs.isEmpty)
//      Nil
//    else
//      insert(xs.head, isort(xs.tail))
//  }
//
//  def insert(x: Int, xs: List[Int]): List[Int] = {
//    if (xs.isEmpty || x < xs.head)
//      x :: xs
//    else
//      xs.head :: insert(x, xs.tail)
//  }

  def isort(xs: List[Int]): List[Int] = {
    xs match {
      case List() => List()
      case x :: xs1 => insert(x, isort(xs1))
    }
  }

  def insert(x: Int, xs: List[Int]): List[Int] = {
    xs match {
      case List() => List(x)
      case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
    }
  }


  def main(args: Array[String]) {
    val xs = List(4, 6, 7, 3, 223, 999999, 6)
    print(isort(xs))
  }

}