package by.kanarski.bankingproducts.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Set;

@Data
public class SupportedCurrencies {

    @JsonProperty("defaultSupportedCurrency")
    private String defaultCurrencyCode;
    @JsonProperty("supportedCurrencies")
    private Set<String> currencyCodes;

}
