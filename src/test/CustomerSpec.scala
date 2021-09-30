import org.specs2.mutable.Specification
import codingtest.{AppError, Customer}

class CustomerSpec extends Specification {
  "deposit" should {
    "Succeed when the customer has the money to deposit" in {
      val customer = Customer(bank = 100.00, personal = 100.00)
      val result = Customer.deposit(customer, 100.00)

      result must beRight(Customer(bank = 200.00, personal = 0.00))
    }

    "Fail when the customer doesn't have enough money" in {
      val customer = Customer(bank = 100.00, personal = 100.00)
      val result = Customer.deposit(customer, 200.00)

      result must beLeft(AppError("Customer doesn't have enough money to deposit"))
    }
  }

  "withdraw" should {
    "Succeed when the customer has the money to withdraw" in {
      val customer = Customer(bank = 100.00, personal = 100.00)
      val result = Customer.withdraw(customer, 100.00)

      result must beRight(Customer(bank = 0.00, personal = 200.00))
    }

    "Fail when the customer doesn't have enough money" in {
      val customer = Customer(bank = 100.00, personal = 100.00)
      val result = Customer.withdraw(customer, 200.00)

      result must beLeft(AppError("Customer doesn't have enough money to withdraw"))
    }
  }

  "checkBalance" should {
    "Show the customers balance" in {
      val customer = Customer(bank = 100.00, personal = 100.00)
      Customer.checkBalance(customer) must beEqualTo(200.00)
    }
  }
}
