package com.lendinglibrary.libraryapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public RestClient aiRestClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000); // 3 δευτερόλεπτα timeout σύνδεσης
        factory.setReadTimeout(3000);    // 3 δευτερόλεπτα timeout ανάγνωσης

        return RestClient.builder()
                .baseUrl("http://localhost:8000") // Το base URL του FastAPI σου
                .requestFactory(factory)
                .build();
    }
}