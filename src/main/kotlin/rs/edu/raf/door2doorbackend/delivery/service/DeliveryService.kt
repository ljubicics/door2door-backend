package rs.edu.raf.door2doorbackend.delivery.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rs.edu.raf.door2doorbackend.account.repository.AccountRepository
import rs.edu.raf.door2doorbackend.delivery.dto.DeliveryDto
import rs.edu.raf.door2doorbackend.delivery.dto.StartDeliveryDto
import rs.edu.raf.door2doorbackend.delivery.mapper.DeliveryMapper
import rs.edu.raf.door2doorbackend.delivery.model.Delivery
import rs.edu.raf.door2doorbackend.delivery.model.enums.DeliveryStatus
import rs.edu.raf.door2doorbackend.delivery.repository.DeliveryRepository

@Service
class DeliveryService @Autowired constructor(
    private val deliveryRepository: DeliveryRepository,
    private val deliveryMapper: DeliveryMapper,
    private val accountRepository: AccountRepository
) {

    fun getAllDeliveries(): List<DeliveryDto> {
        return deliveryRepository.findAll().stream().map { deliveryMapper.deliveryToDeliveryDto(it) }.toList()
    }

    fun getAllDeliveriesForDriver(driverId: Long): List<DeliveryDto> {
        return deliveryRepository.findAllByDriverId(driverId)
            .stream().map { deliveryMapper.deliveryToDeliveryDto(it) }.toList()
    }

    fun getInProgressDeliveriesForDriver(driverId: Long): DeliveryDto {
        return deliveryMapper.deliveryToDeliveryDto(
            deliveryRepository.findByDriverIdAndStatusIn(
                driverId,
                listOf(
                    DeliveryStatus.ACCEPTED,
                    DeliveryStatus.IN_PROGRESS
                )
            )
        )
    }

    fun getAllDeliveriesForCustomer(customerId: Long): List<DeliveryDto> {
        return deliveryRepository.findAllByReceiverIdAndStatusIn(
            customerId,
            listOf(
                DeliveryStatus.DELIVERED
            )
        )
            .stream().map { deliveryMapper.deliveryToDeliveryDto(it) }.toList()
    }

    fun changeDeliveryStatus(deliveryId: Long, status: DeliveryStatus): Boolean {
        return deliveryRepository.updateDeliveryStatus(id = deliveryId, status = status) > 0
    }

    fun getAllInProgressDeliveriesForCustomer(customerId: Long): List<DeliveryDto> {
        return deliveryRepository.findAllByReceiverIdAndStatusIn(
            customerId, listOf(
                DeliveryStatus.PENDING,
                DeliveryStatus.ACCEPTED,
                DeliveryStatus.IN_PROGRESS
            )
        ).stream().map { deliveryMapper.deliveryToDeliveryDto(it) }.toList()
    }

    fun startDelivery(startDeliveryDto: StartDeliveryDto) {
        val sender = accountRepository.findById(startDeliveryDto.senderId).get()
        val receiver = accountRepository.findAccountByUserEmail(startDeliveryDto.receiverEmail)
            ?: throw Exception("Receiver not found")

        val delivery = Delivery(
            timeStarted = System.currentTimeMillis(),
            status = DeliveryStatus.PENDING,
            sender = sender,
            receiver = receiver,
            driver = null,
            pickupLocation = startDeliveryDto.pickupLocation,
            deliveryLocation = startDeliveryDto.deliveryLocation,
        )

        deliveryRepository.save(delivery)
        // TODO: FIND DELIVERY AGENT AND ASSIGN DELIVERY ALSO GENERATE QR CODE,
        //  SEND AND SEND EMAIL TO RECEIVER
    }

    fun findAllPendingDeliveriesForReceiver(receiverId: Long): List<DeliveryDto> {
        return deliveryRepository.findAllByReceiverIdAndStatusIn(
            receiverId = receiverId,
            status = listOf(
                DeliveryStatus.PENDING,
                DeliveryStatus.ACCEPTED,
                DeliveryStatus.IN_PROGRESS
            )
        ).stream().map { deliveryMapper.deliveryToDeliveryDto(it) }.toList()
    }
}