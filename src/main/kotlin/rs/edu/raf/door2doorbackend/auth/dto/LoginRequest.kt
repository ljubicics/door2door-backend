package rs.edu.raf.door2doorbackend.auth.dto

data class LoginRequest(
    val username: String? = "",
    val password: String? = ""
)
