package by.kanarski.bankingproducts.products.impl;

import by.kanarski.bankingproducts.exceptions.UnsupportedCurrencyException;
import by.kanarski.bankingproducts.products.abstr.BankProduct;
import by.kanarski.bankingproducts.products.interfaces.IDeposit;
import java.math.BigDecimal;
import java.util.Currency;

public class Deposit extends BankProduct implements IDeposit {

    public Deposit(String name, Currency currency) throws UnsupportedCurrencyException {
        super(name, currency);
    }

    public Deposit(String name, String currencyCode) throws UnsupportedCurrencyException {
        super(name, currencyCode);
    }

    @Override
    public void closeDeposit() {
        setBalance(BigDecimal.ZERO);
    }
}
