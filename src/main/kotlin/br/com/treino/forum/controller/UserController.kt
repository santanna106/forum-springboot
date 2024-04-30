package br.com.treino.forum.controller

import br.com.treino.forum.dto.*
import br.com.treino.forum.service.UsuarioService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/usuarios")
class UserController(private val service: UsuarioService){
    @GetMapping
    @Cacheable("usuarios")
    fun listar(
        @RequestParam(required = false) nomeUsuario:String?,
        @PageableDefault(size = 20,sort = ["nome"], direction = Sort.Direction.DESC) paginacao: Pageable
    ) : Page<UsuarioView> {
        return service.listar(nomeUsuario,paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id:Long) : UsuarioView {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value=["usuarios"], allEntries = true)
    fun cadastrar(@RequestBody @Valid dto: NovoUsuarioForm,
                  uriBuilder: UriComponentsBuilder
    ) : ResponseEntity<UsuarioView> {
        val usuarioview = service.cadastrar(dto)
        val uri = uriBuilder
            .path("/usuarios/${usuarioview.id}").build().toUri()

        return ResponseEntity
            .created(uri)
            .body(usuarioview)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value=["usuarios"], allEntries = true)
    fun atualizar(@RequestBody @Valid dto: AtualizaUsuarioForm) : ResponseEntity<UsuarioView> {
        val usuarioview = service.atualizar(dto)

        return ResponseEntity.ok(usuarioview)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value=["usuarios"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id:Long) {
        service.deletar(id)
    }


}