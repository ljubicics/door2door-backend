package rs.edu.raf.door2doorbackend.delivery.dto

data class StartDeliveryDto(
    val senderId: Long,
    val pickupLocation: String,
    val deliveryLocation: String,
    val receiverEmail: String
)
