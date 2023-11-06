package by.kanarski.bankingproducts;

import by.kanarski.bankingproducts.interfaces.ICurrencyBankProductTests;
import by.kanarski.bankingproducts.products.impl.Deposit;
import by.kanarski.bankingproducts.products.interfaces.IDeposit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.util.Currency;

public class DepositTests implements ICurrencyBankProductTests<IDeposit> {

    @Override
    public IDeposit getBankProductInstance(String name, String currencyCode) {
        return new Deposit(name, currencyCode);
    }

    @Override
    public IDeposit getBankProductInstance(String name, Currency currency) {
        return new Deposit(name, currency);
    }

    @Test
    public void closeZeroBalanceDepositTest() {
        IDeposit deposit = getBankProductInstance();
        deposit.closeDeposit();
        Assertions.assertEquals(0, deposit.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/moneyamount/positive.csv", numLinesToSkip = 1)
    public void closeDepositTest(Double moneyAmount) {
        IDeposit deposit = getBankProductInstance();
        deposit.topUp(moneyAmount);
        deposit.closeDeposit();
        Assertions.assertEquals(0, deposit.getBalance());
    }
}
