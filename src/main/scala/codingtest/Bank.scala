package codingtest

case class Bank(customers: Vector[Customer])

object Bank {
  def total(bank: Bank): BigDecimal = {
    bank.customers.foldLeft(BigDecimal(0))((accumulator, customer) => accumulator + customer.bank)
  }
}
