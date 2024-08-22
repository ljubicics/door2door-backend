package rs.edu.raf.door2doorbackend.delivery.model

import jakarta.persistence.*
import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.account.model.DeliveryAccount
import rs.edu.raf.door2doorbackend.delivery.model.enums.DeliveryStatus
import java.math.BigDecimal

@Entity
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val timeStarted: Long = 0,
    var timeDelivered: Long = 0,
    val trackingCode: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus? = null,
    var qrConfirmed: Boolean = false,
    val pickupLocation: String,
    val deliveryLocation: String,
    @ManyToOne
    val sender: Account,
    @ManyToOne
    val receiver: Account,
    @ManyToOne
    var driver: DeliveryAccount?
)