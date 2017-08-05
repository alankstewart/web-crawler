package alankstewart.webcrawler.domain

case class WebNode(url: String, title: String, nodes: Seq[WebNode])
