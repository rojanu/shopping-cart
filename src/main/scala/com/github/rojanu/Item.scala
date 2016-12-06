package com.github.rojanu

sealed class Item {
  val price: BigDecimal = 0
  val offers: Seq[Offer] = Seq.empty
  def total(quantity: Int): BigDecimal = offers.map(_.toPay(quantity, price)).sum
}

object Apple extends Item {
  override val price: BigDecimal = .60
  override val offers: Seq[Offer] = Seq(QuantityOffer(2,1))
}

object Orange extends Item {
  override val price: BigDecimal = .25
  override val offers: Seq[Offer] = Seq(QuantityOffer(3,2))
}
