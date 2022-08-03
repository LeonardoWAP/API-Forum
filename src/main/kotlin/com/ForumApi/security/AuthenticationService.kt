package com.ForumApi.security

import com.ForumApi.model.CustomerModel
import com.ForumApi.repository.CustomerRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

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
class UserCustomDetails(val customerModel: CustomerModel): UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return customerModel.password
    }

    override fun getUsername(): String {
        return customerModel.email
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
       return false
    }

    override fun isEnabled(): Boolean {
        return true
    }
}