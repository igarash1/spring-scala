# Spring Scala

The goal of Spring Scala is to make it easier to use the Spring framework in Scala.

Currently, the two main areas of focus are:

* Wiring up Scala classes as Spring Beans, both in traditional [XML](https://github.com/SpringSource/spring-scala/wiki/Defining-Scala-Beans-in-Spring-XML) as well as [Scala](https://github.com/SpringSource/spring-scala/wiki/Functional-Bean-Configuration)
* Provide [Scala-friendly](https://github.com/SpringSource/spring-scala/wiki/Using-Spring-Templates-in-Scala) wrappers for the Spring templates

For more information, please refer to the [spring-scala JIRA site](https://jira.springsource.org/browse/SCALA), or e-mail [Paul Snively](mailto:psnively@mac.com).

## Installation

spring-scala snapshots and releases are publishes to the Sonatype snapshot and release repositories. As of this writing (August 2015), the following versions of Spring are supported:

* Spring 3.2.10
* Spring 3.2.14
* Spring 4.0.9
* Spring 4.1.7
* Spring 4.2.0

All artifacts are cross-published for Scala 2.10.x and 2.11.x, and digitally signed by Paul Snively, fingerprint 3002 0815 C339 E64E 9698  698A 2FB0 1967 4C7B 02B4.

An example sbt dependency:

```scala
libraryDependencies += "org.psnively" %% "spring_scala_3-2-10" % "1.0.0"
```

adds a dependency on spring-scala version 1.0.0 built with Spring 3.2.10 for whatever the project's scalaVersion is.

## Building from Source

Spring Scala uses a [sbt](http://www.scala-sbt.org)-based build system.
In the instructions below, [`sbt`](http://vimeo.com/34436402) is assumed to be on your $PATH.
The only prerequisites are [darcs](http://darcs.net) and JDK 1.7+.

### check out sources
darcs clone http://hub.darcs.net/psnively/spring-scala

### compile and test, build all jars, distribution zips and docs
sbt ';so test ;much package'           # Thanks to sbt-doge for cross-building across subprojects!

### install all spring-\* jars into your local Ivy cache
sbt 'very publish-local'

... and discover more commands with `sbt tasks`. See also the [sbt FAQ](http://www.scala-sbt.org/0.13.0/docs/faq.html).

## Documentation

The Pivotal spring-scala wiki has not been migrated and is unlikely to be, so please add your questions/issues to the issue tracker.

## Issue Tracking

Spring Scala uses [JIRA](https://jira.springsource.org/browse/SCALA) for issue tracking purposes

## License

Spring Scala is [Apache 2.0 licensed](http://www.apache.org/licenses/LICENSE-2.0.html).
