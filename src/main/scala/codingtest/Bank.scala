package codingtest

class Bank(customers: Map[String, BigDecimal], val totalBalance: BigDecimal) {

  def deposit(depositAmt: BigDecimal, customerId: String): Either[AppError, Bank] = {

    val newCustomerBalance = customers.get(customerId) match {
      case Some(balance) => Right(balance + depositAmt)
      case None => Left(AppError("Customer not found."))
    }

    val newCustomers = newCustomerBalance.map(customers.updated(customerId, _))
    newCustomers.map(new Bank(_, totalBalance + depositAmt))
  }

  def withdraw(withdrawAmt: BigDecimal, customerId: String): Either[AppError, Bank] = {

    val newCustomerBalance = customers.get(customerId) match {
      case Some(balance) => Right(balance - withdrawAmt)
      case None => Left(AppError("Customer not found."))
    }

    val validCustomerBalance = newCustomerBalance.flatMap(maybeNegativeBalance => {
      if (maybeNegativeBalance < 0)
      Left(AppError("Cannot withdraw more than balance."))
      else Right(maybeNegativeBalance)
    })

    val newCustomers = validCustomerBalance.map(customers.updated(customerId, _))
    newCustomers.map(new Bank(_, totalBalance - withdrawAmt))
  }

  def checkBalance(customerId: String): Either[AppError, BigDecimal] = {
    customers.get(customerId) match {
      case Some(balance) => Right(balance)
      case None => Left(AppError("Customer not found."))
    }
  }

  def getTotal(): BigDecimal = {
    customers.foldLeft(BigDecimal(0))((accumulator, item) => acc + item._2)
  }

}
