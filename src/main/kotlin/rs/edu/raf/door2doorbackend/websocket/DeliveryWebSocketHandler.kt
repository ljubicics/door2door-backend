package rs.edu.raf.door2doorbackend.websocket

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import rs.edu.raf.door2doorbackend.delivery.model.Delivery
import java.util.concurrent.CopyOnWriteArrayList

@Component
class DeliveryWebSocketHandler : TextWebSocketHandler() {

    private var sessions = CopyOnWriteArrayList<WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
        println("New connection established with session id: ${session.id}")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: org.springframework.web.socket.CloseStatus) {
        println("ZATVARAM KONEKCIJU")
        sessions.remove(session)
        println("Connection closed with session id: ${session.id}, status: $status")
    }

    fun notifyAllClients(delivery: Delivery) {
        println("Calling notifyAllClients ${sessions.size}")
        val message = convertDeliveryToMessage(delivery)
        for (session in sessions) {
            println("Sending message to session id: ${session.id}")
            session.sendMessage(TextMessage(message))
        }
    }

    private fun convertDeliveryToMessage(delivery: Delivery): String {
        return "ID ${delivery.id}"
    }
}