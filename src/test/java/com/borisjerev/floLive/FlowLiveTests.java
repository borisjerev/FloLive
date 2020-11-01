package com.borisjerev.floLive;
//TODO
import com.borisjerev.floLive.model.Color;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.StandardWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ReplayProcessor;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlowLiveTests {

    @LocalServerPort
    private String port;

    private static final ObjectMapper  objectMapper= new ObjectMapper();

    @Test
    public void color() throws Exception {
//        int count = 1;
//        Mono<String> input = Mono.just(objectMapper.writeValueAsString(new Color(0, 0, 0)));
//        ReplayProcessor<Object> output = ReplayProcessor.create(count);
//
//        WebSocketClient client = new StandardWebSocketClient();
//        client.execute(getUrl("/color"),
//                session -> session
//                        .send(input.map(session::textMessage))
//                        .thenMany(session.receive().map(WebSocketMessage::getPayloadAsText))
//                        .subscribeWith(output)
//                        .then())
//                .block(Duration.ofMillis(5000));
//
//        assertEquals(input.block(Duration.ofMillis(5000)), output.collectList().block(Duration.ofMillis(5000)));
    }

    protected URI getUrl(String path) throws URISyntaxException {
        return new URI("ws://localhost:" + this.port + path);
    }
}
