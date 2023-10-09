scalaVersion := "3.3.0"

console / initialCommands := """
      |import doodle.core.*
      |import doodle.image.*
      |import doodle.image.syntax.all.*
      |import doodle.image.syntax.core.*
      |import doodle.java2d.*
      |import cats.effect.unsafe.implicits.global
    """.trim.stripMargin



libraryDependencies ++= Seq("org.typelevel" %% "cats-effect" % "3.5.1",
        "org.creativescala" %% "doodle" % "0.20.0")
