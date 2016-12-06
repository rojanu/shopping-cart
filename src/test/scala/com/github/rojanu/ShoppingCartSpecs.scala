package com.github.rojanu

import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpecs extends FlatSpec with Matchers {

  "Empty Cart" should "total to 0" in {
    new ShoppingCart(Seq.empty).total shouldBe 0
  }

  "A shopping cart containing Apple, Orange" should "be charged £0.85 pence at checkout" in {
    new ShoppingCart(Seq(Apple, Orange)).total shouldBe 0.85
  }
}
