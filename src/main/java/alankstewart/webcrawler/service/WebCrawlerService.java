package alankstewart.webcrawler.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
public class WebCrawlerService {

    public WebPage crawl(URI uri, int depth) throws IOException {
        return new WebPage(uri, depth);
    }
}
