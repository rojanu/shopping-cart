package com.github.rojanu

class ShoppingCart(basket: Seq[Item]) {
  def total: BigDecimal = basket.groupBy(item => item).map(g => g._1.total(g._2.size)).sum
}
