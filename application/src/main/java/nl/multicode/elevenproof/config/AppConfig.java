package nl.multicode.elevenproof.config;


import nl.multicode.elevenproof.controller.BankAccountNumberController;
import nl.multicode.elevenproof.controller.CitizenServiceNumberController;
import nl.multicode.elevenproof.controller.GiroAccountNumberController;
import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.generate.CitizenServiceNumberGenerator;
import nl.multicode.elevenproof.generate.supplier.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.service.BankAccountNumberService;
import nl.multicode.elevenproof.service.CitizenServiceNumberService;
import nl.multicode.elevenproof.service.GiroAccountNumberService;
import nl.multicode.elevenproof.validate.BankAccountNumberElevenProof;
import nl.multicode.elevenproof.validate.CitizenServiceNumberElevenProof;
import nl.multicode.elevenproof.validate.GiroAccountNumberElevenProof;


public class AppConfig {

    public BankAccountNumberGenerator getBankAccountNumberGenerator(
            IntArrayToString intArrayToString,
            BankAccountNumberElevenProof bankAccountNumberElevenProof) {

        return new BankAccountNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        BankAccountNumberGenerator.BANK_ACCOUNT_DIGITS_LENGTH),
                intArrayToString,
                bankAccountNumberElevenProof);
    }

    public CitizenServiceNumberGenerator getCitizenServiceNumberGenerator(
            IntArrayToString intArrayToString,
            CitizenServiceNumberElevenProof citizenServiceNumberElevenProof) {

        return new CitizenServiceNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        CitizenServiceNumberGenerator.BSN_DIGITS_LENGTH),
                intArrayToString,
                citizenServiceNumberElevenProof);
    }

    public CitizenServiceNumberElevenProof getCitizenServiceNumberElevenProof() {

        return new CitizenServiceNumberElevenProof();
    }

    public BankAccountNumberElevenProof getBankAccountNumberElevenProof() {

        return new BankAccountNumberElevenProof();
    }

    public GiroAccountNumberElevenProof getGiroAccountNumberElevenProof() {

        return new GiroAccountNumberElevenProof();
    }

    public IntArrayToString getIntArrayToString() {

        return new IntArrayToString();
    }

    public StringToIntArray getStringToIntArray() {

        return new StringToIntArray();
    }

    public CitizenServiceNumberService getCitizenServiceNumberService(
            CitizenServiceNumberGenerator generator,
            CitizenServiceNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new CitizenServiceNumberService(generator, elevenProof, stringToIntArray);
    }

    public BankAccountNumberService getBankAccountNumberService(
            BankAccountNumberGenerator generator,
            BankAccountNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new BankAccountNumberService(generator, elevenProof, stringToIntArray);
    }

    public GiroAccountNumberService getGiroAccountNumberService(
            GiroAccountNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new GiroAccountNumberService(elevenProof, stringToIntArray);
    }

    public BankAccountNumberController getBankAccountNumberController() {

        final var intArrayToString = getIntArrayToString();
        final var stringToIntArray = getStringToIntArray();
        final var generator = getBankAccountNumberGenerator(intArrayToString,
                getBankAccountNumberElevenProof());
        final var service = getBankAccountNumberService(generator,
                getBankAccountNumberElevenProof(), stringToIntArray);
        return new BankAccountNumberController(service);
    }

    public CitizenServiceNumberController getCitizenServiceNumberController() {

        final var intArrayToString = getIntArrayToString();
        final var stringToIntArray = getStringToIntArray();
        final var generator = getCitizenServiceNumberGenerator(intArrayToString,
                getCitizenServiceNumberElevenProof());
        final var service = getCitizenServiceNumberService(generator, getCitizenServiceNumberElevenProof(),
                stringToIntArray);
        return new CitizenServiceNumberController(service);
    }

    public GiroAccountNumberController getGiroAccountNumberController() {

        final var stringToIntArray = getStringToIntArray();
        final var service = getGiroAccountNumberService(getGiroAccountNumberElevenProof(),
                stringToIntArray);
        return new GiroAccountNumberController(service);
    }
}
