import org.scalatest.FlatSpec
import collection.mutable.Stack
import com.cellos.data.support._

class CheckMkConnectSpec extends FlatSpec {
 
 "A CheckMkCheck" should "initialized with value" in {
    val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=2, warn=4, crit=6, min=1, max=10)
    assert(check.checkValue === 2)
  }

 it should "throw IllegalArgumentException if check name has space" in {
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="total Bytes", varName="speed", checkValue=2, warn=4, crit=6, min=1, max=10)
    }
  }
}
