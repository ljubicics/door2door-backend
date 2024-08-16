package rs.edu.raf.door2doorbackend.delivery.dto

import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.delivery.model.enums.DeliveryStatus

data class DeliveryDto(
    val id: Long = 0,
    val timeStarted: Long = 0,
    val timeDelivered: Long = 0,
    val trackingCode: String? = null,
    val status: DeliveryStatus? = null,
    val qrConfirmed: Boolean = false,
    val pickupLocation: String = "",
    val deliveryLocation: String = "",
    val sender: Account? = null,
    val receiver: Account? = null,
    val driver: Account? = null
)