package by.kanarski.bankingproducts;

import by.kanarski.bankingproducts.interfaces.IBankProductTests;
import by.kanarski.bankingproducts.products.impl.DebitCard;
import java.util.Currency;

public class DebitCardTests implements IBankProductTests<DebitCard> {

    @Override
    public DebitCard getBankProductInstance(String name, String currencyCode) {
        return new DebitCard(name);
    }

    @Override
    public DebitCard getBankProductInstance(String name, Currency currency) {
        return new DebitCard(name);
    }

}
