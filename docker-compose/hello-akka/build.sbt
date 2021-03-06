name := "hello-akka"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= {
  val akkaVersion = "2.5.3"
  Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http"  % "10.0.9",
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "org.slf4j" % "slf4j-log4j12" % "1.7.25"
  )
}

enablePlugins(DockerPlugin)

assemblyJarName in assembly := "monstradamuzz-hello-akka.jar"

imageNames in docker := Seq( ImageName("monstradamuzz/hello-akka:latest") )

dockerfile in docker := {
  val artifact: File = assembly.value
  val artifactTargetPath = s"/app/${artifact.name}"

  new Dockerfile {
    from("java")
    add(artifact, artifactTargetPath)
    entryPoint("java", "-jar")
    expose(8080)
    cmd(artifactTargetPath)
  }
}
