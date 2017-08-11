package alankstewart.webcrawler.web;

import alankstewart.webcrawler.WebCrawlerApplication;
import alankstewart.webcrawler.service.WebPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Tests for {@link WebCrawlerController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebCrawlerApplication.class, webEnvironment = RANDOM_PORT)
public class WebCrawlerControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCrawlUrlWithDefaultDepth() throws Exception {
        URL url = new URL("http://repo1.maven.org/maven2/org/springframework/boot/spring-boot-parent");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("/webcrawler/crawl")
                .queryParam("url", url);

        ResponseEntity<WebPage> response = restTemplate.getForEntity(builder.toUriString(), WebPage.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        WebPage webPage = response.getBody();
        assertThat(webPage.getChildren(), hasSize(greaterThan(0)));
    }

    @Test
    public void shouldCrawlUrlWithDepthSpecified() throws Exception {
        URL url = new URL("http://repo1.maven.org/maven2/org/springframework/boot/spring-boot-parent");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("/webcrawler/crawl")
                .queryParam("url", url)
                .queryParam("depth", 0);

        ResponseEntity<WebPage> response = restTemplate.getForEntity(builder.toUriString(), WebPage.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        WebPage webPage = response.getBody();
        assertThat(webPage.getChildren(), hasSize(equalTo(0)));
    }
}
