package by.kanarski.bankingproducts.products.impl;

import by.kanarski.bankingproducts.exceptions.UnsupportedCurrencyException;
import by.kanarski.bankingproducts.products.abstr.BankCard;
import by.kanarski.bankingproducts.utils.FinanceDataUtil;

/*
 TODO: Я не совсем понял требование наличия отдельно дебетовой и валютной дебетовой карт,
 поэтому решил сделать дебетовую карту как карту использующую валюту по умолчанию
 */
public class DebitCard extends BankCard {

    public DebitCard(String name) throws UnsupportedCurrencyException {
        super(name, FinanceDataUtil.getDefaultCurrency());
    }
}
