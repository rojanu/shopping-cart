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
    Future {
      val itemGroups = items.groupBy(item => item.price).toSeq.sortBy(_._1).toArray
      if (itemGroups.length > 2) {
        // TODO: Implement when there are more than two items are in the same Compound Offer
        ???
      } else if (itemGroups.length == 2) {
        val cheaperItems = itemGroups(0)._2
        val expensiveItems = itemGroups(1)._2

        if (cheaperItems.size > expensiveItems.size) {
          offerTotalPrice(expensiveItems.size, expensiveItems.head.price) +
            offerTotalPrice(cheaperItems.size - expensiveItems.size, cheaperItems.head.price)
        } else if (cheaperItems.size < expensiveItems.size) {
          offerTotalPrice(cheaperItems.size, expensiveItems.head.price) +
            offerTotalPrice(expensiveItems.size - cheaperItems.size, expensiveItems.head.price)
        } else {
          offerTotalPrice(cheaperItems.size + expensiveItems.size, expensiveItems.head.price)
        }
      } else {
        offerTotalPrice(items.size, items.head.price)
      }
    }
  }

  private def offerTotalPrice(size: Int, price: BigDecimal) = {
    (payFor * (size / get) + (size % get)) * price
  }
}
