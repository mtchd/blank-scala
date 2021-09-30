package codingtest

case class Bank(customers: Vector[Customer])

object Bank {
  def total(bank: Bank): BigDecimal = {
    bank.customers.map(customer => customer.bank).sum
  }
}
