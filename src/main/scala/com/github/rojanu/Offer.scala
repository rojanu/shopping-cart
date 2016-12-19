package com.github.rojanu

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Offer {
  def toPay(items: Seq[Item]): Future[BigDecimal]
}

case class QuantityOffer(get: Int, payFor: BigDecimal) extends Offer {
  override def toPay(items: Seq[Item]): Future[BigDecimal] = {
    Future((payFor * (items.size / get) + (items.size % get)) * items.head.price)
  }
}
