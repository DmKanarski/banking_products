package by.kanarski.bankingproducts.interfaces;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.products.interfaces.IBankProduct;
import by.kanarski.bankingproducts.utils.FinanceDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.util.Currency;

public interface IBankProductTests<T extends IBankProduct> {

    String DEFAULT_PRODUCT_NAME = "default name";
    Double DEFAULT_TOP_UP_AMOUNT = 99999999999.0;

    T getBankProductInstance(String name, String currencyCode);

    T getBankProductInstance(String name, Currency currency);

    default T getBankProductInstance() {
        return getBankProductInstance(DEFAULT_PRODUCT_NAME, FinanceDataUtil.getDefaultCurrency());
    }

    @Test
    default void initialBalanceTest() {
        T bankProduct = getBankProductInstance();
        Assertions.assertEquals(0, bankProduct.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    default void topPositiveUpTest(Double moneyAmount) {
        T bankProduct = getBankProductInstance();
        bankProduct.topUp(moneyAmount);
        Assertions.assertEquals(moneyAmount, bankProduct.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/nonPositive.csv", numLinesToSkip = 1)
    default void topUpNonPositiveTest(Double moneyAmount) {
        T bankProduct = getBankProductInstance();
        Assertions.assertThrows(FinanceOperationException.class, () -> bankProduct.topUp(moneyAmount));
    }

}
