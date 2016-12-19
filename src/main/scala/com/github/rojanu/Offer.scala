package com.github.rojanu

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Offer {
  def toPay(quantity: Int, price: BigDecimal): Future[BigDecimal]
}

case class QuantityOffer(get: Int, payFor: BigDecimal) extends Offer {
  override def toPay(quantity: Int, price: BigDecimal): Future[BigDecimal] = Future((payFor * (quantity / get) + (quantity % get)) * price)
}
