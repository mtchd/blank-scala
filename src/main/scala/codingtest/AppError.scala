package codingtest

sealed trait AppError
case class InsufficientFunds(customerId: String, balance: BigDecimal) extends AppError
case class CustomerNotFound(customerId: String) extends AppError
