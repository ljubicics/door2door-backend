package rs.edu.raf.door2doorbackend.account.mapper

import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy
import rs.edu.raf.door2doorbackend.account.dto.AccountDto
import rs.edu.raf.door2doorbackend.account.model.Account

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AccountMapper {
    fun accountToAccountDto(account: Account): AccountDto
    fun accountDtoToAccount(accountDto: AccountDto): Account
}