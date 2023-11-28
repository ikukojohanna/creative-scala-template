// Creative Scala Exercises: Structural Recursion over the Natural Numbers - End

import cats.effect.unsafe.implicits.global
import doodle.core.*
import doodle.image.*
import doodle.syntax.all.*
import doodle.image.syntax.all.*
import doodle.java2d.*
import fs2.concurrent.Channel.Closed

@main
  def scalaBridge(): Unit =

//---------------------------------------------------- Structural Recursion over the Natural Numbers --------------------------------------------------------------------

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
        
    //stacks(3).draw()


      
    def alternatingRow(count: Int) : Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      val aSecondBox = Image.square(side = 40).fillColor(Color.antiqueWhite)
      count match 
        case 0 => Image.empty
        case n if (n % 2 == 0) => aBox.beside(alternatingRow(n-1))
        case n => aSecondBox.beside(alternatingRow(n-1))
     
    //alternatingRow(5).draw()

  

    def funRow(count: Int) : Image =
      count match 
        case 0 => Image.empty
        case n => Image.star(20, n * 10, 10).fillColor(Color.royalBlue.spin((10 * n).degrees)).beside(funRow(n-1))
      
    // funRow(5).draw()
   

    def cross(count: Int) : Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      val redBox = Image.square(20).fillColor(Color.darkRed)
      count match 
        case 0 => redBox
        case n => aBox.above(aBox.beside(cross(n-1).beside(aBox))).above(aBox)
      
    // cross(10).draw()


//----------------------------------------------------------------- Fractals -----------------------------------------------------------------------

    def chessboard(count: Int) : Image =
      val blueBox = Image.square(20).fillColor(Color.royalBlue)
      val redBox = Image.square(20).fillColor(Color.darkRed)

      count match {
        case 0 => redBox.beside(blueBox).above(blueBox.beside(redBox))
        case n =>
          val unit : Image = chessboard(n-1)
          unit.beside(unit).above(unit.beside(unit))
      }

    // chessboard(4).draw()

    
    def sierpinski(count: Int): Image =
      val aTriangle = Image.triangle(20,20).fillColor(Color.royalBlue)
      count match {
        case 0 => aTriangle.above(aTriangle.beside(aTriangle))
        case n =>
          val unit : Image = sierpinski(n-1)
          unit.above(unit.beside(unit))
      }

    // sierpinski(3).draw()
 
    
    
    

  
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

    // chessboardTwo(3).draw()


    def boxesTwo(count: Int): Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      def loop(count: Int): Image =
        count match
          case 0 => Image.empty
          case n => aBox.beside(boxes(n - 1))
      loop(count)

    // boxesTwo(3).draw()


// ----- Auxiliary Parameters ----- 


    def gradientBoxes(count: Int, color: Color): Image =
      val aBox = Image.square(20).fillColor(Color.royalBlue)
      count match 
        case 0 => Image.empty
        case n =>
         aBox
          .fillColor(color)
          .beside(gradientBoxes(n-1, color.spin(1.radians)))

    // gradientBoxes(4, Color.royalBlue).draw()

    def concentricCircles(count: Int, size: Int, color: Color): Image =
      def circle (size: Int, color: Color): Image = Image.circle(size).strokeColor(color)
      count match
        case 0 => Image.empty
        case n =>
          circle(size, color)
            .on(concentricCircles(n - 1, size + 10, color.spin(1.radians)))

    // concentricCircles(5, 200, Color.royalBlue).draw()

// ----- Exercise: colorful chessboard ----- 

    def colorfulChessboard(count: Int, color: Color) : Image =
      count match 
        case 0 =>             
          val contrast = color.spin(180.degrees)
          val aBox = Image.square(40)

          aBox.fillColor(color).beside(aBox.fillColor(contrast)).above(aBox.fillColor(contrast).beside(aBox.fillColor(color)))
        case n =>
          colorfulChessboard(n-1, color.spin(3.degrees)).beside(colorfulChessboard(n-1, color.spin(10.degrees))).above(colorfulChessboard(n-1, color.spin(30.degrees)).beside(colorfulChessboard(n-1, color.spin(100.degrees))))
          
    // colorfulChessboard(2, Color.royalBlue).draw()


//----------------------------------------------------------- Points, Paths, and Polygons --------------------------------------------------------------


    def polygonPoints(sides: Int, radius: Int): Image =
      val dot = Image.circle(5).fillColor(Color.darkViolet)
      val turn = (360 / sides).degrees
      def loop(count: Int ): Image =
        count match
          case 0 => dot.at(Point(radius, 0.degrees))
          case n => dot.at(Point(radius, turn * n)).on(loop(n - 1))
      loop(sides)

    // polygonPoints(10, 50).draw()


// ----- Exercise: Paths to Polygons ----- 
    def regularPolygon(sides: Int, radius: Double, color: Color) : Image =
      val turn = (360 /sides).degrees
      def loop(count: Int) : ClosedPath =
        count match 
          case 0 => ClosedPath.empty.moveTo(radius, 0.degrees)
          case n => loop(n-1).lineTo(radius, turn * n)

      Image.path(loop(sides)).strokeColor(color).strokeWidth(3.0)

      // regularPolygon(5, 100).draw()

// -----  Going Further... Concentric Polygons ----- 

    def concentricPolygons(count: Int, sides: Int, radius: Int, color: Color) : Image = 
      def loop(count: Int): Image =
        count match 
          case 0 => regularPolygon(sides, radius, color)
          case n => loop(n-1).on(regularPolygon(sides + n, radius + (20.0 * n), color.spin((5* n).degrees)))
      loop(count)

    // concentricPolygons(8, 4, 50, Color.pink).draw()
            
// -----  Polygon Spiral ----- 

    def polygonSpiral(count: Int, sides: Int, radius: Int, color: Color): Image =
      def loop(count: Int): Image =
        count match 
          case 0 => regularPolygon(sides, radius, color)
          case n => loop(n-1).on(regularPolygon(sides, radius +(20.0 * n), color.spin((n % 2 * 180).degrees))).rotate((n * 0.5).degrees)
      loop(count)

   // polygonSpiral(8, 4, 50, Color.red).draw()

//  ----- Star Polygons ----- 

    def starPolygons(sides: Int, radius: Int, skip: Int) : Image =
      val turn = (360 / sides).degrees
      def loop(count: Int): ClosedPath =
        count match 
          case 0 => ClosedPath.empty.moveTo(radius, 0.degrees)
          case n => loop(n-1).lineTo(radius, turn * n * skip)

      Image.path(loop(sides))

    //starPolygons(20, 50, 9).draw()

    
//  ----- Curvygons ----- 

//skipped

//----------------------------------------------------------- Functions and Flowers --------------------------------------------------------------

//  ----- Exercise: Function Literals ----- 


//(x : Int) => Math.pow(x, 2)
//(x : Int) => x * x

//(color: Color) => color.spin(15.degrees)

// (image : Image) => 
//   image.beside(image.rotate(90.degrees))
//     .beside(image.rotate(180.degrees))
//     .beside(image.rotate(-90.degrees))
//     .beside(image)

//  ----- Fun with Functions ----- 



def fold(count: Int, build: (Int, Image) => Image): Image =
  count match 
    case 0 => Image.empty
    case n => build(n, fold(count - 1, build))

val gradientBoxes: (Int, Image) => Image =
  (count, image) =>
    Image
      .square(50)
      .fillColor(Color.royalBlue.spin(10.degrees * count))
      .noStroke
      .beside(image)

fold(5, gradientBoxes)


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

enum Pitch:
  val referenceFrequency: Double = 440.0 // A4 frequency in Hz
  val ratio: Double = Math.pow(2, 1.0 / 12.0) // Ratio between semitones
  def calculateFrequency(referenceFrequency: Double, steps: Int) : Double =
    steps match
      case 0 => referenceFrequency
      case n => referenceFrequency * Math.pow(ratio, steps)
  case C, D, E, F, G, A, B








   
  

   
  


