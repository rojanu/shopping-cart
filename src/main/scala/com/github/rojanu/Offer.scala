package com.github.rojanu

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Offer {
  def toPay(items: Seq[Item]): Future[BigDecimal]
}

case class QuantityOffer(get: Int, payFor: BigDecimal) extends Offer {
  override def toPay(items: Seq[Item]): Future[BigDecimal] = {
    Future((payFor * (items.size / get) + (items.size % get)) * items.head.price)
  }
}

case class CheaperItemFreeQuantityOffer(get: Int, payFor: BigDecimal) extends Offer {
  override def toPay(items: Seq[Item]): Future[BigDecimal] = {
    val itemGroups = items.groupBy(item => item.price).toSeq.sortBy(_._1).toArray
    if (itemGroups.length > 2) {
      Future(0)
    } else if (itemGroups.length == 2) {
      val cheaperItems = itemGroups(0)._2
      val expensiveItems = itemGroups(1)._2

      if (cheaperItems.size > expensiveItems.size) {
        val difference = cheaperItems.size - expensiveItems.size
        val compoundOfferPrice = (payFor * (expensiveItems.size / get) + (expensiveItems.size % get)) * expensiveItems.head.price
        val offerPrice = (payFor * (difference / get) + (difference % get)) * cheaperItems.head.price
        Future(compoundOfferPrice + offerPrice)
      } else if (cheaperItems.size == expensiveItems.size) {
        val size = cheaperItems.size + expensiveItems.size
        Future((payFor * (size / get) + (size % get)) * expensiveItems.head.price)
      } else {
        Future(0)
      }
    } else {
      Future((payFor * (items.size / get) + (items.size % get)) * items.head.price)
    }
  }
}
