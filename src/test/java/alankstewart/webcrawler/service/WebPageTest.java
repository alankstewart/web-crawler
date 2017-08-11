package alankstewart.webcrawler.service;

import org.junit.Test;

import java.net.URI;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class WebPageTest {

    @Test
    public void test() throws Exception {
        final WebPage home = new WebPage(new URI("http://www.carbonbikewheels.com.au/en/"), 0);
        assertThat(home, is(notNullValue()));
    }
}
