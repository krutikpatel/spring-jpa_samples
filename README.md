# spring-jpa_samples
All Spring JPA related examples and notes

## Transaction Management
## Transaction Isolation
https://www.baeldung.com/spring-transactional-propagation-isolation

Another good link: https://techannotation.wordpress.com/2014/12/04/5-minutes-with-spring-transaction-isolation-level/

Isolation is one of the common ACID properties: Atomicity, Consistency, Isolation, and Durability. Isolation describes how changes applied by concurrent transactions are visible to each other.

Each isolation level prevents zero or more concurrency side effects on a transaction:

### Dirty read: 
read the uncommitted change of a concurrent transaction

### Nonrepeatable read: 
get different value on re-read of a row if a concurrent transaction updates the same row and commits

### Phantom read: 
get different rows after re-execution of a range query if another transaction adds or removes some rows in the range and commits

We can set the isolation level of a transaction by @Transactional::isolation. 
It has these five enumerations in Spring: 
DEFAULT, READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE.
