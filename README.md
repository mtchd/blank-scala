# Bank 🏧

## Tests 🧪

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

- Easiest to sum all customers money in the bank when a total is needed, rather than keeping count as each transaction is processed. This does have O(n) performance instead of O(1), but is simpler and less error prone.
- Used case classes and companion objects instead of classes, as that's more idiomatic scala.
