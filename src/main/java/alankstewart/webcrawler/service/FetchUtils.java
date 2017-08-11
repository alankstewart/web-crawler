package alankstewart.webcrawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.util.Random;

public final class FetchUtils {

    private static final Random RND = new Random();
    private static final int RETRY_MAX = 2;
    private static final int RETRY_MS = 100;

    /**
     * Fetch a JSoup document, retrying up to the {@link #RETRY_MAX} limit
     * and applying a random {@link #RETRY_MS} delay after failed requests.
     *
     * @return document
     * @throws IOException if retry limit reached
     */
    @SuppressWarnings("SleepWhileInLoop")
    public static Document getDoc(URI location) throws IOException {
        int attempts = 0;
        while (true) {
            attempts++;
            try {
                return Jsoup.parse(location.toURL(), 2000);
            } catch (final IOException e) {
                if (attempts == RETRY_MAX) {
                    throw new IOException(location + " failed " + RETRY_MAX + " times", e);
                }
                final int delayMs = RND.nextInt(RETRY_MS);
                try {
                    Thread.sleep(delayMs);
                } catch (final InterruptedException ignored) {
                }
            }
        }
    }
}
