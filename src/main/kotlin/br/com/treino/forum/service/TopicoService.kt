package br.com.treino.forum.service

import br.com.treino.forum.dto.AtualizaTopicoForm
import br.com.treino.forum.dto.NovoTopicoForm
import br.com.treino.forum.dto.TopicoView
import br.com.treino.forum.exception.NotFoundException
import br.com.treino.forum.mapper.TopicoFormMapper
import br.com.treino.forum.mapper.TopicoViewMapper
import br.com.treino.forum.model.Curso
import br.com.treino.forum.model.Topico
import br.com.treino.forum.model.Usuario
import br.com.treino.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository:TopicoRepository,

    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado"

    ) {

    fun listar(
        nomeCurso:String?,
        paginacao: Pageable
        ): Page<TopicoView> {
        val topicos = if(nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso,paginacao)
        }
        return  topicos.map {
            t -> topicoViewMapper.map(t)
        }
    }

    fun buscarPorId(id:Long) : TopicoView{
        val topico = repository.findById(id)
             .orElseThrow(){NotFoundException(notFoundMessage)}

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm) : TopicoView{
        val topico = topicoFormMapper.map(dto)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(dto: AtualizaTopicoForm): TopicoView {
        val topico = repository.findById(dto.id)
            .orElseThrow(){NotFoundException(notFoundMessage)}

        val topicoAtualizado = topico.copy(
            titulo = dto.titulo,
            mensagem = dto.mensagem
            )

        repository.save(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id:Long) {
        repository.deleteById(id)
    }

}