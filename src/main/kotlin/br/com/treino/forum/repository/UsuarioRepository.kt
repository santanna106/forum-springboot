package br.com.treino.forum.repository

import br.com.treino.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository:JpaRepository<Usuario,Long> {
}