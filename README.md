# spring-jpa_samples
All Spring JPA related examples and notes

<#>IMP<#>: all technical notes are in the code in comments at relevant places, marked with keyword "knote",
so, to see all notes, search for ## "knote" ##

- Lot of usage code, how to use various methods (normally what you do in Service level classes) is in "Test" classes implemented as Junit tests, that is very nice way to implement new code and try it out ! That way you can try and run it easily without whole infrastructure

## Transaction Management
## Transaction Isolation
https://www.baeldung.com/spring-transactional-propagation-isolation

Another good link: https://techannotation.wordpress.com/2014/12/04/5-minutes-with-spring-transaction-isolation-level/

https://marcin-chwedczuk.github.io/spring-transactional-cheat-sheet


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
* DEFAULT, 
* READ_UNCOMMITTED, --> very common
* READ_COMMITTED, 
* REPEATABLE_READ, 
* SERIALIZABLE.
