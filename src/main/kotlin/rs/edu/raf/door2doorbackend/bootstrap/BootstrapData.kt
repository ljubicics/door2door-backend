package rs.edu.raf.door2doorbackend.bootstrap

import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.account.repository.AccountRepository
import rs.edu.raf.door2doorbackend.auth.util.PasswordEncryptor
import rs.edu.raf.door2doorbackend.user.model.User
import rs.edu.raf.door2doorbackend.user.repository.UserRepository

@Component
@AllArgsConstructor
class BootstrapData @Autowired constructor(
    val accountRepository: AccountRepository,
    val userRepository: UserRepository,
    private val passwordEncryptor: PasswordEncryptor
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (accountRepository.count().toInt() == 0) {
            val user = User(
                name = "Strahinja",
                surname = "Ljubicic",
                email = "strahinja.ljubicic@gmail.com",
                mobileNumber = "123456789",
                address = "Neka adresa"
            )

            userRepository.save(user)

            val account = Account(
                username = "admin",
                password = passwordEncryptor.passwordEncoder().encode("admin"),
                role = "ADMIN",
                user = user
            )

            accountRepository.save(account)
        }
    }
}