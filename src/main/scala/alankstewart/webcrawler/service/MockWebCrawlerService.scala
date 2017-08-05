package alankstewart.webcrawler.service

import java.net.URL

import alankstewart.webcrawler.domain.WebNode
import com.typesafe.scalalogging.LazyLogging
import org.springframework.stereotype.Service

@Service
class MockWebCrawlerService
  extends WebCrawlerService
    with LazyLogging {

  override def search(url: URL, depth: Int): WebNode = {
    WebNode(url.toExternalForm, "title 1", Seq(WebNode("http://www.facebook.com", "Facebook", Seq.empty)))
  }
}