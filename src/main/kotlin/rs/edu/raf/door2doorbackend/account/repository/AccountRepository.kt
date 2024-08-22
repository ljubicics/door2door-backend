package rs.edu.raf.door2doorbackend.account.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.account.model.DeliveryAccount

@Repository
interface AccountRepository: JpaRepository<Account, Long> {

    fun findAccountByUsername(username: String): Account?

    fun findAccountByUserEmail(email: String): Account?

    @Query("SELECT d FROM DeliveryAccount d")
    fun findAllDeliveryAccounts(): List<DeliveryAccount>
}