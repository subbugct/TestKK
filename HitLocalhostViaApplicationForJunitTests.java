package net.citi.taurus.refdata.api;

import net.citi.taurus.refdata.Application;
import net.citi.taurus.refdata.api.json.options.OptionsResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = {Application.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class OptionsIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String ENDPOINT;

    @Before
    public void setup() {
        ENDPOINT = ""http://localhost:"" + port + ""/products/options"";
    }

    /**
     * ***********************************************************
     * INTEGRATION TESTS
     * ***********************************************************
     */
    @Test
    public void Should_NotBeNull_When_RequestIsMade() {
        OptionsResponse response = optionsResponseFor(""ftse"", ""ukx"", ""2017-12-15"", ""IFLO"", 4500, 10);
        assertThat(response).isNotNull();
    }

 /**
     * ***********************************************************
     * HELPER METHODS
     * ***********************************************************
     */
    private String makeQuery( String ric
                            , String ets
                            , String expirationDate
                            , String exchange
                            , double centerStrike
                            , int numberOfResultsPerGroup ) {

        return ""?ric="" + ric +
                ""&ets="" + ets +
                ""&expirationDate="" + expirationDate +
                ""&exchange="" + exchange +
                ""&centerStrike="" + centerStrike +
                ""&numberOfResultsPerGroup="" + numberOfResultsPerGroup;
    }

    private OptionsResponse makeRequest(String query) {
        
        return restTemplate.getForObject(ENDPOINT + query, OptionsResponse.class);
    }

 private OptionsResponse optionsResponseFor( String ric
                                              , String ets
                                              , String expirationDate
                                              , String exchange
                                              , double centerStrike
                                              , int numberOfResultsPerGroup ) {

        return makeRequest(makeQuery(ric, ets, expirationDate, exchange, centerStrike, numberOfResultsPerGroup));
    }
}

	