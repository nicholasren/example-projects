package org.nicholasren.scala.stock
import scala.swing._
import event._

object SampleGUI extends SimpleGUIApplication {
  def top = new MainFrame {
    title = "Nicholas's Scala Swing GUI"
    val label = new Label { text = "-----------" }
    val button = new Button { text = "Click me" }
    contents = new FlowPanel {
      contents += label
      contents += button
    }
    listenTo(button)

    reactions += {
      case ButtonClicked(button) => label.text = "You clicked!!!"
    }
  }
}