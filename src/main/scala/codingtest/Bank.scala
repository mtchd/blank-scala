package codingtest

case class Bank(customers: Map[String, BigDecimal]) {

  def deposit(customerId: String, depositAmt: BigDecimal): Either[AppError, Bank] = {
    this
      .getBalance(customerId)
      .map(_ + depositAmt)
      .map(this.updated(customerId, _))
  }

  def withdraw(customerId: String, withdrawAmt: BigDecimal): Either[AppError, Bank] = {
    this
      .getBalance(customerId)
      .flatMap(balance => {
        val newBalance = balance - withdrawAmt
        newBalance < 0 match {
          case true => Left(InsufficientFunds(customerId, balance))
          case false => Right(newBalance)
        }
      })
      .map(this.updated(customerId, _))
  }

  def getBalance(customerId: String): Either[AppError, BigDecimal] = {
    customers.get(customerId) match {
      case Some(balance) => Right(balance)
      case None => Left(CustomerNotFound(customerId))
    }
  }

  def getTotal(): BigDecimal = {
    customers.foldLeft(BigDecimal(0))((accumulator, customer) => accumulator + customer._2)
  }

  private def updated(customerId: String, balance: BigDecimal): Bank = {
    Bank(customers.updated(customerId, balance))
  }

}
