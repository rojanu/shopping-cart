package com.github.rojanu

object Shop {

  private val apple = Item("Apple", .60)
  private val banana = Item("Banana", .20)
  private val orange = Item("Orange", .25)

  val stock: Map[String, Item] = Map(
    apple.name -> apple,
    orange.name -> orange,
    banana.name -> banana
  )

  private val cheaperItemFreeQuantityOffer = CheaperItemFreeQuantityOffer(2, 1)
  private val quantityOfferThreeForTwo = QuantityOffer(3, 2)

  val offers: Map[Item, Offer] = Map(
    apple -> cheaperItemFreeQuantityOffer,
    banana -> cheaperItemFreeQuantityOffer,
    orange -> quantityOfferThreeForTwo
  )

  val compoundOfferItems: Map[Offer, Seq[Item]] = Map[Offer, Seq[Item]](
    cheaperItemFreeQuantityOffer -> Seq(apple, banana),
    quantityOfferThreeForTwo -> Seq(orange)
  ).withDefaultValue(Seq())
}
