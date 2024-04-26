package br.com.treino.forum.repository

import br.com.treino.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso,Long> {
}