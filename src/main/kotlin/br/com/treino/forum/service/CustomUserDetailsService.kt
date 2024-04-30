package br.com.treino.forum.service

import br.com.treino.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = br.com.treino.forum.model.Usuario

@Service
class CustomUserDetailsService (
    private val userRepository: UsuarioRepository
) : UserDetailsService {

    override fun loadUserByUsername (username:String) : UserDetails =
        userRepository.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw  UsernameNotFoundException("Not found!")


    private fun ApplicationUser.mapToUserDetails()
        : UserDetails =
            User.builder()
                .username(this.email)
                .password(this.password)
                .build()

}