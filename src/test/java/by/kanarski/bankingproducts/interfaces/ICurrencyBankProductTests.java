package by.kanarski.bankingproducts.interfaces;

import by.kanarski.bankingproducts.exceptions.UnsupportedCurrencyException;
import by.kanarski.bankingproducts.products.interfaces.IBankProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public interface ICurrencyBankProductTests<T extends IBankProduct> extends IBankProductTests<T> {

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/currency/supported.csv", numLinesToSkip = 1)
    default void supportedCurrenciesTest(String supportedCurrencyCode) {
        Assertions.assertDoesNotThrow(() -> getBankProductInstance(DEFAULT_PRODUCT_NAME, supportedCurrencyCode));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/currency/unsupported.csv", numLinesToSkip = 1)
    default void unsupportedCurrenciesTest(String unsupportedCurrencyCode) {
        Assertions.assertThrows(UnsupportedCurrencyException.class,
                () -> getBankProductInstance(DEFAULT_PRODUCT_NAME, unsupportedCurrencyCode));
    }

}
