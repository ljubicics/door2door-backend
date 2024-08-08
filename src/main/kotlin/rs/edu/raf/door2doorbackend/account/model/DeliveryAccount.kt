package rs.edu.raf.door2doorbackend.account.model

import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
class DeliveryAccount(
    var earnings: BigDecimal = BigDecimal.ZERO,
    var rating: Double = 0.0,
    var numberOfRatings: Int = 0,
    var numberOfDeliveries: Int = 0,
    var numberOfCanceledDeliveries: Int = 0
) : Account(){
}