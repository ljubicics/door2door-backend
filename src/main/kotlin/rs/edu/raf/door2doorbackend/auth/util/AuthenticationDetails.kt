package rs.edu.raf.door2doorbackend.auth.util

interface AuthenticationDetails {
    fun fetchId(): Long
    fun fetchUsername(): String
    fun fetchRole(): String
}