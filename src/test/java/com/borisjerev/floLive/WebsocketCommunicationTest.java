package com.borisjerev.floLive;

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
import reactor.core.publisher.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

// static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebsocketCommunicationTest {

    @LocalServerPort
    private String port;

    private static final ObjectMapper  objectMapper= new ObjectMapper();

    @Test
    public void color() throws Exception {
        int count = 1;
        String inputObject = objectMapper.writeValueAsString(new Color(0, 0, 0));
        Flux<String> input = Flux.range(1, count).map(index -> inputObject);
        ReplayProcessor<Object> output = ReplayProcessor.create(count);

        WebSocketClient client = new StandardWebSocketClient();
        client.execute(getUrl("/color"),
                session -> session
                        .send(input.map(session::textMessage))
                        //.thenReturn(session.receive().map(WebSocketMessage::getPayloadAsText))
                        .thenMany(session.receive().take(count).map(WebSocketMessage::getPayloadAsText))
                        .subscribeWith(output)
                        .then())
                .block(Duration.ofMillis(5000));

        assertEquals(input.collectList().block(Duration.ofMillis(5000)).get(0),
                output.collectList().block(Duration.ofMillis(5000)).get(0));
    }

    protected URI getUrl(String path) throws URISyntaxException {
        return new URI("ws://localhost:" + this.port + path);
    }
}
