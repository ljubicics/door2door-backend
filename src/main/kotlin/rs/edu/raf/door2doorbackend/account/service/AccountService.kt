package rs.edu.raf.door2doorbackend.account.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rs.edu.raf.door2doorbackend.account.dto.AccountDto
import rs.edu.raf.door2doorbackend.account.mapper.AccountMapper
import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.account.repository.AccountRepository

@Service
class AccountService @Autowired constructor(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper
) {

    fun findByUsername(username: String?): AccountDto {
        return accountMapper.accountToAccountDto(
            accountRepository.findAccountByUsername(
                username ?: throw Exception("Username not provided")
            ) ?: throw Exception("Account not found")
        )
    }

    fun saveAccount(account: Account) {
        accountRepository.save(account)
    }
}