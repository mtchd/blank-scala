package codingtest

case class Customer(personal: BigDecimal, bank: BigDecimal)

object Customer {

  def deposit(customer: Customer, money: BigDecimal): Either[AppError, Customer] = {

    val newPersonal = customer.personal - money
    val newBank = customer.bank + money

    val outcome = if (newBank < 0.0) Left(AppError("Customer doesn't have enough money to deposit")) else Right(newPersonal)

    outcome.map(Customer(_, newBank))
  }

  def withdraw(customer: Customer, money: BigDecimal): Either[AppError, Customer] = {

    val newPersonal = customer.personal + money
    val newBank = customer.bank - money

    val outcome = if (newBank < 0.0) Left(AppError("Customer doesn't have enough money to withdraw")) else Right(newBank)

    outcome.map(Customer(newPersonal, _))
  }

  def checkBalance(customer: Customer): BigDecimal = {
    customer.personal + customer.bank
  }

}
