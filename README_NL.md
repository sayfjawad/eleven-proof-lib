# eleven-proof-lib

Opensource Java API voor het valideren en genereren van nummers die gebruikmaken van de elfproef
(BSN - Burgerservicenummer, bankrekeningnummer, etc.)

## Hoe de elfproef werkt

Het onderstaande voorbeeld toont de Nederlandse toepassing van de elfproef voor het
Burgerservicenummer (BSN).

| BSN nummer | 2  | 5  | 3  | 0  | 4  | 7  | 1  | 4  | 6  |

|                     | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 |        |     |        |   |
|---------------------|----|----|----|----|----|----|----|----|----|--------|-----|--------|---|
| BSN cijfers         | 2  | 5  | 3  | 0  | 4  | 7  | 1  | 4  | 6  |        |     |        |   |
|                     | x  | x  | x  | x  | x  | x  | x  | x  | x  |        |     |        |   |
| BSN vermenigvuldigers | 9 | 8  | 7  | 6  | 5  | 4  | 3  | 2  | -1 |        |     |        |   |
| Resultaat elfproef  | 18 | 40 | 21 | 0  | 20 | 28 | 3  | 8  | -6 | Totaal | 132 | % 11 = | 0 |

#### Applicatie-informatie

Naam: eleven-proof-lib
Beheerder: Sayf Jawad ([sayf@multicode.nl](mailto:sayf@multicode.nl))

#### Vereisten

Dit project gebruikt:

```
Java 21
Maven
```

#### Applicatie bouwen

```
mvn clean install
```

#### Gebruik van de applicatie

De applicatie kan gebruikt worden als Java-bibliotheek of via de Command Line Interface (CLI).

##### Gebruik als Java-bibliotheek

Voeg de `services`-module toe aan je `pom.xml` (deze brengt `core` automatisch mee):

```xml
<dependency>
    <groupId>nl.multicode.elevenproof</groupId>
    <artifactId>services</artifactId>
    <version>1.0</version>
</dependency>
```

of, voor Gradle:

```groovy
implementation 'nl.multicode.elevenproof:services:1.0'
```

Voorbeeld — een Burgerservicenummer (BSN) valideren en genereren:

```java
import nl.multicode.elevenproof.generate.CitizenServiceNumberGenerator;
import nl.multicode.elevenproof.generate.supplier.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.model.CitizenServiceNumberDto;
import nl.multicode.elevenproof.service.CitizenServiceNumberService;
import nl.multicode.elevenproof.validate.CitizenServiceNumberElevenProof;

public class BsnVoorbeeld {

    public static void main(String[] args) {

        var elevenProof = new CitizenServiceNumberElevenProof();
        var generator = new CitizenServiceNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        CitizenServiceNumberGenerator.BSN_DIGITS_LENGTH),
                new IntArrayToString(),
                elevenProof);
        var service = new CitizenServiceNumberService(
                generator, elevenProof, new StringToIntArray());

        // Valideer een bestaand BSN
        boolean geldig = service.isValid("123456782");
        System.out.println("123456782 is geldig? " + geldig);

        // Genereer een nieuw geldig BSN
        CitizenServiceNumberDto gegenereerd = service.generate();
        System.out.println("Gegenereerd BSN: " + gegenereerd.number());
    }
}
```

Voor een snelle validatie zonder volledige service-bedrading kun je de validator direct gebruiken:

```java
var geldig = new CitizenServiceNumberElevenProof()
        .test(new StringToIntArray().apply("123456782"));
```

##### Gebruik via Command Line Interface (CLI)

Bouw de applicatie:
```
mvn clean install
```

Start de CLI:
```
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar help
```

Genereer een nummer:
```
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar generate bsn
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar generate bank
```

Valideer een nummer:
```
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar validate bsn 123456782
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar validate bank 123456789
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar validate giro 1234567
```

#### Tests draaien

Om de unit- en integratietests uit te voeren:
```
mvn clean verify
```

#### Een nieuwe versie uitbrengen op Maven Central

Releases kunnen **alleen** vanaf `main` worden gemaakt. De procedure is:

1. Merge alle wijzigingen voor de release naar `main` (via een pull request).
2. Maak en push vanaf een schone lokale `main` een semver-tag met prefix `v`:

   ```bash
   git checkout main && git pull
   git tag v1.1.0
   git push origin v1.1.0
   ```

3. De `Release to Maven Central` GitHub Actions-workflow start op de tag,
   zet de projectversie op de tag-naam (zonder de `v`), ondertekent de
   artifacts met de GPG-sleutel van het project en publiceert alle modules
   naar Maven Central via de `central-publishing-maven-plugin`.

De workflow weigert te publiceren als de getagde commit niet bereikbaar is
vanuit `origin/main`, dus releases vanaf feature branches worden geblokkeerd.

Vereiste GitHub Secrets (al geconfigureerd voor deze repository):
`CENTRAL_USERNAME`, `CENTRAL_PASSWORD`, `GPG_PRIVATE_KEY`, `GPG_PASSPHRASE`.
