package br.com.treino.forum.repository

import br.com.treino.forum.model.Topico
import br.com.treino.forum.model.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository:JpaRepository<Usuario,Long> {
    fun findByNome(nomeUsuario:String,
                        paginacao:Pageable):Page<Usuario>
    fun findByEmail(email:String
    ): Usuario
}