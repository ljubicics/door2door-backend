package rs.edu.raf.door2doorbackend.auth.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    private val secretKey = Jwts.SIG.HS512.key().build()

    fun extractAllClaims(token: String): Claims {
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload
        } catch (e: Exception) {
            throw Exception("Invalid token")
        }
    }

    fun extractUsername(token: String): String {
        return extractAllClaims(token).subject
    }

    fun validateToken(token: String, user: UserDetails): Boolean {
        return user.username == extractUsername(token) && !isTokenExpired(token)
    }

    fun generateToken(authenticationDetails: AuthenticationDetails): String {
        val claims = HashMap<String, Any>()
        claims["id"] = authenticationDetails.fetchId()
        claims["role"] = authenticationDetails.fetchRole()
        return Jwts.builder()
            .subject(authenticationDetails.fetchUsername())
            .claims(claims)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(secretKey).compact()
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date())
    }
}
