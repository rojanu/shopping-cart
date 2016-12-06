package com.github.rojanu

class ShoppingCart(basket: Seq[Item]) {

  def total: BigDecimal = {
    basket.map(_.price).sum
  }
}
