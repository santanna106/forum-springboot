package br.com.treino.forum.mapper

import br.com.treino.forum.dto.NovoUsuarioForm
import br.com.treino.forum.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioFormMapper : Mapper<NovoUsuarioForm, Usuario> {
    override fun map(u: NovoUsuarioForm): Usuario {
        return Usuario(
            nome = u.nome,
            email = u.email,
            password = u.password
        )
    }
}
