package rs.edu.raf.door2doorbackend.delivery.model

import jakarta.persistence.*
import rs.edu.raf.door2doorbackend.account.model.Account

@Entity
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val timeStarted: Long = 0,
    val timeDelivered: Long = 0,
    val trackingCode: String? = null,
    @ManyToOne
    val sender: Account,
    @ManyToOne
    val deliveryAgent: Account
)