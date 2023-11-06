package by.kanarski.bankingproducts.products.interfaces;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;

public interface Withdrawable {

    void withdraw(Number amount) throws FinanceOperationException;
    
}
