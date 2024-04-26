package br.com.treino.forum.service

import br.com.treino.forum.model.Curso
import br.com.treino.forum.model.Usuario
import br.com.treino.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutorService(private val repository:UsuarioRepository) {

    fun buscarPorId(id:Long): Usuario {
        return repository.getReferenceById(id)
    }

}
