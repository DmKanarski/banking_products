package by.kanarski.bankingproducts.products.interfaces;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;

public interface IBankProduct {

    void topUp(Number amount) throws FinanceOperationException;

    Double getBalance();

}
