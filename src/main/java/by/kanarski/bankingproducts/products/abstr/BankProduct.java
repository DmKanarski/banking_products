package by.kanarski.bankingproducts.products.abstr;

import by.kanarski.bankingproducts.exceptions.UnsupportedCurrencyException;
import by.kanarski.bankingproducts.utils.FinanceDataUtil;
import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.products.interfaces.IBankProduct;
import lombok.*;
import java.math.BigDecimal;
import java.util.Currency;

public abstract class BankProduct implements IBankProduct {

    @Getter
    @Setter
    private String name;
    @Getter
    private final Currency currency;
    protected BigDecimal balance;

    public BankProduct(String name, Currency currency) throws UnsupportedCurrencyException {
        FinanceDataUtil.throwIfNotSupportedCurrency(currency);
        this.name = name;
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
    }

    public BankProduct(String name, String currencyCode) throws UnsupportedCurrencyException {
        FinanceDataUtil.throwIfNotSupportedCurrency(currencyCode);
        this.name = name;
        this.currency = Currency.getInstance(currencyCode);
        this.balance = BigDecimal.ZERO;
    }

    public void topUp(Number amount) throws FinanceOperationException {
        FinanceDataUtil.throwIfNotPositive(amount);
        balance = FinanceDataUtil.add(balance, amount);
    }

    public Double getBalance() {
        return balance.doubleValue();
    }

    protected void setBalance(BigDecimal newBalance) {
        balance = newBalance;
    }

}
