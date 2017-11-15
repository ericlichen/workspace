#!/bin/bash
SCALA=`which scala`
exec $SCALA -cp ../libs/* "$0" "$@"
!#
object HelloWorld {
  def main(args: Array[String]) {
    println("Hello, world! " + args.toList)
  }
}
