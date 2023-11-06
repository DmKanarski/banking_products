package by.kanarski.bankingproducts.utils;

import by.kanarski.bankingproducts.exceptions.FinanceOperationException;
import by.kanarski.bankingproducts.exceptions.UnsupportedCurrencyException;
import java.math.BigDecimal;
import java.util.*;

public class FinanceDataUtil {

    public static final SupportedCurrencies SUPPORTED_CURRENCIES = JsonMapper.getSupportedCurrencies();

    public static boolean isCurrencySupported(String currencyCode) {
        return SUPPORTED_CURRENCIES.getCurrencyCodes().contains(currencyCode);
    }

    public static boolean isCurrencySupported(Currency currency) {
        return  isCurrencySupported(currency.getCurrencyCode());
    }

    public static Currency getDefaultCurrency() {
        return Currency.getInstance(SUPPORTED_CURRENCIES.getDefaultCurrencyCode());
    }

    public static String getDefaultCurrencyCode() {
        return SUPPORTED_CURRENCIES.getDefaultCurrencyCode();
    }

    public static void throwIfNotPositive(Number number, String message) throws FinanceOperationException {
        if (number.doubleValue() <= 0) {
            throw new FinanceOperationException(message);
        }
    }

    public static void throwIfNotPositive(Number number) throws FinanceOperationException {
        throwIfNotPositive(number,
                String.format("Only operations with positive numbers are allowed, '%f' is not positive number",
                number.doubleValue()));
    }

    public static void throwIfNegative(Number number, String message) throws FinanceOperationException {
        if (number.doubleValue() < 0) {
            throw new FinanceOperationException(message);
        }
    }

    public static void throwIfNegative(Number number) throws FinanceOperationException {
        throwIfNegative(number,
                String.format("Only operations with non-negative numbers are allowed, '%f' is negative number",
                number.doubleValue()));
    }

    public static void throwIfNotSupportedCurrency(String currencyCode) throws UnsupportedCurrencyException {
        if (!isCurrencySupported(currencyCode)) {
            throw new UnsupportedCurrencyException("Currency '%s' is not supported");
        }
    }

    public static void throwIfNotSupportedCurrency(Currency currency) throws UnsupportedCurrencyException {
        throwIfNotSupportedCurrency(currency.getCurrencyCode());
    }

    public static BigDecimal add(BigDecimal one, Number two) {
        return one.add(BigDecimal.valueOf(two.doubleValue()));
    }

    public static BigDecimal subtract(BigDecimal one, Number two) {
        return one.add(BigDecimal.valueOf(two.doubleValue() * (-1)));
    }

}
