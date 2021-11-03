package codingtest

import org.specs2.mutable.Specification

class MainSpec extends Specification {

  "Hello" should {
    "Do nothing" in {
      0 mustEqual 0
    }

    "Do nothing again" in {
      1 mustEqual 1
    }
  }
}
