package rs.edu.raf.door2doorbackend.delivery.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rs.edu.raf.door2doorbackend.delivery.model.Delivery

@Repository
interface DeliveryRepository: JpaRepository<Delivery, Long> {
}