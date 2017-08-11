package alankstewart.webcrawler.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Service
public class WebCrawlerService {

    public WebPage crawl(URI uri, int depth) throws IOException {
        WebPage webPage = new WebPage(uri, depth);
        return webPage;
    }
}
