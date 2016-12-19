package com.github.rojanu

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ShoppingCart(basketItems: Seq[String]) {
  val basket: Seq[Item] = basketItems.map(Shop.stock(_))

  def subTotal: Future[BigDecimal] = Future(basket.foldLeft(BigDecimal(0))((sum, item) => sum + item.price))

  def total: Future[BigDecimal] = Future.sequence(basket.groupBy(item => item).map {
    case (item, items) => Future.sequence(item.offers.map(_.toPay(items.size, item.price)))
  }).map(_.map(_.sum).sum)
}
