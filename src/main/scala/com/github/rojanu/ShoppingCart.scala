package com.github.rojanu

class ShoppingCart(basketItems: Seq[String]) {
  val basket: Seq[Item] = basketItems.map(Shop.stock(_))

  def subTotal: BigDecimal = basket.foldLeft(BigDecimal(0))((sum, item) => sum + item.price)

  def total: BigDecimal = basket.groupBy(item => item).map {
    case (item, items) => item.offers.map(_.toPay(items.size, item.price)).sum
  }.sum
}
