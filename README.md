1. Project created with Hexagonal architecture with application and core modules, together with H2 for data persistence

Core - contains pure domain logic
- domain model (InventoryItem)
- domain events
- use cases (ReserveItemUseCase)
- ports (interfaces):
- InventoryItemRepository
- DomainEventRepository

Application - infrastructure and adapters
- REST controller
- JPA entities and repositories
- H2 database config
- repository implementations
- configuration wiring (CoreConfig)

2. How CAS and retry works

Uses manual version check in the following way:
- first thread starts operation on the item if expected version matches 
- it increases it by one
- second tries to get in into unfinished operation, but item versions dont match
- starts the 3 retries cycle
- during each retry gets the fresh version of the item, if it wasn't updated during the attempt updates successfuly otherwise throws 409 after 3 tries

3. Transactions
The project uses short, local transactions only inside repository methods, not around the entire use case:
- each db call runs in its own small transactional boundary
- no long transaction is opened around the whole reserve process
- avoids locks being held for too long
