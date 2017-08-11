# web-crawler

[![Build Status](https://travis-ci.org/alankstewart/web-crawler.svg?branch=master)](https://travis-ci.org/alankstewart/web-crawler)

Simple web crawler that provides deep-crawling.

The endpoint `/webcrawler/crawl` requires a `url` and a `depth` parameter. For example,

```
curl -H "Content-Type: application/json" -X GET "http://localhost:8080/webcrawler/crawl?url=https://www.qantas.com&depth=1"

```
