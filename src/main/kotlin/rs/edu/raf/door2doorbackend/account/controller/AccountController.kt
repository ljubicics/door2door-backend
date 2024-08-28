package rs.edu.raf.door2doorbackend.account.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import rs.edu.raf.door2doorbackend.account.service.AccountService

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(
    val accountService: AccountService
) {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun getAccounts(): ResponseEntity<String> {
        val accountDto = accountService.findByUsername(username = "admin")
        return ResponseEntity.ok().body(accountDto.toString())
    }

    @GetMapping(path = ["/getAccountInfo"], produces = ["application/json"])
    fun getAccountInfo(@RequestParam("username") username: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(accountService.getAccountInfo(username = username))
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }
}