import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CustomUserDetails(private val userId: Long) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList() // No roles or authorities

    override fun getPassword(): String? = null // Not used in this context

    override fun getUsername(): String = userId.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
