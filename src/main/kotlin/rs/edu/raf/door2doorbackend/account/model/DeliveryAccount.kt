package rs.edu.raf.door2doorbackend.account.model

import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
open class DeliveryAccount(
    open var earnings: BigDecimal = BigDecimal.ZERO,
    open var rating: Double = 0.0,
    open var numberOfRatings: Int = 0,
    open var numberOfDeliveries: Int = 0,
    open var numberOfCanceledDeliveries: Int = 0
) : Account()