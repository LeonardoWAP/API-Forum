package com.ForumApi.security

import com.ForumApi.repository.CustomerRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
     authenticationManager: AuthenticationManager,
     private val customerRepository: CustomerRepository,
     private val jwtUtil : JwtUtil
) : UsernamePasswordAuthenticationFilter(authenticationManager){

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
        val authToken = UsernamePasswordAuthenticationToken(loginRequest.email,loginRequest.password)
        return authenticationManager.authenticate(authToken)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        super.unsuccessfulAuthentication(request, response, failed)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val authenticatedId = (authResult.principal as UserCustomDetails).customerModel.id!!
        val jwtToken = jwtUtil.generateToken(authenticatedId)
        response.addHeader("Authorization",jwtToken)
    }
}