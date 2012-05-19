package org.nicholasren.scala.stock

object UseXML {
def main(args: Array[String]) {
  val xmlFragment = <symbols>
  <symbol ticker="AAPL"><units>200</units></symbol>
  <symbol ticker="IBM"><units>215</units></symbol>
  </symbols>
  
//  println(xmlFragment)
//  println(xmlFragment.getClass())
  
  var symbolsNodes = xmlFragment \ "symbol"
  println(symbolsNodes.mkString("\n"))
  println(symbolsNodes.getClass)
  
}
}