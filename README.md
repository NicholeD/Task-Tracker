# ATA-Week-13

Before running any of the commands ensure you first run `./gradlew build`

## Table deploy commands

### Iterators and DynamoDB Delete

**Task Tracker**
```bash
aws cloudformation create-stack --stack-name iteratorsanddynamodb-tasktracker --template-body file://IteratorsAndDynamoDBDelete/TaskTracker/tasktracker_table.yaml 
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/TaskTracker/tasktracker_seeddata.json
```

**Clothing Items**
```bash
aws cloudformation create-stack --stack-name iteratorsanddynamodb-clothingitems --template-body file://IteratorsAndDynamoDBDelete/Clothing/clothingitems_table.yaml 
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/Clothing/clothingitems_seeddata.json
```

**Kenzie Social Calendar**
```bash
aws cloudformation create-stack --stack-name iteratorsanddynamodb-socialcalendar --template-body file://IteratorsAndDynamoDBDelete/SocialCalendar/socialcalendar_tables.yaml 
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/SocialCalendar/events_seeddata.json
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/SocialCalendar/invites_seeddata.json
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/SocialCalendar/members_seeddata.json
```

### DynamoDB Query

**Books**
```bash
aws cloudformation create-stack --stack-name dynamodbquery-books --template-body file://DynamoDBQuery/Books/booksread_table.yaml 
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/Books/booksread_seeddata.json
```

**Log Entries**
```bash
aws cloudformation create-stack --stack-name dynamodbquery-logentries --template-body file://DynamoDBQuery/LogEntries/logentries_table.yaml 
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/LogEntries/logentries_seeddata.json
```

**Kenzie Social Calendar: Search**
```bash
aws cloudformation create-stack --stack-name dynamodbquery-socialcalendar --template-body file://DynamoDBQuery/SocialCalendar/socialcalendar_tables.yaml 
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/eventannouncements_seeddata.json
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/events_seeddata.json
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/invites_seeddata.json
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/members_seeddata.json
```

### Metrics
**Reservations**
```bash
aws cloudformation create-stack --stack-name metrics-reservations --template-body file://Metrics/HotelReservations/reservations_table.yaml 
aws dynamodb batch-write-item --request-items file://Metrics/HotelReservations/reservations_seeddata.json
```

## Test commands

### Iterators and DynamoDB Delete
1. `./gradlew iterators-wishlist-test`
2. `./gradlew iterators-tasktracker-phase1-test`
3. `./gradlew iterators-tasktracker-phase2-test`
4. `./gradlew iterators-clothing-test`
5. `./gradlew iterators-socialcalendar-activitytest`
6. `./gradlew iterators-socialcalendar-eventdaotest`
7. `./gradlew iterators-socialcalendar-invitedaotest`
8. `./gradlew iterators-socialcalendar-memberdaotest`
9. `./gradlew iterators-socialcalendar-all-tests`

### DynamoDB Query
1. `./gradlew dynamodbquery-book-test`
2. `./gradlew dynamodbquery-narrowing-test`
3. `./gradlew dynamodbquery-socialcalendar-deletetest`
4. `./gradlew dynamodbquery-socialcalendar-betweendatestest`
5. `./gradlew dynamodbquery-socialcalendar-getinvitestest`
6. `./gradlew dynamodbquery-socialcalendar-geteventtest`
7. `./gradlew dynamodbquery-phase0`
8. `./gradlew dynamodbquery-phase1`
9. `./gradlew dynamodbquery-phase2`
10. `./gradlew dynamodbquery-phase3`
11. `./gradlew dynamodbquery-phase4`


### Metrics
1. `./gradlew metrics-orders`
2. `./gradlew metrics-hotel-phase0`
3. `./gradlew metrics-hotel-phase1`
4. `./gradlew metrics-hotel-phase2`
5. `./gradlew metrics-hotel-all-tests`




