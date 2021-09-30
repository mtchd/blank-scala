import org.specs2.mutable.Specification
import codingtest.Customer
import codingtest.Bank

class BankSpec extends Specification {
  "total" should {
    "Sum all customers balances" in {
      val customer1 = Customer(personal = 100, bank = 100)
      val customer2 = Customer(personal = 0, bank = 0)
      val bank = Bank(Vector(customer1, customer2))

      Bank.total(bank) mustEqual 100.00
    }
  }
}
