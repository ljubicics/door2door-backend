package rs.edu.raf.door2doorbackend.account.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rs.edu.raf.door2doorbackend.account.model.Account

@Repository
interface AccountRepository: JpaRepository<Account, Long> {

    fun findAccountByUsername(username: String): Account?

    fun findAccountByUserEmail(email: String): Account?
}