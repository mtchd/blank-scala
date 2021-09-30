package codingtest

case class Customer(personal: BigDecimal, bank: BigDecimal)
case class AppError(message: String)

object Customer {

  def deposit(customer: Customer, money: BigDecimal): Either[AppError, Customer] = {

    val personal_after_deposit = customer.personal - money
    val bank = customer.bank + money

    val personal_money = if (personal_after_deposit < 0.0) Left(AppError("Customer doesn't have enough money to deposit")) else Right(personal_after_deposit)

    personal_money.map(personal => Customer(personal, bank))
  }

  def withdraw(customer: Customer, money: BigDecimal): Either[AppError, Customer] = {

    val personal = customer.personal + money
    val bank = customer.bank - money

    val bank_money = if (bank < 0.0) Left(AppError("Customer doesn't have enough money to withdraw")) else Right(bank)

    bank_money.map(bank => Customer(personal, bank))
  }

  def checkBalance(customer: Customer): BigDecimal = {
    customer.personal + customer.bank
  }

}
