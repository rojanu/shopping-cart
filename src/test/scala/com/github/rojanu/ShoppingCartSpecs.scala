package com.github.rojanu

import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpecs extends FlatSpec with Matchers {

  private val apple = Item("Apple", .60, Seq(QuantityOffer(2, 1)))
  private val orange = Item("Orange", .25, Seq(QuantityOffer(3, 2)))


  "Empty ShoppingCart" should "total to 0" in {
    val till: ShoppingCart = new ShoppingCart(Seq.empty)
    till.total shouldBe 0
  }

  "A shopping cart containing Apple, Orange" should "be charged £0.85 pence at checkout" in {
    val cart = new ShoppingCart(Seq(apple, orange))
    cart.total shouldBe 0.85
    cart.subTotal shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange" should "be charged £0.85 pence at checkout" in {
    val cart = new ShoppingCart(Seq(apple, apple, orange))
    cart.total shouldBe 1.45
    cart.subTotal shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange, Apple" should "be discounted to £1.45 pence at checkout" in {
    val cart = new ShoppingCart(Seq(apple, apple, orange, apple))
    cart.total shouldBe 2.05
    cart.subTotal shouldBe 1.45
  }

  "A shopping cart containing Apple, Apple, Orange, Orange" should "be discounted to £1.10 pence at checkout" in {
    val cart = new ShoppingCart(Seq(apple, apple, orange, orange))
    cart.total shouldBe 1.70
    cart.subTotal shouldBe 1.10
  }

  "A shopping cart containing Apple, Apple, Orange, Apple, Orange, Orange" should "be discounted to £1.70 pence at checkout" in {
    val cart = new ShoppingCart(Seq(apple, apple, orange, apple, orange, orange))
    cart.total shouldBe 2.55
    cart.subTotal shouldBe 1.70
  }

  "A shopping cart containing Apple, Apple, Orange, Orange, Apple, Apple" should "be discounted to £1.70 pence at checkout" in {
    val cart = new ShoppingCart(Seq(apple, apple, orange, orange, apple, apple))
    cart.total shouldBe 2.90
    cart.subTotal shouldBe 1.70
  }
}
