package by.kanarski.bankingproducts;

import by.kanarski.bankingproducts.interfaces.CreditableProductTests;
import by.kanarski.bankingproducts.interfaces.IBankCardTests;
import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.products.abstr.BankCard;
import by.kanarski.bankingproducts.products.impl.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.util.Currency;

public class CreditCardTests implements IBankCardTests<CreditCard>, CreditableProductTests<CreditCard> {

    @Override
    public CreditCard getBankProductInstance(String name, String currencyCode) {
        return new CreditCard(name, DEFAULT_INTEREST_RATE);
    }

    @Override
    public CreditCard getBankProductInstance(String name, Currency currency) {
        return new CreditCard(name, DEFAULT_INTEREST_RATE);
    }

    @Override
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    public void withdrawIfBalanceZeroTest(Double moneyAmount) {
        BankCard bankCard = getBankProductInstance();
        bankCard.withdraw(moneyAmount);
        Assertions.assertEquals(moneyAmount * (-1), bankCard.getBalance());
    }

    @Override
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    public void withdrawMoreThanBalanceTest(Double moneyAmount) {
        BankCard bankCard = getBankProductInstance();
        bankCard.topUp(moneyAmount);
        bankCard.withdraw(moneyAmount * 2);
        Assertions.assertEquals(moneyAmount * (-1), bankCard.getBalance());
    }

    @Test
    public void debtZeroIfBalanceZeroTest() {
        CreditCard creditCard = getBankProductInstance();
        Assertions.assertEquals(0, creditCard.getDebt());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    public void balanceCanBeNegativeTest(Double moneyAmount) {
        CreditCard creditCard = getBankProductInstance();
        creditCard.withdraw(moneyAmount);
        Assertions.assertTrue(creditCard.getBalance() < 0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    public void debtIfBalanceNegativeTest(Double moneyAmount) {
        CreditCard creditCard = getBankProductInstance();
        creditCard.withdraw(moneyAmount);
        Assertions.assertEquals(moneyAmount, creditCard.getDebt());
        Assertions.assertEquals(moneyAmount, creditCard.getBalance() * (-1));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    public void debtZeroAfterTopUpNegativeBalanceTest(Double moneyAmount) {
        CreditCard creditCard = getBankProductInstance();
        creditCard.withdraw(moneyAmount);
        creditCard.topUp(moneyAmount * 2);
        Assertions.assertEquals(0, creditCard.getDebt());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/nonPositive.csv", numLinesToSkip = 1)
    public void withdrawNegativeTest(Double moneyAmount) {
        CreditCard creditCard = getBankProductInstance();
        Assertions.assertThrows(FinanceOperationException.class, () -> creditCard.withdraw(moneyAmount));
    }

}
