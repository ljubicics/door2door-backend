package rs.edu.raf.door2doorbackend.delivery.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class DeliveryDriverService(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val busyDeliveryDriversKey = "busyDeliveryDrivers"

    fun markDelivererAsBusy(delivererId: String) {
        redisTemplate.opsForSet().add(busyDeliveryDriversKey, delivererId)
    }

    fun markDelivererAsAvailable(delivererId: String) {
        redisTemplate.opsForSet().remove(busyDeliveryDriversKey, delivererId)
    }

    fun isDelivererBusy(delivererId: String): Boolean {
        return redisTemplate.opsForSet().isMember(busyDeliveryDriversKey, delivererId) == true
    }

    fun getAllBusyDeliverers(): Set<String> {
        return redisTemplate.opsForSet().members(busyDeliveryDriversKey) as Set<String>? ?: emptySet()
    }
}