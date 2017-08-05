package alankstewart.webcrawler.web;

import alankstewart.webcrawler.domain.WebNode;
import alankstewart.webcrawler.service.WebCrawlerService;
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

    @GetMapping("/search")
    public ResponseEntity<WebNode> search(@RequestParam URL url, @RequestParam(defaultValue = "10") int depth) throws Exception {
        return ResponseEntity.ok(webCrawlerService.search(url, depth));
    }
}
