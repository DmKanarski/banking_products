package by.kanarski.bankingproducts;

import by.kanarski.bankingproducts.interfaces.IBankCardTests;
import by.kanarski.bankingproducts.interfaces.ICurrencyBankProductTests;
import by.kanarski.bankingproducts.products.impl.CurrencyDebitCard;
import java.util.Currency;

public class CurrencyDebitCardTests implements IBankCardTests<CurrencyDebitCard>, ICurrencyBankProductTests<CurrencyDebitCard> {

    @Override
    public CurrencyDebitCard getBankProductInstance(String name, String currencyCode) {
        return new CurrencyDebitCard(name, currencyCode);
    }

    @Override
    public CurrencyDebitCard getBankProductInstance(String name, Currency currency) {
        return new CurrencyDebitCard(name, currency);
    }

}
