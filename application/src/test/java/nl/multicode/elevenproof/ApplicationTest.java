package nl.multicode.elevenproof;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integration-test")
class ApplicationTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testHelp() {
        Application.main(new String[]{"help"});
        assertThat(outputStreamCaptor.toString()).contains("ElevenProof CLI Tool");
        assertThat(outputStreamCaptor.toString()).contains("Usage:");
    }

    @Test
    void testGenerateBsn() {
        Application.main(new String[]{"generate", "bsn"});
        String output = outputStreamCaptor.toString().trim();
        assertThat(output).matches("\\d{9}");
    }

    @Test
    void testGenerateBank() {
        Application.main(new String[]{"generate", "bank"});
        String output = outputStreamCaptor.toString().trim();
        assertThat(output).matches("\\d{9,10}");
    }

    @Test
    void testValidateBsn() {
        // We know from BSN generator it works, let's test a known valid one
        Application.main(new String[]{"validate", "bsn", "123456782"});
        assertThat(outputStreamCaptor.toString()).contains("123456782 is a valid BSN number.");
    }

    @Test
    void testValidateBank() {
        Application.main(new String[]{"validate", "bank", "123456789"});
        assertThat(outputStreamCaptor.toString()).contains("123456789 is a valid BANK number.");
    }

    @Test
    void testValidateGiro() {
        Application.main(new String[]{"validate", "giro", "1234567"});
        assertThat(outputStreamCaptor.toString()).contains("1234567 is a valid GIRO number.");
    }

    @Test
    void testUnknownCommand() {
        Application.main(new String[]{"unknown"});
        assertThat(outputStreamCaptor.toString()).contains("Unknown command: unknown");
    }
}
