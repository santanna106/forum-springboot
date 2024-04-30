package br.com.treino.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class NovoUsuarioForm (

    @field:NotEmpty(message = "Título não pode ser em branco")
    @field:Size(min=5,max=100, message = "Título deve ter entre 5 e 100 caracteres")
    val nome:String,
    @field:NotEmpty(message = "Mensagem não pode ser em branco")
    val email:String,
    @field:NotNull
    val password:String
)


