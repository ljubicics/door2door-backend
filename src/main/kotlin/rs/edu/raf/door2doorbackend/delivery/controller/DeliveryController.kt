package rs.edu.raf.door2doorbackend.delivery.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.edu.raf.door2doorbackend.delivery.dto.StartDeliveryDto
import rs.edu.raf.door2doorbackend.delivery.service.DeliveryService


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/deliveries")
class DeliveryController @Autowired constructor(
    private val deliveryService: DeliveryService
) {

    @GetMapping(path = [""], produces = ["application/json"])
    fun getAllDeliveries(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(deliveryService.getAllDeliveries())
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @PostMapping(path = ["/start"], produces = ["application/json"], consumes = ["application/json"])
    fun startDelivery(@RequestBody startDeliveryDto: StartDeliveryDto): ResponseEntity<Any> {
        return try {
            deliveryService.startDelivery(startDeliveryDto = startDeliveryDto)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }
}