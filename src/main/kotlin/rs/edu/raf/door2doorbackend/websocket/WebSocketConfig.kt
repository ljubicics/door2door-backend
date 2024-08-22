package rs.edu.raf.door2doorbackend.websocket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig @Autowired constructor(
    private val deliveryWebSocketHandler: DeliveryWebSocketHandler
): WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(deliveryWebSocketHandler, "/ws")
            .setAllowedOrigins("*")
    }
}