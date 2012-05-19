package org.nicholasren.scala

object ClassComposition {
  def main(args: Array[String]): Unit =
    {
      class Iter extends StringIterator("abcdef") with RichIterator[Char]

      val iter = new Iter()

      iter.foreach(System.out.print)
    }

}

trait AbsIterator[T] {
  def hasNext: Boolean
  def next: T
}

trait RichIterator[T] extends AbsIterator[T] {
  def foreach(f: T => Unit): Unit =
    while (hasNext) f(next)
}

class StringIterator(s: String) extends AbsIterator[Char] {
  private var i = 0

  def hasNext: Boolean = i < s.length

  def next: Char = { val x = s charAt i; i = i + 1; x }

}
