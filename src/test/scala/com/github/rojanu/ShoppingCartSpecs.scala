package com.github.rojanu

import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpecs extends FlatSpec with Matchers {

  "Empty Cart" should "total to 0" in {
    new ShoppingCart(Seq.empty).total shouldBe 0
  }

  "A shopping cart containing Apple, Orange" should "be charged £0.85 pence at checkout" in {
    new ShoppingCart(Seq(Apple, Orange)).total shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange" should "be charged £0.85 pence at checkout" in {
    new ShoppingCart(Seq(Apple, Apple, Orange)).total shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange, Apple" should "be discounted to £1.45 pence at checkout" in {
    new ShoppingCart(Seq(Apple, Apple, Orange, Apple)).total shouldBe 1.45
  }

  "A shopping cart containing Apple, Apple, Orange, Orange" should "be discounted to £1.10 pence at checkout" in {
    new ShoppingCart(Seq(Apple, Apple, Orange, Orange)).total shouldBe 1.10
  }

  "A shopping cart containing Apple, Apple, Orange, Apple, Orange, Orange" should "be discounted to £1.70 pence at checkout" in {
    new ShoppingCart(Seq(Apple, Apple, Orange, Apple, Orange, Orange)).total shouldBe 1.70
  }

  "A shopping cart containing Apple, Apple, Orange, Orange, Apple, Apple" should "be discounted to £1.70 pence at checkout" in {
    new ShoppingCart(Seq(Apple, Apple, Orange, Orange, Apple, Apple)).total shouldBe 1.70
  }
}
