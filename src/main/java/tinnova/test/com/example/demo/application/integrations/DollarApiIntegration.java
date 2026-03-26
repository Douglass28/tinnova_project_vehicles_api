package tinnova.test.com.example.demo.application.integrations;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import tinnova.test.com.example.demo.application.integrations.dto.AwesomeUsdBrlResponse;
import tinnova.test.com.example.demo.application.integrations.dto.FrankfurterUsdBrlResponse;

@Slf4j
@Component
public class DollarApiIntegration implements IDollarApiIntegration {

    private static final String USD_BRL_CACHE_NAME = "usdBrlQuote";
    private static final String USD_BRL_CACHE_KEY = "'usd_brl_latest'";
    private static final String BRL = "BRL";

    private final RestClient restClient;
    private final String awesomeUrl;
    private final String frankfurterUrl;

    public DollarApiIntegration(RestClient.Builder restClientBuilder,
        @Value("${app.integrations.dollar.awesome-url}") String awesomeUrl,
        @Value("${app.integrations.dollar.frankfurter-url}") String frankfurterUrl) {
        this.restClient = restClientBuilder.build();
        this.awesomeUrl = awesomeUrl;
        this.frankfurterUrl = frankfurterUrl;
    }

    @Override
    @Cacheable(value = USD_BRL_CACHE_NAME, key = USD_BRL_CACHE_KEY)
    public BigDecimal getUsdToBrlRate() {
        try {
            BigDecimal awesomeRate = fetchAwesomeRate();
            log.info("Cotacao USD/BRL obtida via AwesomeAPI");
            return awesomeRate;
        } catch (RuntimeException awesomeException) {
            log.warn("Falha na AwesomeAPI, tentando fallback Frankfurter: {}", awesomeException.getMessage());
        }

        try {
            BigDecimal fallbackRate = fetchFrankfurterRate();
            log.info("Cotacao USD/BRL obtida via fallback Frankfurter");
            return fallbackRate;
        } catch (RuntimeException fallbackException) {
            log.error("Falha ao obter cotacao USD/BRL em ambos provedores", fallbackException);
            throw new ResponseStatusException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Nao foi possivel obter a cotacao do dolar no momento"
            );
        }
    }

    private BigDecimal fetchAwesomeRate() {
        AwesomeUsdBrlResponse response = restClient.get()
            .uri(awesomeUrl)
            .retrieve()
            .body(AwesomeUsdBrlResponse.class);

        if (response == null || response.getUSDBRL() == null || response.getUSDBRL().getBid() == null) {
            throw new IllegalStateException("Resposta AwesomeAPI sem campo USDBRL.bid");
        }
        return new BigDecimal(response.getUSDBRL().getBid());
    }

    private BigDecimal fetchFrankfurterRate() {
        FrankfurterUsdBrlResponse response = restClient.get()
            .uri(frankfurterUrl)
            .retrieve()
            .body(FrankfurterUsdBrlResponse.class);

        if (response == null || response.getRates() == null || response.getRates().get(BRL) == null) {
            throw new IllegalStateException("Resposta Frankfurter sem campo rates.BRL");
        }
        return response.getRates().get(BRL);
    }
}
