package alankstewart.webcrawler.web;

import alankstewart.webcrawler.WebCrawlerApplication;
import alankstewart.webcrawler.domain.WebNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Tests for {@link WebCrawlerController}.
 * <p>
 * Note that for HttpMethod.PATCH requests, RestTemplate requires the HttpComponentsClientHttpRequestFactory, ie
 * <br>
 * private RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebCrawlerApplication.class, webEnvironment = RANDOM_PORT)
public class WebCrawlerControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCrawlUrlWithDefaultDepth() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("/webcrawler/search")
                .queryParam("url", new URL("https://google.com"))
                .queryParam("depth", 5);
        ResponseEntity<WebNode> response = restTemplate.getForEntity(builder.toUriString(), WebNode.class);
        WebNode body = response.getBody();
        assertThat(body, is(notNullValue()));
        assertThat(body.url(), is("https://google.com"));
    }
}
