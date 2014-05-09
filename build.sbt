import AssemblyKeys._
 
assemblySettings
 
name := "slick-poc"
 
version := "0.0.1"
 
scalaVersion := "2.10.3"
 
scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature" )
 
lazy val akkaVersion = "2.2.3"
lazy val sprayVersion = "1.2-RC4"

val postgresDriver = "org.postgresql" % "postgresql" % "9.3-1101-jdbc41"
 
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
    "com.typesafe.slick" %% "slick" % "2.0.1",
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "org.squeryl" %% "squeryl" % "0.9.5-6", postgresDriver,
    "c3p0" % "c3p0" % "0.9.1.2", postgresDriver
)