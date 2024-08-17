package rs.edu.raf.door2doorbackend.bootstrap

import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.account.model.DeliveryAccount
import rs.edu.raf.door2doorbackend.account.repository.AccountRepository
import rs.edu.raf.door2doorbackend.auth.util.PasswordEncryptor
import rs.edu.raf.door2doorbackend.delivery.model.Delivery
import rs.edu.raf.door2doorbackend.delivery.model.enums.DeliveryStatus
import rs.edu.raf.door2doorbackend.delivery.repository.DeliveryRepository
import rs.edu.raf.door2doorbackend.delivery.util.TrackingCodeGenerator
import rs.edu.raf.door2doorbackend.role.model.Role
import rs.edu.raf.door2doorbackend.role.model.enums.RoleName
import rs.edu.raf.door2doorbackend.role.repository.RoleRepository
import rs.edu.raf.door2doorbackend.user.model.User
import rs.edu.raf.door2doorbackend.user.repository.UserRepository
import java.math.BigDecimal

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
                name = RoleName.ROLE_CUSTOMER
            )

            val roleEmployee = Role(
                name = RoleName.ROLE_EMPLOYEE
            )

            val roleDelivery = Role(
                name = RoleName.ROLE_DELIVERY
            )

            roleRepository.saveAll(listOf(roleAdmin, roleEmployee, roleNormalUser, roleDelivery))

            val adminUser = User(
                name = "Strahinja",
                surname = "Ljubicic",
                email = "strahinja.ljubicic@gmail.com",
                mobileNumber = "123456789",
                address = "Neka adresa"
            )

            val normalUser = User(
                name = "Strahinja",
                surname = "Ljubicic",
                email = "strahinja.ljubicic@gmail.com",
                mobileNumber = "123456789",
                address = "Banatska 23"
            )

            val deliveryUser = User(
                name = "Nikola",
                surname = "Sekulic",
                email = "strandza007@gmail.com",
                mobileNumber = "123456789",
                address = "Trg Republike 1"
            )

            userRepository.saveAll(listOf(adminUser, normalUser, deliveryUser))

            val adminAccount = Account(
                username = "admin",
                password = passwordEncryptor.passwordEncoder().encode("admin"),
                role = roleAdmin,
                user = adminUser
            )

            val normalAccount = Account(
                username = "customer",
                password = passwordEncryptor.passwordEncoder().encode("customer"),
                role = roleNormalUser,
                user = normalUser
            )

            val deliveryAccount = DeliveryAccount()
            deliveryAccount.username = "delivery"
            deliveryAccount.password = passwordEncryptor.passwordEncoder().encode("delivery")
            deliveryAccount.role = roleDelivery
            deliveryAccount.user = deliveryUser
            deliveryAccount.numberOfDeliveries = 10
            deliveryAccount.numberOfCanceledDeliveries = 0
            deliveryAccount.earnings = BigDecimal.valueOf(100)
            deliveryAccount.rating = 5.0

            accountRepository.saveAll(listOf(adminAccount, normalAccount, deliveryAccount))

            val delivery = Delivery(
                timeStarted = System.currentTimeMillis(),
                timeDelivered = System.currentTimeMillis(),
                trackingCode = TrackingCodeGenerator.generateTrackingCode(),
                price = BigDecimal.valueOf(100),
                status = DeliveryStatus.ACCEPTED,
                pickupLocation = "Bratstva i jedinstva 30",
                deliveryLocation = "Banatska 23 Borca",
                sender = adminAccount,
                receiver = normalAccount,
                driver = deliveryAccount
            )

            val finishedDelivery1 = Delivery(
                timeStarted = System.currentTimeMillis(),
                timeDelivered = System.currentTimeMillis(),
                trackingCode = TrackingCodeGenerator.generateTrackingCode(),
                price = BigDecimal.valueOf(100),
                status = DeliveryStatus.DELIVERED,
                pickupLocation = "Bratstva i jedinstva 30",
                deliveryLocation = "Banatska 23",
                sender = adminAccount,
                receiver = normalAccount,
                driver = deliveryAccount
            )

            val finishedDelivery2 = Delivery(
                timeStarted = System.currentTimeMillis(),
                timeDelivered = System.currentTimeMillis(),
                trackingCode = TrackingCodeGenerator.generateTrackingCode(),
                price = BigDecimal.valueOf(100),
                status = DeliveryStatus.DELIVERED,
                pickupLocation = "Bratstva i jedinstva 30",
                deliveryLocation = "Banatska 23",
                sender = adminAccount,
                receiver = normalAccount,
                driver = deliveryAccount
            )

            val finishedDelivery3 = Delivery(
                timeStarted = System.currentTimeMillis(),
                timeDelivered = System.currentTimeMillis(),
                trackingCode = TrackingCodeGenerator.generateTrackingCode(),
                price = BigDecimal.valueOf(100),
                status = DeliveryStatus.DELIVERED,
                pickupLocation = "Bratstva i jedinstva 30",
                deliveryLocation = "Banatska 23",
                sender = adminAccount,
                receiver = normalAccount,
                driver = deliveryAccount
            )

            deliveryRepository.saveAll(listOf(delivery, finishedDelivery1, finishedDelivery2, finishedDelivery3))
        }
    }
}