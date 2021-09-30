import org.specs2.mutable.Specification
import codingtest.Customer
import codingtest.Bank

class BankSpec extends Specification {
  "totalBalance" should {
    "Should be a sum of all customers balances" in {
      val customer1 = Customer(personal = 100, bank = 100)
      val customer2 = Customer(personal = 0, bank = 0)
      val bank = Bank(Vector(customer1, customer2))

      bank.getTotal() mustEqual 100.00
    }
  }
}
