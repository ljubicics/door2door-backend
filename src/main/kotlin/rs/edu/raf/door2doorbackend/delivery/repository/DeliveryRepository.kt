package rs.edu.raf.door2doorbackend.delivery.repository

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import rs.edu.raf.door2doorbackend.delivery.model.Delivery
import rs.edu.raf.door2doorbackend.delivery.model.enums.DeliveryStatus


@Repository
interface DeliveryRepository: JpaRepository<Delivery, Long> {
    fun findAllByDriverIdAndStatusIn(driverId: Long, status: List<DeliveryStatus>): List<Delivery>
    fun findByDriverIdAndStatusIn(driverId: Long, status: List<DeliveryStatus>): Delivery
    fun findAllByReceiverIdAndStatusIn(receiverId: Long, status: List<DeliveryStatus>): List<Delivery>

    @Modifying
    @Transactional
    @Query("UPDATE Delivery d SET d.status = :status WHERE d.id = :id")
    fun updateDeliveryStatus(id: Long?, status: DeliveryStatus?): Int
}