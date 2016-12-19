package com.github.rojanu

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartSpecs extends FlatSpec with Matchers with ScalaFutures {

  "Empty ShoppingCart" should "subTotal to 0" in {
    val cart: ShoppingCart = new ShoppingCart
    cart.subTotal.futureValue shouldBe 0
  }

  "A shopping cart containing Apple, Orange" should "be charged £0.85 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Orange")
    cart.subTotal.futureValue shouldBe 0.85
    cart.total.futureValue shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange" should "be charged £0.85 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.addItem("Orange")
    cart.subTotal.futureValue shouldBe 1.45
    cart.total.futureValue shouldBe 0.85
  }

  "A shopping cart containing Apple, Apple, Orange, Apple" should "be discounted to £1.45 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.addItem("Orange")
    cart.addItem("Apple")
    cart.subTotal.futureValue shouldBe 2.05
    cart.total.futureValue shouldBe 1.45
  }

  "A shopping cart containing Apple, Apple, Orange, Orange" should "be discounted to £1.10 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.addItem("Orange")
    cart.addItem("Orange")
    cart.subTotal.futureValue shouldBe 1.70
    cart.total.futureValue shouldBe 1.10
  }

  "A shopping cart containing Apple, Apple, Orange, Apple, Orange, Orange" should "be discounted to £1.70 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.addItem("Orange")
    cart.addItem("Apple")
    cart.addItem("Orange")
    cart.addItem("Orange")
    cart.subTotal.futureValue shouldBe 2.55
    cart.total.futureValue shouldBe 1.70
  }

  "A shopping cart containing Apple, Apple, Orange, Orange, Apple, Apple" should "be discounted to £1.70 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.addItem("Orange")
    cart.addItem("Orange")
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.subTotal.futureValue shouldBe 2.90
    cart.total.futureValue shouldBe 1.70
  }

  "A shopping cart containing Apple, Banana" should "be charged £0.60 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Banana")
    cart.subTotal.futureValue shouldBe 0.80
    cart.total.futureValue shouldBe 0.60
  }

  "A shopping cart containing Apple, Banana, Apple, Banana" should "be charged £1.20 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Banana")
    cart.addItem("Apple")
    cart.addItem("Banana")
    cart.subTotal.futureValue shouldBe 1.60
    cart.total.futureValue shouldBe 1.20
  }

  "A shopping cart containing Apple, Banana, Banana" should "be charged £0.80 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Banana")
    cart.addItem("Banana")
    cart.subTotal.futureValue shouldBe 1.00
    cart.total.futureValue shouldBe 0.80
  }

  "A shopping cart containing Apple, Banana, Banana, Banana" should "be charged £0.80 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Banana")
    cart.addItem("Banana")
    cart.addItem("Banana")
    cart.subTotal.futureValue shouldBe 1.20
    cart.total.futureValue shouldBe 0.80
  }

  "A shopping cart containing Apple, Banana, Apple" should "be charged £1.20 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Banana")
    cart.addItem("Apple")
    cart.subTotal.futureValue shouldBe 1.40
    cart.total.futureValue shouldBe 1.20
  }

  "A shopping cart containing Apple, Banana, Apple, Apple" should "be charged £1.20 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Banana")
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.subTotal.futureValue shouldBe 2.00
    cart.total.futureValue shouldBe 1.20
  }

  "A shopping cart containing Apple, Melon" should "be charged £1.60 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Melon")
    cart.subTotal.futureValue shouldBe 1.60
    cart.total.futureValue shouldBe 1.60
  }

  "A shopping cart containing Apple, Apple, Melon, Apple, Melon, Melon" should "be discounted to £3.20 pence at checkout" in {
    val cart = new ShoppingCart
    cart.addItem("Apple")
    cart.addItem("Apple")
    cart.addItem("Melon")
    cart.addItem("Apple")
    cart.addItem("Melon")
    cart.addItem("Melon")
    cart.subTotal.futureValue shouldBe 4.80
    cart.total.futureValue shouldBe 3.20
  }

  "A shopping card" should "calculate a running total as items are scanned" in {
    val cart = new ShoppingCart
    cart.addItem("Apple").futureValue match {
      case (subTotal, total) => {
        subTotal shouldBe .60
        total shouldBe .60
      }
    }

    cart.addItem("Apple").futureValue match {
      case (subTotal, total) => {
        subTotal shouldBe 1.20
        total shouldBe .60
      }
    }

    cart.addItem("Melon").futureValue match {
      case (subTotal, total) => {
        subTotal shouldBe 2.20
        total shouldBe 1.60
      }
    }

    cart.addItem("Apple").futureValue match {
      case (subTotal, total) => {
        subTotal shouldBe 2.80
        total shouldBe 2.20
      }
    }

    cart.addItem("Melon").futureValue match {
      case (subTotal, total) => {
        subTotal shouldBe 3.80
        total shouldBe 3.20
      }
    }

    cart.addItem("Melon").futureValue match {
      case (subTotal, total) => {
        subTotal shouldBe 4.80
        total shouldBe 3.20
      }
    }

    cart.subTotal.futureValue shouldBe 4.80
    cart.total.futureValue shouldBe 3.20
  }
}
