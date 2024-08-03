package rs.edu.raf.door2doorbackend.delivery.mapper

import org.mapstruct.Mapper
import rs.edu.raf.door2doorbackend.delivery.dto.DeliveryDto
import rs.edu.raf.door2doorbackend.delivery.model.Delivery

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
interface DeliveryMapper {
    fun deliveryToDeliveryDto(delivery: Delivery): DeliveryDto
    fun deliveryDtoToDelivery(deliveryDto: DeliveryDto): Delivery
}