package tinnova.test.com.example.demo.application.integrations.dto;

import java.math.BigDecimal;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrankfurterUsdBrlResponse {

    private Map<String, BigDecimal> rates;
}
