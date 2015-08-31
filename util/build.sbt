// add scala-xml dependency when needed (for Scala 2.11 and newer) in a robust way
// this mechanism supports cross-version publishing
// taken from: http://github.com/scala/scala-module-dependency-sample
libraryDependencies := {
  val deps = CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 11)) =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.3",
        "org.scala-lang" % "scala-reflect" % "2.11.7"
      )
    case Some((2, 10)) =>
      // or just libraryDependencies.value if you don't depend on scala-swing
      libraryDependencies.value :+ ("org.scala-lang" % "scala-reflect" % "2.10.5")
  }
  deps ++ Seq(
    "org.reflections" % "reflections" % Common.REFLECTIONS_VERSION,
    "com.typesafe" % "config" % Common.TYPESAFE_CONFIG_VERSION
  )
}

unmanagedSourceDirectories in Compile += {
  val Some((_, majorVersion)) = CrossVersion.partialVersion(scalaVersion.value)
  println((sourceDirectory in Compile).value)
  (sourceDirectory in Compile).value / s"scala_2.$majorVersion"
}
