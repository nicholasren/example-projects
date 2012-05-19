package org.nicholasren.scala.stock

import scala.xml.XML
object ReadWriteXML {
  def main(args: Array[String]) {
    val stocksAndUnits = XML.load("stocks.xml")

    println(stocksAndUnits.getClass)
    println("loaed file has " + (stocksAndUnits \\ "symbol").size + " symbol emelents")

    val stocksAndUnitsMap = (Map[String, Int]() /: (stocksAndUnits \ "symbol")) { (map, symbolNode) =>
      val ticker = (symbolNode \ "@ticker").toString
      val units = (symbolNode \ "units").text.toInt

      map(ticker) = units
    }

    println("Number of symbol elements found is " + stocksAndUnitsMap.size)

    def undateUnitsAndCreateXML(element: (String, Int)) = {
      val (ticker, units) = element
      <symbol ticker={ ticker }>
        <units>{ units + 1 }</units>
      </symbol>
    }

    val updatedStocksAndUnitsXML = <symbols>
                                     { stocksAndUnitsMap.map { undateUnitsAndCreateXML } }
                                   </symbols>

    XML save ("stock2.xml", updatedStocksAndUnitsXML)

    println("The saved file contains " + (XML.load("stock2.xml") \\ "symbol").size + " symbol elements")

  }
}