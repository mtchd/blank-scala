package codingtest

class Bank(customers: Map[String, BigDecimal]) {

  def deposit(depositAmt: BigDecimal, customerId: String): Either[AppError, Bank] = {

    val newCustomerBalance = customers.get(customerId) match {
      case Some(balance) => Right(balance + depositAmt)
      case None => Left(AppError("Customer not found."))
    }

    val newCustomers = newCustomerBalance.map(customers.updated(customerId, _))
    newCustomers.map(new Bank(_))
  }

  def withdraw(withdrawAmt: BigDecimal, customerId: String): Either[AppError, Bank] = {

    val newCustomerBalance = customers.get(customerId) match {
      case Some(balance) => Right(balance + withdrawAmt)
      case None => Left(AppError("Customer not found."))
    }

    val validCustomerBalance = newCustomerBalance.flatMap(maybeNegativeBalance => {
      if (maybeNegativeBalance < 0) Left(AppError("Cannot withdraw more than balance."))
      else Right(maybeNegativeBalance)
    })

    val newCustomers = validCustomerBalance.map(customers.updated(customerId, _))
    newCustomers.map(new Bank(_))
  }

  def checkBalance(customerId: String): Either[AppError, BigDecimal] = {
    customers.get(customerId) match {
      case Some(value) => Right(value)
      case None => Left(AppError("Customer not found."))
    }
  }
}
