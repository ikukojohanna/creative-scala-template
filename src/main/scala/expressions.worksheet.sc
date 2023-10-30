// Creative Scala Exercises: Expressions - Expressive Expressions

import doodle.core.*
import doodle.image.*
import doodle.image.syntax.all.*
import doodle.image.syntax.core.*
import doodle.java2d.*
import doodle.reactor.*
import scala.concurrent.duration.*
import cats.effect.unsafe.implicits.global


// ----- Expressions ----- 

1 + 2
1 + 2

2 * 21

1 + 2 * 3
1 + (2 * 3)
(1 + 2) * 3

1 + 2
// 1 ? 2
4 / 2
1 / 2
1.0 / 2.0
// 1 / 0

//----- Expanding Expressions ----- 

1 < 2
(3 + 2) > 7
//(3 / 0) < 6




"""
  Be with me always—take any form—drive me mad! 
  only do not leave me in this abyss, where I cannot find you!"
""".substring(76, 98)

"hello" ++ " world !"
"hello".++(" world !")



// ----- Expressive Expressions-----

Image.circle(100).draw()
Image.rectangle(150,50).draw()
Image.triangle(100,50).draw()

//Layout 

(Image.circle(100).above(Image.circle(50))).draw()

(Image.circle(100).on(Image.circle(50))).draw()

(Image
  .circle(50).strokeColor(Color.blue).strokeWidth(3) 
  .beside(Image.circle(50).fillColor(Color.chartreuse)
  .beside(Image.circle(50).fillColor(Color.hotpink)))
  .on(Image.circle(150).fillColor(Color.hsl(0.degrees, 0.8, 0.6))))
  .draw()


//3 triangles

    
(Image
  .equilateralTriangle(80)
  .strokeWidth(10) 
  .fillColor(Color.blue.spin(30.degrees))
  .strokeColor(
    Color.blue
      .darken(0.2.normalized)
      .spin(30.degrees))
  .above(Image
    .equilateralTriangle(80)
    .strokeWidth(10) 
    .fillColor(Color.blue)
    .strokeColor(Color.blue.darken(0.2.normalized))
    .beside(Image
      .equilateralTriangle(80)
      .strokeWidth(10) 
      .fillColor(Color.blue.spin(15.degrees))
      .strokeColor(Color.blue
      .spin(15.degrees)
      .darken(0.2.normalized)))))
      .draw()

//compilation target

(Image
  .circle(30).fillColor(Color.red)
  .on(Image.circle(60)fillColor(Color.white))
  .on(Image.circle(90)fillColor(Color.red))
  .above(Image.rectangle(10,25).fillColor(Color.brown))
  .above(Image.rectangle(25,10).fillColor(Color.brown))
  .above(Image.rectangle(60,30).noStroke.fillColor(Color.darkGreen)))
  .draw()


      
    



