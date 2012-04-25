/**
 * Created by IntelliJ IDEA.
 * User: nicholasren
 * Date: 11-7-9
 * Time: 下午3:17
 */

object MergeSort {
  def msort[T](less: (T, T) => Boolean)
              (xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  def main(args : Array[String])
  {
      val sorted = msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3))
      println(sorted)
  }
}