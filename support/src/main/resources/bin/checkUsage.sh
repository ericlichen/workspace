#!/bin/bash
SCALA=`which scala`
exec $SCALA -cp ".:../libs/*" "$0" "$@"
!#

import com.cellos.data.support._

object HelloWorld {
  def main(args: Array[String]) {
    println("Hello, world! " + args.toList)
    val check1 = CheckMkCheck("a", 1, 2, 3, 0, 5)
    println(check1.name)
  }
}
