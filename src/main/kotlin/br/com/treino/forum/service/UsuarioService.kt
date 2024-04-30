package br.com.treino.forum.service

import br.com.treino.forum.dto.*
import br.com.treino.forum.exception.NotFoundException
import br.com.treino.forum.mapper.UsuarioFormMapper
import br.com.treino.forum.mapper.UsuarioViewMapper
import br.com.treino.forum.repository.UsuarioRepository
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UsuarioService(
    private val repository:UsuarioRepository,

    private val usuarioViewMapper: UsuarioViewMapper,
    private val usuarioFormMapper: UsuarioFormMapper,
    private val notFoundMessage: String = "Usuário não encontrado",
    private val encoder: PasswordEncoder,
    private val em: EntityManager

    ) {

    fun listar(
        nomeUsuario:String?,
        paginacao: Pageable
        ): Page<UsuarioView> {
        val usuarios = if(nomeUsuario == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByNome(nomeUsuario,paginacao)
        }
        return  usuarios.map {
            t -> usuarioViewMapper.map(t)
        }
    }

    fun buscarPorId(id:Long) : UsuarioView{
        val usuario = repository.findById(id)
             .orElseThrow(){NotFoundException(notFoundMessage)}

        return usuarioViewMapper.map(usuario)
    }

    fun cadastrar(dto: NovoUsuarioForm) : UsuarioView{
        val usuario = usuarioFormMapper.map(dto)
        usuario.password = encoder.encode(usuario.password)
        repository.save(usuario)
        return usuarioViewMapper.map(usuario)
    }

    fun atualizar(dto: AtualizaUsuarioForm): UsuarioView {
        val usuario = repository.findById(dto.id)
            .orElseThrow(){NotFoundException(notFoundMessage)}

        val usuarioAtualizado = usuario.copy(
            nome = dto.nome,
            password = dto.password
            )

        repository.save(usuarioAtualizado)

        return usuarioViewMapper.map(usuarioAtualizado)
    }

    fun deletar(id:Long) {
        repository.deleteById(id)
    }



}