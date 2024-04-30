package br.com.treino.forum.mapper


import br.com.treino.forum.dto.UsuarioView
import br.com.treino.forum.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioViewMapper : Mapper<Usuario, UsuarioView> {
    override fun map(t: Usuario): UsuarioView {
        return UsuarioView(
            id = t.id,
            nome = t.nome,
            email = t.email,
            password = t.password
        )
    }
}
