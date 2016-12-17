package com.github.rojanu


class ShoppingCart(basket: Seq[Item]) {

  def total: BigDecimal = basket.foldLeft(BigDecimal(0))((sum, item) => sum + item.price)

  def subTotal: BigDecimal = basket.groupBy(item => item).map {
    case (item, items) => item.offers.map(_.toPay(items.size, item.price)).sum
  }.sum
}
