package by.kanarski.bankingproducts.interfaces;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.products.abstr.BankCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public interface IBankCardTests<T extends BankCard> extends IBankProductTests<T> {

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    default void withdrawPositiveTest(Double moneyAmount) {
        BankCard bankCard = getBankProductInstance();
        bankCard.topUp(moneyAmount);
        bankCard.withdraw(moneyAmount);
        Assertions.assertEquals(0, bankCard.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/nonPositive.csv", numLinesToSkip = 1)
    default void withdrawNonPositiveTest(Double moneyAmount) {
        BankCard bankCard = getBankProductInstance();
        bankCard.topUp(DEFAULT_TOP_UP_AMOUNT);
        Assertions.assertThrows(FinanceOperationException.class, () -> bankCard.withdraw(moneyAmount));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    default void withdrawIfBalanceZeroTest(Double moneyAmount) {
        BankCard bankCard = getBankProductInstance();
        Assertions.assertThrows(FinanceOperationException.class, () -> bankCard.withdraw(moneyAmount));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    default void withdrawMoreThanBalanceTest(Double moneyAmount) {
        BankCard bankCard = getBankProductInstance();
        bankCard.topUp(moneyAmount);
        Assertions.assertThrows(FinanceOperationException.class, () -> bankCard.withdraw(moneyAmount * 2));
    }

}
