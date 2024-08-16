package rs.edu.raf.door2doorbackend.user.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.edu.raf.door2doorbackend.user.service.UserService

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/info", produces = ["application/json"])
    fun getUserInfo(@RequestParam("username") username: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(userService.getUserInfo(username))
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }
}