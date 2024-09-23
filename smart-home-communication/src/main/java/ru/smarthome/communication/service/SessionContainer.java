package ru.smarthome.communication.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SessionContainer {

    private final Map<String, WebSocketSession> sessions
            = new ConcurrentHashMap<>();

    public void addSession(String id, WebSocketSession session) {
        sessions.put(id, session);
    }

    Optional<WebSocketSession> getSession(String id) {
        return Optional.ofNullable(sessions.get(id));
    }

    public void removeSession(String id) {
        sessions.remove(id);
    }
}
