package rs.edu.raf.door2doorbackend.delivery.dto

data class StartDeliveryDto(
    val senderEmail: String,
    val pickupLocation: String,
    val deliveryLocation: String,
    val receiverEmail: String
)
