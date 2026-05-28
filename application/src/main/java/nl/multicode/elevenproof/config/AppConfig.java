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
import nl.multicode.elevenproof.validate.GiroAccountNumberValidator;


/**
 * Manual DI container that wires all components for the CLI application. Every component is
 * lazily instantiated and cached so repeated calls share the same instance.
 */
public class AppConfig {

    private IntArrayToString intArrayToString;
    private StringToIntArray stringToIntArray;
    private BankAccountNumberElevenProof bankAccountNumberElevenProof;
    private CitizenServiceNumberElevenProof citizenServiceNumberElevenProof;
    private GiroAccountNumberValidator giroAccountNumberValidator;
    private BankAccountNumberGenerator bankAccountNumberGenerator;
    private CitizenServiceNumberGenerator citizenServiceNumberGenerator;
    private BankAccountNumberService bankAccountNumberService;
    private CitizenServiceNumberService citizenServiceNumberService;
    private GiroAccountNumberService giroAccountNumberService;
    private BankAccountNumberController bankAccountNumberController;
    private CitizenServiceNumberController citizenServiceNumberController;
    private GiroAccountNumberController giroAccountNumberController;

    /** @return shared digit-array to string converter */
    public IntArrayToString getIntArrayToString() {

        if (intArrayToString == null) {
            intArrayToString = new IntArrayToString();
        }
        return intArrayToString;
    }

    /** @return shared string to digit-array converter */
    public StringToIntArray getStringToIntArray() {

        if (stringToIntArray == null) {
            stringToIntArray = new StringToIntArray();
        }
        return stringToIntArray;
    }

    /** @return shared BSN eleven-proof validator */
    public CitizenServiceNumberElevenProof getCitizenServiceNumberElevenProof() {

        if (citizenServiceNumberElevenProof == null) {
            citizenServiceNumberElevenProof = new CitizenServiceNumberElevenProof();
        }
        return citizenServiceNumberElevenProof;
    }

    /** @return shared bank account eleven-proof validator */
    public BankAccountNumberElevenProof getBankAccountNumberElevenProof() {

        if (bankAccountNumberElevenProof == null) {
            bankAccountNumberElevenProof = new BankAccountNumberElevenProof();
        }
        return bankAccountNumberElevenProof;
    }

    /** @return shared Giro account length validator */
    public GiroAccountNumberValidator getGiroAccountNumberValidator() {

        if (giroAccountNumberValidator == null) {
            giroAccountNumberValidator = new GiroAccountNumberValidator();
        }
        return giroAccountNumberValidator;
    }

    /** @return shared bank account number generator */
    public BankAccountNumberGenerator getBankAccountNumberGenerator() {

        if (bankAccountNumberGenerator == null) {
            bankAccountNumberGenerator = new BankAccountNumberGenerator(
                    new FixedLengthStringRandomNumbersSupplier(BankAccountNumberGenerator.BANK_ACCOUNT_DIGITS_LENGTH),
                    getIntArrayToString(),
                    getBankAccountNumberElevenProof());
        }
        return bankAccountNumberGenerator;
    }

    /** @return shared BSN generator */
    public CitizenServiceNumberGenerator getCitizenServiceNumberGenerator() {

        if (citizenServiceNumberGenerator == null) {
            citizenServiceNumberGenerator = new CitizenServiceNumberGenerator(
                    new FixedLengthStringRandomNumbersSupplier(CitizenServiceNumberGenerator.BSN_DIGITS_LENGTH),
                    getIntArrayToString(),
                    getCitizenServiceNumberElevenProof());
        }
        return citizenServiceNumberGenerator;
    }

    /** @return shared bank account service facade */
    public BankAccountNumberService getBankAccountNumberService() {

        if (bankAccountNumberService == null) {
            bankAccountNumberService = new BankAccountNumberService(
                    getBankAccountNumberGenerator(),
                    getBankAccountNumberElevenProof(),
                    getStringToIntArray());
        }
        return bankAccountNumberService;
    }

    /** @return shared BSN service facade */
    public CitizenServiceNumberService getCitizenServiceNumberService() {

        if (citizenServiceNumberService == null) {
            citizenServiceNumberService = new CitizenServiceNumberService(
                    getCitizenServiceNumberGenerator(),
                    getCitizenServiceNumberElevenProof(),
                    getStringToIntArray());
        }
        return citizenServiceNumberService;
    }

    /** @return shared Giro service facade */
    public GiroAccountNumberService getGiroAccountNumberService() {

        if (giroAccountNumberService == null) {
            giroAccountNumberService = new GiroAccountNumberService(
                    getGiroAccountNumberValidator(),
                    getStringToIntArray());
        }
        return giroAccountNumberService;
    }

    /** @return shared controller for bank account number operations */
    public BankAccountNumberController getBankAccountNumberController() {

        if (bankAccountNumberController == null) {
            bankAccountNumberController = new BankAccountNumberController(getBankAccountNumberService());
        }
        return bankAccountNumberController;
    }

    /** @return shared controller for BSN operations */
    public CitizenServiceNumberController getCitizenServiceNumberController() {

        if (citizenServiceNumberController == null) {
            citizenServiceNumberController = new CitizenServiceNumberController(getCitizenServiceNumberService());
        }
        return citizenServiceNumberController;
    }

    /** @return shared controller for Giro account number operations */
    public GiroAccountNumberController getGiroAccountNumberController() {

        if (giroAccountNumberController == null) {
            giroAccountNumberController = new GiroAccountNumberController(getGiroAccountNumberService());
        }
        return giroAccountNumberController;
    }
}
