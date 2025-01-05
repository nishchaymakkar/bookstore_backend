package com.nishchay.bookstore.config

import CustomUserDetails
import com.nishchay.bookstore.util.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenUtil: JwtTokenUtil
) : OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader : String = request.getHeader("Authorization")
        if (authHeader!= null && authHeader.startsWith("Bearer ")){
            val token: String = authHeader.substring(7)

            if (jwtTokenUtil.isTokenValid(token)){
                val userId = jwtTokenUtil.getClaimsFromToken(token).subject.toLong()

                val userDetails = CustomUserDetails(userId)

                val authentication : UsernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails,null,null)

                SecurityContextHolder.getContext().authentication

            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token")
                return
            }
        }
    }

}