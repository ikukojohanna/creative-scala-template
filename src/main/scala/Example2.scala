// Creative Scala Exercises: Structural Recursion over the Natural Numbers - End

import cats.effect.unsafe.implicits.global
import doodle.core.*
import doodle.image.*
import doodle.syntax.all.*
import doodle.image.syntax.all.*
import doodle.java2d.*


@main
  def scalaBridge(): Unit =




/*
    val aBox = Image.square(20).fillColor(Color.royalBlue)
    val redBox = Image.square(20).fillColor(Color.darkRed)
    val aSecondBox = Image.square(side = 40).fillColor(Color.antiqueWhite)
    val aTriangle = Image.triangle(20,20).fillColor(Color.royalBlue)*/
    val aRectangle = Image.rectangle(20, 40).fillColor(Color.royalBlue)
    val aSecondRectangle = Image.rectangle(40, 20).fillColor(Color.royalBlue)


// ----- A Line of Boxes ----- 

    def boxes(count: Int): Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      count match 
        case 0 => Image.empty
        case n => aBox.beside(boxes(n-1))
   
// ----- The Natural Numbers ----- 
      
    def stacks(count: Int): Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      count match 
        case 0 => Image.empty
        case n => aBox.above(stacks(n-1))


      
    def alternatingRow(count: Int) : Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      val aSecondBox = Image.square(side = 40).fillColor(Color.antiqueWhite)
      count match 
        case 0 => Image.empty
        case n if (n % 2 == 0) => aBox.beside(alternatingRow(n-1))
        case n => aSecondBox.beside(alternatingRow(n-1))
      

  

    def funRow(count: Int) : Image =
      count match 
        case 0 => Image.empty
        case n => Image.star(20, n * 10, 10).fillColor(Color.royalBlue.spin((10 * n).degrees)).beside(funRow(n-1))
      

   

    def cross(count: Int) : Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      val redBox = Image.square(20).fillColor(Color.darkRed)
      count match 
        case 0 => redBox
        case n => aBox.above(aBox.beside(cross(n-1).beside(aBox))).above(aBox)
      


// ----- Fractals ----- 

    def chessboard(count: Int) : Image =
      val blueBox = Image.square(20).fillColor(Color.royalBlue)
      val redBox = Image.square(20).fillColor(Color.darkRed)

      count match {
        case 0 => redBox.beside(blueBox).above(blueBox.beside(redBox))
        case n =>
          val unit : Image = chessboard(n-1)
          unit.beside(unit).above(unit.beside(unit))
      }


    
    def sierpinski(count: Int): Image =
      val aTriangle = Image.triangle(20,20).fillColor(Color.royalBlue)
      count match {
        case 0 => aTriangle.above(aTriangle.beside(aTriangle))
        case n =>
          val unit : Image = sierpinski(n-1)
          unit.above(unit.beside(unit))
      }

  
// ----- Nested Methods ----- 


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



// ----- Auxiliary Parameters ----- 


    def gradientBoxes(count: Int, color: Color): Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      count match {
        case 0 => Image.empty
        case n =>
         aBox
          .fillColor(color)
          .beside(gradientBoxes(n-1, color.spin(1.radians)))
      }

    def concentricCircles(count: Int, size: Int, color: Color): Image =
      def circle (size: Int, color: Color): Image = Image.circle(size).strokeColor(color)
      count match
        case 0 => Image.empty
        case n =>
          circle(size, color)
            .on(concentricCircles(n - 1, size + 10, color.spin(1.radians)))

// ----- Exercise: colorful chessboard ----- 

    def colorfulChessboard(count: Int, color: Color) : Image =
      count match {
        case 0 =>             
          val contrast = color.spin(180.degrees)
          val aBox = Image.square(40)

          aBox.fillColor(color).beside(aBox.fillColor(contrast)).above(aBox.fillColor(contrast).beside(aBox.fillColor(color)))
        case n =>
          colorfulChessboard(n-1, color.spin(3.degrees)).beside(colorfulChessboard(n-1, color.spin(10.degrees))).above(colorfulChessboard(n-1, color.spin(30.degrees)).beside(colorfulChessboard(n-1, color.spin(100.degrees))))
          }

// ----- Points, Paths, and Polygons ----- 

    def polygonPoints(sides: Int, radius: Int): Image =
      val dot = Image.circle(5).fillColor(Color.darkViolet)
      def loop(count: Int ): Image =
        count match
          case 0 => dot.at(Point(radius, 0.degrees))
          case n => dot.at(Point(radius, (360 / sides).degrees)).on(loop(n - 1))
      loop(sides)

      //---- test for artwork:
    
    val rectangleH = Image.rectangle(300,100).fillColor(Color.darkViolet)
    val rectangleV = Image.rectangle(100,300).fillColor(Color.darkViolet)
    val vertices =
       rectangleV.at(Point(120, 0.degrees))
        .on(rectangleH.at(Point(120, 90.degrees)))
        .on(rectangleV.at(Point(120, -180.degrees)))
        .on(rectangleH.at(Point(120, -90.degrees)))
        .on(
          rectangleV.at(Point(100, 0.degrees))
            .on(rectangleH.at(Point(100, 90.degrees)))
            .on(rectangleV.at(Point(100, -180.degrees)))
            .on(rectangleH.at(Point(100, -90.degrees))))

    //vertices.draw()







    //stacks(3).draw()
    //alternatingRow(5).draw()
    // funRow(5).draw()
    // cross(10).draw()
    // chessboard(4).draw()
    // sierpinski(3).draw()
    // chessboardTwo(3).draw()
    //  boxesTwo(3).draw()
    // gradientBoxes(4, Color.royalBlue).draw()
    // concentricCircles(5, 200, Color.royalBlue).draw()
    //colorfulChessboard(2, Color.royalBlue).draw()


    //polygonPoints(5, 50).draw()
   
  


