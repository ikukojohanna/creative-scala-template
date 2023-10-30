// Creative Scala Exercises: Writing Larger Programs - Methods without madness

import doodle.core.*
import doodle.image.*
import doodle.image.syntax.all.*
import doodle.image.syntax.core.*
import doodle.java2d.*
import doodle.reactor.*
import scala.concurrent.duration.*
import cats.effect.unsafe.implicits.global
import example.*


object Example {

  val image =
    Image
      .circle(100)
      .fillColor(Color.red)
      .on(Image.circle(200).fillColor(Color.red))
      .on(Image.circle(300).fillColor(Color.steelBlue))

  val animation =
    Reactor
      .init(0.degrees)
      .withOnTick(a => a + 1.degrees)
      .withStop(a => a > 360.degrees)
      .withTickRate(20.millis)
      .withRender { a =>
        val location = Point(200, a)
        val planet = Image.circle(40.0).noStroke.fillColor(Color.seaGreen)
        val moon = Image
          .circle(10.0)
          .noStroke
          .fillColor(Color.slateGray)
          .at(Point(60, a * 5))

        moon.on(planet).at(location)
      }

  val frame = Frame.default.withSize(600, 600).withCenterAtOrigin

// naming target
    
  val disc = 
    Image
    .circle(30).fillColor(Color.red)
    .on(Image.circle(60)fillColor(Color.white))
    .on(Image.circle(90)fillColor(Color.red))

  val stand =
    Image
    .rectangle(10,25).fillColor(Color.brown)
    .above(Image.rectangle(25,10).fillColor(Color.brown))
  
  val ground = 
    Image
    .rectangle(60,30).noStroke.fillColor(Color.darkGreen)

  val target =
    disc.above(stand.above(ground))

  // writing Methods

  def boxes(color: Color): Image = {
    val box =
      Image.rectangle(40, 40).
        strokeWidth(5.0).
        strokeColor(color.spin(30.degrees)).
        fillColor(color)

    box.beside(box).beside(box).beside(box).beside(box)
  }

// Exercise: Gradient Boxes

  def box(color: Color, angle: Angle) : Image = 
    Image.rectangle(40, 40)
      .fillColor(color.spin(angle))
  

  def gradientBoxes(color: Color) : Image =
    box(color, 0.degrees)
      .beside(box(color, 15.degrees))
      .beside(box(color, 30.degrees))
      .beside(box(color, 45.degrees))
      .beside(box(color, 60.degrees))
    



// Exercise: Gradient Concentric Circles 
  def circle(color: Color, n : Int): Image =
    Image.circle(50 +( n * 10))
    .fillColor(color.spin((15 * n).degrees))

  def gradientCircles(color: Color) : Image =
    circle(color, 1)
    .on(circle(color, 2))
    .on(circle(color, 3))
    .on(circle(color, 4))
    .on(circle(color, 5))

  @main def go(): Unit = {
    // image.draw()
    // target.draw()
    // animation.run(frame)
    // boxes(Color.mistyRose).draw()
    //gradientBoxes(Color.pink).draw()
    //gradientCircles(Color.pink).draw()
      
  }
}
