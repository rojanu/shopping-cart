package com.github.rojanu

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ShoppingCart(basketItems: Seq[String]) {

  val basket: Seq[Item] = basketItems.map(Shop.stock(_))

  def subTotal: Future[BigDecimal] = Future(basket.foldLeft(BigDecimal(0))((sum, item) => sum + item.price))

  def total: Future[BigDecimal] = Future.sequence(basket.groupBy(item => item).map {
    case (item, items) =>
      Shop.offers(item).toPay(items)
  }).map(_.sum)
}
