package com.github.rojanu

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ShoppingCart {

  private var basket: Seq[Item] = Seq.empty[Item]

  def addItem(name: String): Unit = basket = basket :+ Shop.stock(name)

  def subTotal: Future[BigDecimal] = Future(basket.foldLeft(BigDecimal(0))((sum, item) => sum + item.price))

  def total: Future[BigDecimal] = Future.sequence {
    basket.groupBy {
      item => {
        val compoundOfferItems = Shop.compoundOfferItems(Shop.offers(item))
        if (compoundOfferItems.contains(item)) {
          compoundOfferItems.head
        } else {
          item
        }
      }
    }.map {
      case (item, items) => Shop.offers(item).toPay(items)
    }
  }.map(_.sum)
}
