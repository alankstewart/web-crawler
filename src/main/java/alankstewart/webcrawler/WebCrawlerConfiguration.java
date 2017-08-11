package alankstewart.webcrawler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class WebCrawlerConfiguration {

    @SuppressWarnings("unchecked")
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializationInclusion(JsonInclude.Include.NON_ABSENT)
                .featuresToDisable(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        SerializationFeature.INDENT_OUTPUT,
                        DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE,
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
