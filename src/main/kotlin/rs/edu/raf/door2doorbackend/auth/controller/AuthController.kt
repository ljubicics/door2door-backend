package rs.edu.raf.door2doorbackend.auth.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.edu.raf.door2doorbackend.account.service.AccountService
import rs.edu.raf.door2doorbackend.auth.dto.LoginRequest
import rs.edu.raf.door2doorbackend.auth.dto.LoginResponse
import rs.edu.raf.door2doorbackend.auth.service.AuthService
import rs.edu.raf.door2doorbackend.auth.util.JwtUtil

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
class AuthController @Autowired constructor(
    private val authService: AuthService,
    private val accountService: AccountService,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
) {

    @PostMapping(path = ["/login"], consumes = ["application/json"], produces = ["application/json"])
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        try {
            println("loginRequest: $loginRequest")
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
        } catch (e: Exception) {
            return ResponseEntity.status(401).build()
        }
        return ResponseEntity.ok(LoginResponse(jwtUtil.generateToken(accountService.findByUsername(loginRequest.username))))
    }
}