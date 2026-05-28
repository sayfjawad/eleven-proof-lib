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


/**
 * Provides configuration and factory methods for generating, validating,
 * and managing account and service numbers, along with their corresponding
 * services, proofs, and controllers.
 *
 * This class acts as a central hub for creating and assembling various
 * components used in number generation and validation workflows. It
 * includes methods to retrieve configured instances of generators,
 * validators, services, and controllers for bank account numbers,
 * citizen service numbers, and giro account numbers.
 */
public class AppConfig {

    /**
     * Builds a bank account number generator wired with the standard 10-digit random supplier.
     *
     * @param intArrayToString             digit-to-string converter
     * @param bankAccountNumberElevenProof bank account eleven-proof validator
     * @return a configured {@link BankAccountNumberGenerator}
     */
    public BankAccountNumberGenerator getBankAccountNumberGenerator(
            IntArrayToString intArrayToString,
            BankAccountNumberElevenProof bankAccountNumberElevenProof) {

        return new BankAccountNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        BankAccountNumberGenerator.BANK_ACCOUNT_DIGITS_LENGTH),
                intArrayToString,
                bankAccountNumberElevenProof);
    }

    /**
     * Builds a BSN generator wired with the standard 9-digit random supplier.
     *
     * @param intArrayToString               digit-to-string converter
     * @param citizenServiceNumberElevenProof BSN eleven-proof validator
     * @return a configured {@link CitizenServiceNumberGenerator}
     */
    public CitizenServiceNumberGenerator getCitizenServiceNumberGenerator(
            IntArrayToString intArrayToString,
            CitizenServiceNumberElevenProof citizenServiceNumberElevenProof) {

        return new CitizenServiceNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        CitizenServiceNumberGenerator.BSN_DIGITS_LENGTH),
                intArrayToString,
                citizenServiceNumberElevenProof);
    }

    /**
     * @return a new BSN eleven-proof validator
     */
    public CitizenServiceNumberElevenProof getCitizenServiceNumberElevenProof() {

        return new CitizenServiceNumberElevenProof();
    }

    /**
     * @return a new bank account eleven-proof validator
     */
    public BankAccountNumberElevenProof getBankAccountNumberElevenProof() {

        return new BankAccountNumberElevenProof();
    }

    /**
     * @return a new Giro account length validator
     */
    public GiroAccountNumberElevenProof getGiroAccountNumberElevenProof() {

        return new GiroAccountNumberElevenProof();
    }

    /**
     * @return a new digit-array to string converter
     */
    public IntArrayToString getIntArrayToString() {

        return new IntArrayToString();
    }

    /**
     * @return a new string to digit-array converter
     */
    public StringToIntArray getStringToIntArray() {

        return new StringToIntArray();
    }

    /**
     * Builds a BSN service facade.
     *
     * @param generator        BSN generator
     * @param elevenProof      BSN eleven-proof validator
     * @param stringToIntArray string-to-digits converter
     * @return a configured {@link CitizenServiceNumberService}
     */
    public CitizenServiceNumberService getCitizenServiceNumberService(
            CitizenServiceNumberGenerator generator,
            CitizenServiceNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new CitizenServiceNumberService(generator, elevenProof, stringToIntArray);
    }

    /**
     * Builds a bank account service facade.
     *
     * @param generator        bank account generator
     * @param elevenProof      bank account eleven-proof validator
     * @param stringToIntArray string-to-digits converter
     * @return a configured {@link BankAccountNumberService}
     */
    public BankAccountNumberService getBankAccountNumberService(
            BankAccountNumberGenerator generator,
            BankAccountNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new BankAccountNumberService(generator, elevenProof, stringToIntArray);
    }

    /**
     * Builds a Giro service facade.
     *
     * @param elevenProof      Giro length validator
     * @param stringToIntArray string-to-digits converter
     * @return a configured {@link GiroAccountNumberService}
     */
    public GiroAccountNumberService getGiroAccountNumberService(
            GiroAccountNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new GiroAccountNumberService(elevenProof, stringToIntArray);
    }

    /**
     * Returns a fully wired controller for bank account number operations.
     *
     * @return a configured {@link BankAccountNumberController}
     */
    public BankAccountNumberController getBankAccountNumberController() {

        final var intArrayToString = getIntArrayToString();
        final var stringToIntArray = getStringToIntArray();
        final var generator = getBankAccountNumberGenerator(intArrayToString,
                getBankAccountNumberElevenProof());
        final var service = getBankAccountNumberService(generator,
                getBankAccountNumberElevenProof(), stringToIntArray);
        return new BankAccountNumberController(service);
    }

    /**
     * Retrieves an instance of {@code CitizenServiceNumberController}, which acts as the controller
     * for managing and validating citizen service numbers. The controller utilizes services and utilities
     * provided by the configuration to enable number generation and validation functionalities.
     *
     * @return a {@code CitizenServiceNumberController} configured with the necessary services and utilities
     *         for handling citizen service number operations.
     */
    public CitizenServiceNumberController getCitizenServiceNumberController() {

        final var intArrayToString = getIntArrayToString();
        final var stringToIntArray = getStringToIntArray();
        final var generator = getCitizenServiceNumberGenerator(intArrayToString,
                getCitizenServiceNumberElevenProof());
        final var service = getCitizenServiceNumberService(generator, getCitizenServiceNumberElevenProof(),
                stringToIntArray);
        return new CitizenServiceNumberController(service);
    }

    /**
     * Returns a fully wired controller for Giro account number operations.
     *
     * @return a configured {@link GiroAccountNumberController}
     */
    public GiroAccountNumberController getGiroAccountNumberController() {

        final var stringToIntArray = getStringToIntArray();
        final var service = getGiroAccountNumberService(getGiroAccountNumberElevenProof(),
                stringToIntArray);
        return new GiroAccountNumberController(service);
    }
}
