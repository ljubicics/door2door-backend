package rs.edu.raf.door2doorbackend.auth.dto.login

data class LoginRequest(
    val username: String? = "",
    val password: String? = ""
)
