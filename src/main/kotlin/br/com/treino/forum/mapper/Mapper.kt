package br.com.treino.forum.mapper

interface Mapper<T, U> {
    fun map(t:T) : U
}
