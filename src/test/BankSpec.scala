import org.specs2.mutable.Specification
import codingtest.Bank
import codingtest.CustomerNotFound
import codingtest.InsufficientFunds

class BankSpec extends Specification {

  val customer1 = ("1", BigDecimal(100))
  val customer2 = ("2", BigDecimal(200))
  val bank = Bank(Map(customer1, customer2))

  "getTotal" should {
    "Should be a sum of all customers balances" in {
      bank.getTotal() mustEqual 300.00
    }
  }

  "deposit" should {
    "Succeed when the customer exists" in {
      val result = bank.deposit(customerId = "1", depositAmt = BigDecimal(100))
      result must beRight(Bank(Map(("1", BigDecimal(200)), customer2)))
    }

    "Fail when the customer doesn't exist" in {
      val result = bank.deposit(customerId = "007", depositAmt = BigDecimal(100))
      result must beLeft(CustomerNotFound("007"))
    }
  }

  "withdraw" should {
    "Succeed when the customer exists and has enough money" in {
      val result = bank.withdraw(customerId = "1", withdrawAmt = BigDecimal(100))
      result must beRight(Bank(Map(("1", BigDecimal(0)), customer2)))
    }

    "Fail when the customer exists and doesn't have enough money" in {
      val result = bank.withdraw(customerId = "1", withdrawAmt = BigDecimal(1000))
      result must beLeft(InsufficientFunds("1", BigDecimal(100)))
    }

    "Fail when the customer doesn't exist" in {
      val result = bank.withdraw(customerId = "99", withdrawAmt = BigDecimal(100))
      result must beLeft(CustomerNotFound("99"))
    }
  }

  "checkbalance" should {
    "Succeed when the customer exists" in {
      val result = bank.getBalance(customerId = "1")
      result must beRight(BigDecimal(100))
    }

    "Fail when the customer doesn't exist" in {
      val result = bank.getBalance(customerId = "86")
      result must beLeft(CustomerNotFound("86"))
    }
  }
}
