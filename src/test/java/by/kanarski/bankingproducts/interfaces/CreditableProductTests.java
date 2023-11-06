package by.kanarski.bankingproducts.interfaces;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.products.interfaces.Creditable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public interface CreditableProductTests<T extends Creditable> extends IBankProductTests<T> {

    double DEFAULT_INTEREST_RATE = 0.5;

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/interest/positive.csv", numLinesToSkip = 1)
    default void positiveInterestTest(Double interestRate) {
        Creditable creditableProduct = getBankProductInstance();
        creditableProduct.setInterestRate(interestRate);
        Assertions.assertEquals(interestRate, creditableProduct.getInterestRate());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/interest/nonPositive.csv", numLinesToSkip = 1)
    default void nonPositiveInterestTest(Double interestRate) {
        Creditable creditableProduct = getBankProductInstance();
        creditableProduct.setInterestRate(interestRate);
        Assertions.assertThrows(FinanceOperationException.class, () -> creditableProduct.setInterestRate(interestRate));
    }

}
