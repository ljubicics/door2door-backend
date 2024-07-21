package rs.edu.raf.door2doorbackend.account.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.edu.raf.door2doorbackend.account.service.AccountService

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(
    val accountService: AccountService
) {

    @GetMapping
    fun getAccounts(): ResponseEntity<String> {
        val accountDto = accountService.findByUsername("admin")
        return ResponseEntity.ok().body(accountDto.toString())
    }
}