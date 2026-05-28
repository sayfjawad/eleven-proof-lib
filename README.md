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

##### Library usage (Java API)

Add the `services` module to your `pom.xml` (it pulls in `core` transitively):

```xml
<dependency>
    <groupId>nl.multicode.elevenproof</groupId>
    <artifactId>services</artifactId>
    <version>1.0</version>
</dependency>
```

or, for Gradle:

```groovy
implementation 'nl.multicode.elevenproof:services:1.0'
```

Example — validate and generate a citizen service number (BSN):

```java
import nl.multicode.elevenproof.generate.CitizenServiceNumberGenerator;
import nl.multicode.elevenproof.generate.supplier.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.model.CitizenServiceNumberDto;
import nl.multicode.elevenproof.service.CitizenServiceNumberService;
import nl.multicode.elevenproof.validate.CitizenServiceNumberElevenProof;

public class BsnExample {

    public static void main(String[] args) {

        var elevenProof = new CitizenServiceNumberElevenProof();
        var generator = new CitizenServiceNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        CitizenServiceNumberGenerator.BSN_DIGITS_LENGTH),
                new IntArrayToString(),
                elevenProof);
        var service = new CitizenServiceNumberService(
                generator, elevenProof, new StringToIntArray());

        // Validate an existing BSN
        boolean valid = service.isValid("123456782");
        System.out.println("123456782 is valid? " + valid);

        // Generate a new valid BSN
        CitizenServiceNumberDto generated = service.generate();
        System.out.println("Generated BSN: " + generated.number());
    }
}
```

For a one-liner validation without the full service wiring you can use the validator directly:

```java
var valid = new CitizenServiceNumberElevenProof()
        .test(new StringToIntArray().apply("123456782"));
```

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

#### Releasing a new version to Maven Central

Releases can **only** be cut from `main`. The procedure is:

1. Merge all changes intended for the release into `main` (via a pull request).
2. From a clean local `main`, create and push a semver tag prefixed with `v`:

   ```bash
   git checkout main && git pull
   git tag v1.1.0
   git push origin v1.1.0
   ```

3. The `Release to Maven Central` GitHub Actions workflow fires on the tag,
   sets the project version to the tag name (without the `v`), signs the
   artifacts with the project's GPG key, and publishes all modules to
   Maven Central via the `central-publishing-maven-plugin`.

The workflow refuses to publish when the tagged commit is not reachable from
`origin/main`, so releases from feature branches are blocked.

Required GitHub Secrets (already configured for this repository):
`CENTRAL_USERNAME`, `CENTRAL_PASSWORD`, `GPG_PRIVATE_KEY`, `GPG_PASSPHRASE`.

