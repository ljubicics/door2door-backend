package rs.edu.raf.door2doorbackend.account.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController {

    @GetMapping
    fun getAccounts(): ResponseEntity<String> {
        return ResponseEntity.ok().body("Hello from account controller")
    }
}