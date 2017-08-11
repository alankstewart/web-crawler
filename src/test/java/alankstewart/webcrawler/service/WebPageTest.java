package alankstewart.webcrawler.service;

import org.junit.Test;

import java.net.URI;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class WebPageTest {

    @Test
    public void shouldCrawlUrl() throws Exception {
        final WebPage webPage = new WebPage(new URI("http://repo1.maven.org/maven2/org/springframework/boot/spring-boot-parent"), 1);
        assertThat(webPage, is(notNullValue()));
    }
}
