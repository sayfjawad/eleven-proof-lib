# eleven-proof-lib

Opensource Java API for validating and generating numbers that use the ElevenProof (elfproef in
Dutch) (BSN Social Security Number / Citizen Service Number, Bank Account number, etc...)

## How the eleven proof works

This example uses the Dutch implementation of the eleven proof for the social security number
equivalent 'BSN - Social Security Number / Citizen Service Number'.

| BSN number | 2  | 5  | 3  | 0  | 4  | 7  | 1  | 4  | 6  |

|                     | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 |       |     |        |   |
|---------------------|----|----|----|----|----|----|----|----|----|-------|-----|--------|---|
| BSN Digits          | 2  | 5  | 3  | 0  | 4  | 7  | 1  | 4  | 6  |       |     |        |   |
|                     | x  | x  | x  | x  | x  | x  | x  | x  | x  |       |     |        |   |
| BSN Multipliers     | 9  | 8  | 7  | 6  | 5  | 4  | 3  | 2  | -1 |       |     |        |   |
| Eleven proof result | 18 | 40 | 21 | 0  | 20 | 28 | 3  | 8  | -6 | Total | 132 | % 11 = | 0 |

#### Application information

Name: eleven-proof-lib
Maintainer: Sayf jawad ([sayf@multicode.nl](mailto:sayf@multicode.nl))

#### Requirements

This project uses:

```
Java 21
Maven
```

#### Build Application

```
mvn clean install
```

#### Application usage

The application can be used as a Java library or as a Command Line Interface (CLI).

##### Command Line Interface (CLI) usage

Build the application:
```
mvn clean install
```

Run the CLI:
```
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar help
```

Generate a number:
```
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar generate bsn
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar generate bank
```

Validate a number:
```
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar validate bsn 123456782
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar validate bank 123456789
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar validate giro 1234567
```

#### Running Tests

To run the unit and integration tests:
```
mvn clean verify
```

