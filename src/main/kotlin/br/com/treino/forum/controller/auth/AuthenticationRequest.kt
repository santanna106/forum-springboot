package br.com.treino.forum.controller.auth

data class AuthenticationRequest(
    val email:String,
    val password:String
)
