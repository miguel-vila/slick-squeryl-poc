import AssemblyKeys._
 
assemblySettings
 
name := "slick-poc"
 
version := "0.0.1"
 
scalaVersion := "2.10.3"
 
scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature" )
 
lazy val akkaVersion = "2.2.3"
lazy val sprayVersion = "1.2-RC4"
 
resolvers ++= Seq(
    "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",   
    "Sonatype Releases"  at "http://oss.sonatype.org/content/repositories/releases",
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Spray.io repository" at "http://repo.spray.io"
)
 
libraryDependencies ++= Seq(
    "com.typesafe.akka"     %% "akka-actor"       % akkaVersion,
    "com.typesafe.akka"     %% "akka-slf4j"           % akkaVersion,
    "com.typesafe.akka"     %% "akka-testkit"         % akkaVersion,
    "com.typesafe.slick" %% "slick" % "2.0.1"
)