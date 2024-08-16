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
    val timeDelivered: Long = 0,
    val trackingCode: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus? = null,
    val qrConfirmed: Boolean = false,
    // TODO: Sender bi trebalo da bude lokacija gde dostavljac da pokupi
    //  paket, tu lokaciju unosi operater preko web-a
    val pickupLocation: String,
    val deliveryLocation: String,
    @ManyToOne
    val sender: Account,
    @ManyToOne
    val receiver: Account,
    @ManyToOne
    val driver: DeliveryAccount?
)