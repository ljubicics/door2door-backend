package rs.edu.raf.door2doorbackend.delivery.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rs.edu.raf.door2doorbackend.delivery.dto.DeliveryDto
import rs.edu.raf.door2doorbackend.delivery.mapper.DeliveryMapper
import rs.edu.raf.door2doorbackend.delivery.repository.DeliveryRepository

@Service
class DeliveryService @Autowired constructor(
    private val deliveryRepository: DeliveryRepository,
    private val deliveryMapper: DeliveryMapper
) {

    fun getAllDeliveries(): List<DeliveryDto> {
        return deliveryRepository.findAll().stream().map { deliveryMapper.deliveryToDeliveryDto(it) }.toList()
    }
}