package rs.edu.raf.door2doorbackend.delivery.dto

import rs.edu.raf.door2doorbackend.account.model.Account

data class DeliveryDto(
    val id: Long = 0,
    val timeStarted: Long = 0,
    val timeDelivered: Long = 0,
    val trackingCode: String? = null,
    val sender: Account,
    val deliveryAgent: Account
)