package by.kanarski.bankingproducts.products.impl;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.products.abstr.BankCard;
import by.kanarski.bankingproducts.utils.FinanceDataUtil;
import by.kanarski.bankingproducts.products.interfaces.Creditable;

import java.math.BigDecimal;

public class CreditCard extends BankCard implements Creditable {

    private Double interestRate;

    public CreditCard(String name, Double interestRate) {
        super(name, FinanceDataUtil.getDefaultCurrency());
        this.interestRate = interestRate;
    }

    @Override
    public Double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(Double newInterestRate) throws FinanceOperationException {
        FinanceDataUtil.throwIfNotPositive(interestRate);
        interestRate = newInterestRate;
    }

    @Override
    public void withdraw(Number amount) throws FinanceOperationException {
        FinanceDataUtil.throwIfNotPositive(amount);
        BigDecimal newBalance = FinanceDataUtil.subtract(balance, amount);
        setBalance(newBalance);
    }

    @Override
    public Double getDebt() {
        if (getBalance() < 0) {
            return balance.multiply(BigDecimal.valueOf(-1)).doubleValue();
        }
        return 0.0;
    }
}
