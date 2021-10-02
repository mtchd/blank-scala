# Bank 🏧

## Requirements

You will need sbt to get started. The official installation instructions can be found here: https://www.scala-sbt.org/1.x/docs/Setup.html

## Tests 🧪

This will compile the code and run tests:

```
sbt test
```

## Assumptions

- A customer will always withdraw or deposit a positive amount.
- A customer will always provide a customer id to identify themselves from another customer.
- The currency quantities can be large enough to justify `BigDecimal`, but not so large they exceed it.
- Any calculations with the currency are expected to avoid rounding errors.
- The currency has more than 2 decimal places. (i.e smaller fractions than cents)

## Design Decisions and Tradeoffs

### Bank Total balance

- The program sums all customers money in the bank when a total is needed, rather than keeping count as each transaction is processed. This does have O(n) performance instead of O(1), but is simpler and less error prone.

- Keeping count as each transaction is processed sounds simple at first, it's simply another operation on `deposit` and `withdraw`, plus another piece of state on the bank class. However, you also need to consider adding new customers and deleting customers. Now the class needs a custom contructor to create it, and it's another thing to remember when deleting a customer. Finally, should a transaction be missed for any reason, we would become out of sync.

- O(n) performance is acceptable as even if this "bank" had every person on earth with 10 accounts each, n would be around 70 billion, a process which can be completed in under 5 seconds by any modern computer, usually less than a second.

- This may no longer be acceptable were we IO or Network bound instead of CPU bound, how ever I've built this to the spec required without future-proofing.

- Finally, I used `.foldLeft(...)` instead of `map(...).sum()` as the latter would require 2 runs through the list instead of one, and the readability is very similiar.

### Classes vs Case Classes

- Used case classes as it makes it easier to compare equality in the tests, as you get an equality function implemented for free.
- Kept things slightly object orientated by using `this` instead of using a companion object which holds static functions that take the `Bank` or `Map` as an input parameter. This was purposeful, as I feel you gain readability by avoiding being totally functional here.

### Either vs Option vs Try

Used `Either` as it lets us tell a user about what went wrong, compared to `Option` which only offers that _something_ went wrong without specifying it. `Try` is also a valid choice here, although `AppError` would need to implement `Throwable` as well. The difference is mostly semantic and I've noticed Scala developers tend to use `Either` more than `Try`, so I figured it was best to optimise for what people are used to and understand more.

### Wrapping the Customers Balance in a Customer Class

- Not wrapping the customers balance in a class is simple and clean
- However, customer is highly likely to store information other than just a singular balance. Making it a class makes it extensible for that purpose. Given the spec specifically called for "Code not needed to implement the requirements", I've chosen to leave it out.
- Another arguement to put it in could be readability, as encapsulating the data ensures whenever someone writes code that accesses it, they are aware of the context of the data they are accessing. I feel this is over engineering the solution, and should only be added if such a confusion is possbible (e.g. many BigDecimals for different purposes are in the code).

### Using BigDecimal

Using `BigDecimal` is a nice way to represent money without overcomplicating things, one can generally avoid rounding errors without having to create a custom class that handles money precisely.

### Functional

I've run with a functional approach as a personal preference, although I'm also comfortable in object orientated.

For memory and processing intensive operations functional can be a disadvantage, although dedicated functional langauges often optimise under the hood, so idiomatic functional code often doesn't cause the performance issues it would appear to.

That being said, functional definitely can still reduce performance. However in practice, CPU and memory bound operations rarely are the cause of bottlenecks compared to IO and Network bound operations and code readability should be prioritised. In a world of scalable cloud computing, pre-optimised code often causes much more problems than it solves.

I find it easier to test and reason about functions that always return something. Returning a `Bank` or an `AppError` is simple to test, but mutating the state of a bank and optionally throwing an error starts to require try / catches and other techniques, which I feel complicates code uneededly.


