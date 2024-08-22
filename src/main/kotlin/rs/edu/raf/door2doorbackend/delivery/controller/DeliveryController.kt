package rs.edu.raf.door2doorbackend.delivery.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import rs.edu.raf.door2doorbackend.delivery.dto.StartDeliveryDto
import rs.edu.raf.door2doorbackend.delivery.model.Delivery
import rs.edu.raf.door2doorbackend.delivery.model.enums.DeliveryStatus
import rs.edu.raf.door2doorbackend.delivery.service.DeliveryService


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/deliveries")
class DeliveryController @Autowired constructor(
    private val deliveryService: DeliveryService
) {

    @PreAuthorize("hasRole('ROLE_DELIVERY')")
    @GetMapping(path = ["/driver"], produces = ["application/json"])
    fun getAllDeliveriesForDriver(@RequestParam("id") id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(deliveryService.getAllDeliveriesForDriver(driverId = id))
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_DELIVERY')")
    @GetMapping(path = ["/driver/inProgress"], produces = ["application/json"])
    fun getAllInProgressDeliveriesForDriver(@RequestParam("id") id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(deliveryService.getInProgressDeliveriesForDriver(driverId = id))
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping(path = ["/customer"], produces = ["application/json"])
    fun getAllDeliveriesForCustomer(@RequestParam("id") id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(deliveryService.getAllDeliveriesForCustomer(customerId = id))
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping(path = ["/customer/inProgress"], produces = ["application/json"])
    fun getAllInProgressDeliveriesForCustomer(@RequestParam("id") id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(deliveryService.getAllInProgressDeliveriesForCustomer(customerId = id))
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping(path = ["/start"], produces = ["application/json"], consumes = ["application/json"])
    fun startDelivery(@RequestBody startDeliveryDto: StartDeliveryDto): ResponseEntity<Any> {
        return try {
            deliveryService.startDelivery(startDeliveryDto = startDeliveryDto)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_DELIVERY')")
    @GetMapping(path = ["/changeStatus"], produces = ["application/json"])
    fun changeDeliveryStatus(
        @RequestParam("id") id: Long,
        @RequestParam("status") status: String
    ): ResponseEntity<Any> {
        return try {
            val deliveryStatus = when (status) {
                "ACCEPTED" -> DeliveryStatus.ACCEPTED
                "IN_PROGRESS" -> DeliveryStatus.IN_PROGRESS
                "DELIVERED" -> DeliveryStatus.DELIVERED
                else -> DeliveryStatus.INVALID
            }
            if (deliveryStatus == DeliveryStatus.INVALID) {
                return ResponseEntity.badRequest().build()
            } else {
                deliveryService.changeDeliveryStatus(deliveryId = id, status = deliveryStatus)
            }
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping(path = ["/info"], produces = ["application/json"])
    fun getDeliveryInfo(@RequestParam("id") id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(deliveryService.findDeliveryById(deliveryId = id))
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_DELIVERY')")
    @GetMapping(path = ["/confirm"], produces = ["application/json"])
    fun confirmDelivery(
        @RequestParam("id") id: Long,
        @RequestParam("code") trackingCode: String,
        @RequestParam("receiverId") receiverId: Long
    ): ResponseEntity<Any> {
        return try {
            deliveryService.confirmDelivery(
                deliveryId = id,
                trackingCode = trackingCode,
                receiverId = receiverId
            )
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PreAuthorize("hasRole('ROLE_DELIVERY')")
    @GetMapping(path = ["/acceptDelivery"], produces = ["application/json"])
    fun acceptDelivery(
        @RequestParam("deliveryId") id: Long,
        @RequestParam("driverId") driverId: Long
    ): ResponseEntity<Any> {
        return try {
            deliveryService.acceptDelivery(deliveryId = id, driverId = driverId)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @MessageMapping("/newDelivery")
    @SendTo("/topic/deliveries")
    fun newDelivery(delivery: Delivery): Delivery {
        return delivery
    }

}