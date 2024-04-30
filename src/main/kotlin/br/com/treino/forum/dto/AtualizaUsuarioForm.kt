package br.com.treino.forum.dto

import io.jsonwebtoken.security.Password
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class AtualizaUsuarioForm (
    @field:NotNull
    val id:Long,
    @field:NotEmpty
    @field:Size(min=5,max=100 )
    val nome:String,
    @field:NotEmpty
    val password: String
)


