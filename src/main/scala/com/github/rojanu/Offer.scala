package com.github.rojanu

trait Offer {
  def toPay(quantity: Int, price: BigDecimal): BigDecimal
}

case class QuantityOffer(get: Int, payFor: Int) extends Offer {
  override def toPay(quantity: Int, price: BigDecimal): BigDecimal = (payFor * (quantity / get) + (quantity % get)) * price
}
