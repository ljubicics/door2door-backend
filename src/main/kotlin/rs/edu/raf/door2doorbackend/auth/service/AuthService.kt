package rs.edu.raf.door2doorbackend.auth.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rs.edu.raf.door2doorbackend.account.service.AccountService
import rs.edu.raf.door2doorbackend.auth.dto.register.RegisterEmployeeRequest
import rs.edu.raf.door2doorbackend.auth.dto.register.RegisterRequest
import rs.edu.raf.door2doorbackend.auth.mapper.AuthMapper
import rs.edu.raf.door2doorbackend.role.model.enums.RoleName
import rs.edu.raf.door2doorbackend.role.repository.RoleRepository
import rs.edu.raf.door2doorbackend.user.service.UserService
import java.util.regex.Pattern

@Service
class AuthService @Autowired constructor(
    val authMapper: AuthMapper,
    val accountService: AccountService,
    val userService: UserService,
    val roleRepository: RoleRepository
) {

    private val emailPattern: Pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")

    fun registerNormalUser(registerRequest: RegisterRequest): Boolean {
        if (emailPattern.matcher(registerRequest.email).matches().not()) {
            throw Exception("Invalid email")
        }

        val user = authMapper.mapRegisterRequestToUser(registerRequest)
        val account = authMapper.mapRegisterRequestToAccount(registerRequest)
        account.user = user
        account.role = roleRepository.findRoleByName(RoleName.ROLE_NORMAL_USER)

        userService.saveUser(user)
        accountService.saveAccount(account)
        return true
    }

    fun registerEmployee(registerRequest: RegisterEmployeeRequest): Boolean {
        if (emailPattern.matcher(registerRequest.email).matches().not()) {
            throw Exception("Invalid email")
        }

        val user = authMapper.mapRegisterEmployeeRequestToUser(registerRequest)
        val account = authMapper.mapRegisterEmployeeRequestToAccount(registerRequest)
        account.user = user

        userService.saveUser(user)
        accountService.saveAccount(account)
        return true
    }
}