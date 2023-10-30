// Creative Scala Exercises: Structural Recursion over the Natural Numbers - End

import cats.effect.unsafe.implicits.global
import doodle.core.*
import doodle.image.*
import doodle.syntax.all.*
import doodle.image.syntax.all.*
import doodle.java2d.*


@main
  def scalaBridge(): Unit =

  // 
    val aBox = Image.square(20).fillColor(Color.royalBlue)
    val redBox = Image.square(20).fillColor(Color.darkRed)
    val aSecondBox = Image.square(side = 40).fillColor(Color.antiqueWhite)

    val aTriangle = Image.triangle(20,20).fillColor(Color.royalBlue)

    def boxes(count: Int): Image =
      count match {
        case 0 => Image.empty
        case n => aBox.beside(boxes(n-1))
      }
    def stacks(count: Int): Image =
      count match {
        case 0 => Image.empty
        case n => aBox.above(stacks(n-1))
      }
    def alternatingRow(count: Int) : Image =
      count match {
        case 0 => Image.empty
        case n if (n % 2 == 0) => aBox.beside(alternatingRow(n-1))
        case n => aSecondBox.beside(alternatingRow(n-1))
      }

    def funRow(count: Int) : Image =
      count match {
        case 0 => Image.empty
        case n => Image.square(n * 10).fillColor(Color.royalBlue).beside(funRow(n-1))
      }

    def cross(count: Int) : Image =
      count match {
        case 0 => redBox
        case n => aBox.above(aBox.beside(cross(n-1).beside(aBox))).above(aBox)
      }

    def chessboard(count: Int) : Image =
      count match {
        case 0 => redBox.beside(aBox).above(aBox.beside(redBox))
        case n =>
          val unit : Image = chessboard(n-1)
          unit.beside(unit).above(unit.beside(unit))
      }

    def sierpinski(count: Int): Image =
      count match {
        case 0 => aTriangle.above(aTriangle.beside(aTriangle))
        case n =>
          val unit : Image = sierpinski(n-1)
          unit.above(unit.beside(unit))
      }

    def chessboardTwo(count: Int): Image = {
      val blackSquare = Image.square(30).fillColor(Color.black)
      val redSquare = Image.square(30).fillColor(Color.red)

      val base =
        (redSquare beside blackSquare) above (blackSquare beside redSquare)

      def loop(count: Int): Image = {
        count match {
        case 0 => base
        case n =>
          val unit = loop(n - 1)
          (unit beside unit) above (unit beside unit)
        }
      }
      loop(count)
    }


    def boxesTwo(count: Int): Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      def loop(count: Int): Image =
        count match
          case 0 => Image.empty
          case n => aBox.beside(boxes(n - 1))
      loop(count)

    def gradientBoxes(count: Int, color: Color): Image =
      count match {
        case 0 => Image.empty
        case n =>
          Image
            .square(20)
            .fillColor(color)
            .beside(gradientBoxes(n-1, color.spin(1.radians)))
      }
    gradientBoxes(4, Color.royalBlue).draw()

    def concentricCircles(count: Int, size: Int, color: Color): Image =
      count match
        case 0 => Image.empty
        case n =>
          Image
            .circle(size)
            .strokeColor(color)
            .on(concentricCircles(n - 1, size + 10, color.spin(1.radians)))
    concentricCircles(5, 20, Color.royalBlue).draw()

    def polygonPoints(sides: Int, radius: Int): Image =
      val dot = Image.circle(5).fillColor(Color.darkViolet)
      def loop(count: Int ): Image =
        count match
          case 0 => dot.at(Point(radius, 0.degrees))
          case n => dot.at(Point(radius, (360 / sides).degrees)).on(loop(n - 1))
      loop(sides)








    //polygonPoints(5, 50).draw()
    //boxesTwo(3).draw()
    //stacks(3).draw()
    //alternatingRow(5).draw()
    //funRow(5).draw()
    cross(3).draw()
    //chessboard(4).draw()
    //sierpinski(3).draw()
    //chessboardTwo(3).draw()
  


