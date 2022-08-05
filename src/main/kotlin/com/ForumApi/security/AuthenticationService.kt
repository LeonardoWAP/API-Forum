package com.ForumApi.security

import com.ForumApi.repository.CustomerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val customerRepository: CustomerRepository
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        val customer = customerRepository.findByEmail(username)
        if (customer == null){
            throw Exception()
        }
        return UserCustomDetails(customer)
    }

}
