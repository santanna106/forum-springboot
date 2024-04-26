package br.com.treino.forum.repository

import br.com.treino.forum.dto.TopicoPorCategoriaDto
import br.com.treino.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico,Long> {
    fun findByCursoNome(nomeCurso:String,
                        paginacao:Pageable):Page<Topico>

    @Query("SELECT new br.com.treino.forum.dto.TopicoPorCategoriaDto(curso.categoria,count(t)) from Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun relatorio():List<TopicoPorCategoriaDto>
}