# Bank 🏧

## Requirements

You will need sbt to get started. The official installation instructions can be found here: https://www.scala-sbt.org/1.x/docs/Setup.html

## Tests 🧪

This will compile the code and run tests:

```
sbt test
```

## Compile 📚

```
sbt assembly
```

## Assumptions

- A customer will always withdraw or deposit a positive amount

## Design Decisions and Tradeoffs

### Bank Total balance

- Easiest to sum all customers money in the bank when a total is needed, rather than keeping count as each transaction is processed. This does have O(n) performance instead of O(1), but is simpler and less error prone.

- Keeping count as each transaction is processed sounds simple at first, it's simply another operation on `deposit` and `withdraw`, plus another piece of state on the bank class. However, you also need to consider adding new customers and deleting customers. Now the class needs a custom contructor to create it, and it's another thing to remember when deleting a customer. Finally should a transaction be missed for any reason, we would become out of sync.

- O(n) performance is acceptable as even if this "bank" had every person on earth with 10 accounts each, n would be around 70 billion, a process which can be completed in well under 5 seconds by any modern computer, usually less than a second.

- This may no longer be acceptable were we IO or Network bound instead of CPU bound, how ever I've built this to the spec required without future-proofing.

- Finally, I used `.fold(...)` instead of `map().sum()` as the latter would require 2 runs through the list instead of one.

### Classes vs Case Classes
- Used case classes as it makes it easier to compare equality in the tests, as you get an equality function implemented for free.

### Either vs Option vs Try

Used `Either` as it lets us tell a user about what went wrong. Didn't use `Try` as I'm not used to it and it requires throwables, which I felt was too much work.

### Wrapping the Customers Balance in a Customer Class

- Not wrapping the customers balance in a class is simple and clean
- However, customer is highly likely to store information other than just a singular balance. Making it a class makes it extensible for that purpose. Given the spec specifically called for "Code not needed to implement the requirements", I've chosen to leave it out.

### Using BigDecimal

Using `BigDecimal` is a nice way to represent money without overcomplicating things, one can generally avoid rounding errors without having to create a custom class that handles money precisely.
