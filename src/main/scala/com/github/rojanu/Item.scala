package com.github.rojanu

sealed class Item {
  val price: BigDecimal = 0
}

object Apple extends Item {
  override val price: BigDecimal = .60
}

object Orange extends Item {
  override val price: BigDecimal = .25
}
