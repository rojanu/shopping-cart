package com.github.rojanu

object Shop {

  private val apple = Item("Apple", .60)
  private val orange = Item("Orange", .25)

  val stock: Map[String, Item] = Map(apple.name -> apple, orange.name -> orange)

  val offers: Map[Item, Offer] = Map(
    apple -> QuantityOffer(2, 1),
    orange -> QuantityOffer(3, 2)
  )
}
