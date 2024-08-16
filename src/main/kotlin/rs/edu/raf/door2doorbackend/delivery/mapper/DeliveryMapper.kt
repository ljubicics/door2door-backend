package rs.edu.raf.door2doorbackend.delivery.mapper

import org.springframework.stereotype.Component
import rs.edu.raf.door2doorbackend.delivery.dto.DeliveryDto
import rs.edu.raf.door2doorbackend.delivery.model.Delivery

@Component
class DeliveryMapper {
    fun deliveryToDeliveryDto(delivery: Delivery): DeliveryDto {
        return DeliveryDto(
            id = delivery.id,
            timeStarted = delivery.timeStarted,
            timeDelivered = delivery.timeDelivered,
            trackingCode = delivery.trackingCode,
            status = delivery.status,
            qrConfirmed = delivery.qrConfirmed,
            pickupLocation = delivery.pickupLocation,
            deliveryLocation = delivery.deliveryLocation,
            sender = delivery.sender,
            receiver = delivery.receiver,
            driver = delivery.driver
        )
    }
}