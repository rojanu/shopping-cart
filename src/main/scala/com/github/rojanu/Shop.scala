package com.github.rojanu

object Shop {

  private val apple = Item("Apple", .60)
  private val banana = Item("Banana", .20)
  private val melon = Item("Melon", 1.00)
  private val orange = Item("Orange", .25)

  val stock: Map[String, Item] = Map(
    apple.name -> apple,
    banana.name -> banana,
    melon.name -> melon,
    orange.name -> orange
  )

  private val cheaperItemFreeQuantityOffer = CheaperItemFreeQuantityOffer(2, 1)
  private val quantityOfferThreeForTwo = QuantityOffer(3, 2)

  val offers: Map[Item, Offer] = Map(
    apple -> cheaperItemFreeQuantityOffer,
    banana -> cheaperItemFreeQuantityOffer,
    melon -> quantityOfferThreeForTwo,
    orange -> quantityOfferThreeForTwo
  )

  val compoundOfferItems: Map[Offer, Seq[Item]] = Map[Offer, Seq[Item]](
    cheaperItemFreeQuantityOffer -> Seq(apple, banana),
    quantityOfferThreeForTwo -> Seq(orange)
  ).withDefaultValue(Seq())
}
