package com.github.rojanu

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpecs extends FlatSpec with Matchers with ScalaFutures{

  private val apple = Item("Apple", .60, Seq(QuantityOffer(2, 1)))
  private val banana = Item("Banana", .20, Seq(QuantityOffer(2, 1)))
  private val orange = Item("Orange", .25, Seq(QuantityOffer(3, 2)))


  "Empty ShoppingCart" should "subTotal to 0" in {
    val cart: ShoppingCart = new ShoppingCart(Seq.empty)
    cart.subTotal.futureValue shouldBe 0
  }

  "A shopping cart containing Apple, Orange" should "be charged £0.85 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Orange"))
    cart.subTotal.futureValue shouldBe 0.85
    cart.total.futureValue shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange" should "be charged £0.85 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Apple", "Orange"))
    cart.subTotal.futureValue shouldBe 1.45
    cart.total.futureValue shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange, Apple" should "be discounted to £1.45 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Apple", "Orange", "Apple"))
    cart.subTotal.futureValue shouldBe 2.05
    cart.total.futureValue shouldBe 1.45
  }

  "A shopping cart containing Apple, Apple, Orange, Orange" should "be discounted to £1.10 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Apple", "Orange", "Orange"))
    cart.subTotal.futureValue shouldBe 1.70
    cart.total.futureValue shouldBe 1.10
  }

  "A shopping cart containing Apple, Apple, Orange, Apple, Orange, Orange" should "be discounted to £1.70 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Apple", "Orange", "Apple", "Orange", "Orange"))
    cart.subTotal.futureValue shouldBe 2.55
    cart.total.futureValue shouldBe 1.70
  }

  "A shopping cart containing Apple, Apple, Orange, Orange, Apple, Apple" should "be discounted to £1.70 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Apple", "Orange", "Orange", "Apple", "Apple"))
    cart.subTotal.futureValue shouldBe 2.90
    cart.total.futureValue shouldBe 1.70
  }
}
