package alankstewart.webcrawler.web;

import alankstewart.webcrawler.service.WebCrawlerService;
import alankstewart.webcrawler.service.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping(path = "/webcrawler")
public class WebCrawlerController {

    private WebCrawlerService webCrawlerService;

    @Autowired
    public WebCrawlerController(WebCrawlerService webCrawlerService) {
        this.webCrawlerService = webCrawlerService;
    }

    @GetMapping("/crawl")
    public ResponseEntity<WebPage> search(@RequestParam URL url, @RequestParam(defaultValue = "1") int depth) throws Exception {
        return ResponseEntity.ok(new WebPage(url.toURI(), depth));
    }
}
