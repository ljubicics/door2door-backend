package rs.edu.raf.door2doorbackend.auth.mapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import rs.edu.raf.door2doorbackend.account.model.Account
import rs.edu.raf.door2doorbackend.auth.dto.register.RegisterEmployeeRequest
import rs.edu.raf.door2doorbackend.auth.dto.register.RegisterRequest
import rs.edu.raf.door2doorbackend.auth.util.PasswordEncryptor
import rs.edu.raf.door2doorbackend.user.model.User

@Component
class AuthMapper @Autowired constructor(
    val passwordEncryptor: PasswordEncryptor
) {

    fun mapRegisterEmployeeRequestToUser(registerEmployeeRequest: RegisterEmployeeRequest): User {
        return User(
            name = registerEmployeeRequest.name,
            surname = registerEmployeeRequest.surname,
            email = registerEmployeeRequest.email,
            mobileNumber = registerEmployeeRequest.mobileNumber,
            address = registerEmployeeRequest.address
        )
    }

    fun mapRegisterEmployeeRequestToAccount(registerEmployeeRequest: RegisterEmployeeRequest): Account {
        return Account(
            username = registerEmployeeRequest.username,
            password =  passwordEncryptor.passwordEncoder().encode(registerEmployeeRequest.password),
            role = registerEmployeeRequest.role
        )
    }

    fun mapRegisterRequestToAccount(registerRequest: RegisterRequest): Account {
        return Account(
            username = registerRequest.username,
            password = passwordEncryptor.passwordEncoder().encode(registerRequest.password),
        )
    }

    fun mapRegisterRequestToUser(registerRequest: RegisterRequest): User {
        return User(
            name = registerRequest.name,
            surname = registerRequest.surname,
            email = registerRequest.email,
            mobileNumber = registerRequest.mobileNumber,
            address = registerRequest.address
        )

    }
}