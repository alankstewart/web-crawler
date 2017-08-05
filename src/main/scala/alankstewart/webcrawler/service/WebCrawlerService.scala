package alankstewart.webcrawler.service

import java.net.URL

import alankstewart.webcrawler.domain.WebNode

trait WebCrawlerService {

  def search(url: URL, depth: Int): WebNode
}
