import java.io.File

crossScalaVersions := Seq("2.11.7", "2.10.5")

releaseCrossBuild := true

autoAPIMappings := true

releaseVcs := Darcs(baseDirectory.value)

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

def springDependencies(springVersion: String): Seq[ModuleID] = Seq(
  "org.springframework"          %  "spring-core"             % springVersion,
  "org.springframework"          %  "spring-beans"            % springVersion,
  "org.springframework"          %  "spring-context"          % springVersion,
  "org.springframework"          %  "spring-aop"              % springVersion          % "optional",
  "org.springframework"          %  "spring-core"             % springVersion          % "optional",
  "org.springframework"          %  "spring-beans"            % springVersion          % "optional",
  "org.springframework"          %  "spring-context"          % springVersion          % "optional",
  "org.springframework"          %  "spring-jdbc"             % springVersion          % "optional",
  "org.springframework"          %  "spring-jms"              % springVersion          % "optional",
  "org.springframework"          %  "spring-web"              % springVersion          % "optional",
  "org.springframework"          %  "spring-test"             % springVersion          % "optional",
  "org.springframework"          %  "spring-aspects"          % springVersion          % "test"
)

lazy val commonSettings = Seq(
  organization := "org.psnively",
  sourceDirectory := baseDirectory.value / ".." / "src",
  unmanagedSourceDirectories in Compile += (sourceDirectory in Compile).value / ("scala-" +
    scalaVersion.value.split('.').take(2).mkString(".")),
  javacOptions ++= Seq("-source", "1.7", "-target", "1.7", "-Xlint:-options"),
  scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:reflectiveCalls"),
  libraryDependencies ++= Seq(
      "org.scala-lang"               %  "scala-reflect"           % scalaVersion.value,
      "com.fasterxml.jackson.module" %% "jackson-module-scala"    % "2.6.0-1"                    % "optional",
      "org.apache.geronimo.specs"    %  "geronimo-jms_1.1_spec"   % "1.1.1"                      % "provided",
      "javax.servlet"                %  "servlet-api"             % "2.5"                        % "provided",
      "javax.inject"                 %  "javax.inject"            % "1"                          % "provided",
      "org.scalatest"                %% "scalatest"               % "2.2.5"                      % "test",
      "junit"                        %  "junit"                   % "4.12"                       % "test",
      "org.hsqldb"                   %  "hsqldb"                  % "2.3.3"                      % "test",
      "log4j"                        %  "log4j"                   % "1.2.17"                     % "test"
    ),
  libraryDependencies := {
    CrossVersion.partialVersion(scalaVersion.value) match {
      // if scala 2.11+ is used, add dependency on scala-xml module
      case Some((2, scalaMajor)) if scalaMajor >= 11 =>
        libraryDependencies.value ++ Seq(
          "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
        )
      case _ =>
        // or just libraryDependencies.value if you don't depend on scala-swing
        libraryDependencies.value
    }
  },
  parallelExecution in Test := false,
  externalDependencyClasspath in Test += new File(System.getProperty("java.home"), "lib" + File.separator + "resources.jar"),
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  pomExtra := (
    <url>http://hub.darcs.net/psnively/spring-scala</url>
    <licenses>
      <license>
        <name>Apache 2.0</name>
        <url>http://opensource.org/licenses/Apache-2.0</url>
      </license>
    </licenses>
    <scm>
      <url>http://hub.darcs.net/psnively/spring-scala</url>
      <connection>http://hub.darcs.net/psnively/spring-scala</connection>
    </scm>
    <developers>
      <developer>
        <id>psnively</id>
        <name>Paul Snively</name>
        <url>http://hub.darcs.net/psnively</url>
      </developer>
    </developers>
  )
)

lazy val root = (project in file(".")).
  settings(
    (compile in Compile) := inc.Analysis.Empty,
    (compile in Test) := inc.Analysis.Empty,
    publishLocal := {},
    publish := {},
    sources in (Compile,doc) := Seq.empty,
    publishArtifact in (Compile, packageBin) := false,
    publishArtifact in (Compile, packageSrc) := false,
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Test, packageBin) := false
  ).
  aggregate(spring_scala_3_2_10, spring_scala_3_2_14, spring_scala_4_0_9, spring_scala_4_1_7, spring_scala_4_2_0)

lazy val spring_scala_3_2_10 = project.
  settings(commonSettings: _*).
  settings(
    name := "spring_scala_3.2.10",
    libraryDependencies ++= springDependencies("3.2.10.RELEASE"),
    unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "src" / "main" / "spring-pre-4.1"
  )

lazy val spring_scala_3_2_14 = project.
  settings(commonSettings: _*).
  settings(
    name := "spring_scala_3.2.14",
    libraryDependencies ++= springDependencies("3.2.14.RELEASE"),
    unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "src" / "main" / "spring-pre-4.1"
  )

lazy val spring_scala_4_0_9 = project.
  settings(commonSettings: _*).
  settings(
    name := "spring_scala_4.0.9",
    libraryDependencies ++= springDependencies("4.0.9.RELEASE"),
    unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "src" / "main" / "spring-pre-4.1"
  )

lazy val spring_scala_4_1_7 = project.
  settings(commonSettings: _*).
  settings(
    name := "spring_scala_4.1.7",
    libraryDependencies ++= springDependencies("4.1.7.RELEASE"),
    unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "src" / "main" / "spring-post-4.1"
 )

lazy val spring_scala_4_2_0 = project.
  settings(commonSettings: _*).
  settings(
    name := "spring_scala_4.2.0",
    libraryDependencies ++= springDependencies("4.2.0.RELEASE"),
    unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "src" / "main" / "spring-post-4.1"
  )
