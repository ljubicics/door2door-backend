package rs.edu.raf.door2doorbackend.bootstrap

import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.account.repository.AccountRepository
import rs.edu.raf.door2doorbackend.auth.util.PasswordEncryptor
import rs.edu.raf.door2doorbackend.delivery.model.Delivery
import rs.edu.raf.door2doorbackend.delivery.repository.DeliveryRepository
import rs.edu.raf.door2doorbackend.role.model.Role
import rs.edu.raf.door2doorbackend.role.model.enums.RoleName
import rs.edu.raf.door2doorbackend.role.repository.RoleRepository
import rs.edu.raf.door2doorbackend.user.model.User
import rs.edu.raf.door2doorbackend.user.repository.UserRepository

@Component
@AllArgsConstructor
class BootstrapData @Autowired constructor(
    val accountRepository: AccountRepository,
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    private val passwordEncryptor: PasswordEncryptor,
    private val deliveryRepository: DeliveryRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (accountRepository.count().toInt() == 0) {

            val roleAdmin = Role(
                name = RoleName.ROLE_ADMIN
            )

            val roleNormalUser = Role(
                name = RoleName.ROLE_NORMAL_USER
            )

            val roleEmployee = Role(
                name = RoleName.ROLE_EMPLOYEE
            )

            roleRepository.saveAll(listOf(roleAdmin, roleEmployee, roleNormalUser))

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
                role = roleAdmin,
                user = user
            )

            accountRepository.save(account)

            val delivery = Delivery(
                timeStarted = System.currentTimeMillis(),
                timeDelivered = System.currentTimeMillis(),
                trackingCode = "123",
                sender = account,
                deliveryAgent = account
            )

            deliveryRepository.save(delivery)
        }
    }
}