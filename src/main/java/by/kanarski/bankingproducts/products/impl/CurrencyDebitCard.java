package by.kanarski.bankingproducts.products.impl;

import by.kanarski.bankingproducts.exceptions.UnsupportedCurrencyException;
import by.kanarski.bankingproducts.products.abstr.BankCard;
import java.util.Currency;

public class CurrencyDebitCard extends BankCard {

    public CurrencyDebitCard(String name, Currency currency) throws UnsupportedCurrencyException {
        super(name, currency);
    }

    public CurrencyDebitCard(String name, String currencyCode) throws UnsupportedCurrencyException {
        super(name, currencyCode);
    }
}
