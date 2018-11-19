name := "finch-samples"

version := "0.1"

scalaVersion := "2.12.7"

val finchVersion = "0.24.0"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % finchVersion,
  "com.github.finagle" %% "finch-circe" % finchVersion,
)
