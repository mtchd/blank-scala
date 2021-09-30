package codingtest

case class Customer(bank: BigDecimal)

object Customer {

  def deposit(customer: Customer, money: BigDecimal): Customer = {
    Customer(customer.bank + money)
  }

  def withdraw(customer: Customer, money: BigDecimal): Either[AppError, Customer] = {

    val newBank = customer.bank - money

    if (newBank < 0.0) Left(AppError("Customer doesn't have enough money to withdraw")) else Right(Customer(newBank))
  }

  def checkBalance(customer: Customer): BigDecimal = {
    customer.bank
  }

}
