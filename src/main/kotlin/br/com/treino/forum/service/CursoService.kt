package br.com.treino.forum.service

import br.com.treino.forum.model.Curso
import br.com.treino.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val repository:CursoRepository) {


    fun buscarPorId(id:Long): Curso {
        return repository.getReferenceById(id)
    }

}
