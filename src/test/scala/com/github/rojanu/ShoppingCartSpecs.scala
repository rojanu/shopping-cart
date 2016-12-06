package com.github.rojanu

import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpecs extends FlatSpec with Matchers {

  "Empty Cart" should "total to 0" in {
    val cart = new ShoppingCart(Seq.empty)
    cart.total shouldBe 0
  }
}
