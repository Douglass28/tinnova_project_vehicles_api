package tinnova.test.com.example.demo.application.integrations.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwesomeUsdBrlResponse {

    private UsdBrlQuote USDBRL;

    @Getter
    @Setter
    public static class UsdBrlQuote {
        private String bid;
    }
}
