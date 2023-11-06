package by.kanarski.bankingproducts.products.abstr;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.exceptions.UnsupportedCurrencyException;
import by.kanarski.bankingproducts.utils.FinanceDataUtil;
import by.kanarski.bankingproducts.products.interfaces.Withdrawable;
import java.math.BigDecimal;
import java.util.Currency;

public abstract class BankCard extends BankProduct implements Withdrawable {

    public BankCard(String name, Currency currency) throws UnsupportedCurrencyException {
        super(name, currency);
    }

    public BankCard(String name, String currencyCode) {
        super(name, currencyCode);
    }

    @Override
    public void withdraw(Number amount) throws FinanceOperationException {
        FinanceDataUtil.throwIfNotPositive(amount);
        FinanceDataUtil.throwIfNotPositive(balance, "Withdrawals from an account with a zero balance are not possible");
        BigDecimal newBalance = FinanceDataUtil.subtract(balance, amount);
        FinanceDataUtil.throwIfNegative(newBalance,
                String.format("The card balance after withdrawal should not be less than 0, attempt to set the balance to '%f'", newBalance));
        setBalance(newBalance);
    }
}
