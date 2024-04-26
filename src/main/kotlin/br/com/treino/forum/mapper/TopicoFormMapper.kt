package br.com.treino.forum.mapper

import br.com.treino.forum.dto.NovoTopicoForm
import br.com.treino.forum.dto.TopicoView
import br.com.treino.forum.model.Topico
import br.com.treino.forum.service.AutorService
import br.com.treino.forum.service.CursoService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper (
    private val cursoService: CursoService,
    private val autorService: AutorService,
): Mapper<NovoTopicoForm, Topico> {
    override fun map(t: NovoTopicoForm): Topico {
        return Topico(

            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = autorService.buscarPorId(t.idAutor),

        )
    }
}
