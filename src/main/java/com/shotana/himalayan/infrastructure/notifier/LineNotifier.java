package com.shotana.himalayan.infrastructure.notifier;

import com.shotana.himalayan.domain.Notifier;
import com.shotana.himalayan.domain.model.Token;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class LineNotifier implements Notifier {

    private static final String LINE_URL = "https://notify-api.line.me/api/notify";

    private RestTemplate restTemplate;

    public LineNotifier(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void send(Token token, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + token.getText());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("message", message);

        RequestEntity<MultiValueMap<String, String>> request =
                new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(LINE_URL));

        try {
            restTemplate.exchange(request, Object.class);
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }
    }
}
