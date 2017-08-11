package alankstewart.webcrawler.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class WebPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebPage.class);

    @JsonProperty("url")
    private final URI location;

    @JsonIgnore
    private int depth;

    @JsonProperty
    private String title;

    @JsonIgnore
    private final WebPage parent;

    @JsonProperty("nodes")
    private final Set<WebPage> children = new HashSet<>();

    @JsonCreator
    public WebPage(@JsonProperty("url") final URI location, @JsonProperty("depth") final int depth) throws IOException {
        this(location, depth, null);
    }

    private WebPage(final URI location, final int depth, final WebPage parent) throws IOException {
        this.location = location;
        this.depth = depth < 0 ? 0 : depth;
        this.parent = parent;
        visit();
    }

    public URI getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public Set<WebPage> getChildren() {
        return children;
    }

    private WebPage priorVisit(final URI location) {
        if (this.location.equals(location)) {
            return this;
        }
        return parent == null ? null : parent.priorVisit(location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebPage webPage = (WebPage) o;
        return Objects.equals(location, webPage.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    private void visit() throws IOException {
        final Document doc;
        try {
            doc = FetchUtils.getDoc(location);
        } catch (final Throwable t) {
            LOGGER.debug(t.getMessage());
            return;
        }
        title = doc.title();
        if (depth <= 0) {
            return;
        }

        for (final Element elem : doc.select("a[href]")) {
            final URI childUri;
            try {
                childUri = new URI(elem.absUrl("href"));
            } catch (final URISyntaxException e) {
                continue;
            }
            final WebPage prevChild = priorVisit(childUri);
            if (prevChild == null) {
                children.add(new WebPage(childUri, depth - 1, this));
            } else {
                children.add(prevChild);
            }
        }
    }
}