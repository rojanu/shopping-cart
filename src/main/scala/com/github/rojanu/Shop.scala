package com.github.rojanu

object Shop {

  private val apple = Item("Apple", .60, Seq(QuantityOffer(2, 1)))
  private val orange = Item("Orange", .25, Seq(QuantityOffer(3, 2)))

  val stock: Map[String, Item] = Map(apple.name -> apple, orange.name -> orange)
}
