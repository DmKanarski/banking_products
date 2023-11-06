package by.kanarski.bankingproducts.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonMapper {

    private static final String CURRENCIES_JSON_PATH = "currencies.json";

    public static SupportedCurrencies getSupportedCurrencies() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                InputStream configDataIs = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(CURRENCIES_JSON_PATH);
                Reader configDataReader = new InputStreamReader(configDataIs)
        ) {
            return objectMapper.readValue(configDataReader, SupportedCurrencies.class);
        } catch (NullPointerException | IOException e) {
            System.exit(1);
            return null;
        }
    }

}