package com.github.rojanu

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpecs extends FlatSpec with Matchers with ScalaFutures{

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

  "A shopping cart containing Apple, Banana" should "be charged £0.60 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Banana"))
    cart.subTotal.futureValue shouldBe 0.80
    cart.total.futureValue shouldBe 0.60
  }

  "A shopping cart containing Apple, Banana, Apple, Banana" should "be charged £1.20 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Banana", "Apple", "Banana"))
    cart.subTotal.futureValue shouldBe 1.60
    cart.total.futureValue shouldBe 1.20
  }

  "A shopping cart containing Apple, Banana, Banana" should "be charged £0.80 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Banana", "Banana"))
    cart.subTotal.futureValue shouldBe 1.00
    cart.total.futureValue shouldBe 0.80
  }

  "A shopping cart containing Apple, Banana, Banana, Banana" should "be charged £0.80 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Banana", "Banana", "Banana"))
    cart.subTotal.futureValue shouldBe 1.20
    cart.total.futureValue shouldBe 0.80
  }

  "A shopping cart containing Apple, Banana, Apple" should "be charged £1.20 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Banana", "Apple"))
    cart.subTotal.futureValue shouldBe 1.40
    cart.total.futureValue shouldBe 1.20
  }

  "A shopping cart containing Apple, Banana, Apple, Apple" should "be charged £1.20 pence at checkout" in {
    val cart = new ShoppingCart(Seq("Apple", "Banana", "Apple", "Apple"))
    cart.subTotal.futureValue shouldBe 2.00
    cart.total.futureValue shouldBe 1.20
  }
}
