package by.kanarski.bankingproducts.products.interfaces;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;

public interface Creditable extends IBankProduct, Withdrawable {

    /**
     * Set the interest rate as a percentage
     * @param newInterestRate - new interest rate value
     */
    void setInterestRate(Double newInterestRate) throws FinanceOperationException;

    /**
     * Get the interest rate as a percentage
     * @return interest rate value
     */
    Double getInterestRate();

    Double getDebt();

}
