package com.borisjerev.floLive.handler;

import com.borisjerev.floLive.entity.ColorEntity;
import com.borisjerev.floLive.model.Color;
import com.borisjerev.floLive.repository.ColorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
public class ColorHandler implements WebSocketHandler {

    @Autowired
    private ColorRepository colorRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session
                .send( session.receive()
                        .map(msg -> {
                            System.out.println(msg.getPayloadAsText());

                            Color color = null;
                            try {
                                color = objectMapper.readValue(msg.getPayloadAsText(), Color.class);
                            } catch (Exception ex) {
                                System.out.println("Wrong format: " + msg.getPayloadAsText());
                            }
                            if (color != null) {
                                colorRepository.save(
                                        new ColorEntity(
                                                color.getRed(), color.getGreen(), color.getBlue(),
                                                color.getTimestamp()
                                        )
                                );
                            }
                            return msg.getPayloadAsText();
                        })
                        .map(session::textMessage)
                );
    }
}