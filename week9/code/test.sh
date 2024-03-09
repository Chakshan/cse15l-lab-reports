#!/bin/bash

set -e

CPATH=".:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar"

javac -cp $CPATH *.java
java -cp $CPATH org.junit.runner.JUnitCore SorterTests
