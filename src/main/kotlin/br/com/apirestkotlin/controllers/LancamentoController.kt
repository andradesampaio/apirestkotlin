package br.com.apirestkotlin.controllers

import br.com.apirestkotlin.documents.Funcionario
import br.com.apirestkotlin.documents.Lancamento
import br.com.apirestkotlin.dtos.LancamentoDto
import br.com.apirestkotlin.enum.TipoEnum
import br.com.apirestkotlin.response.Response
import br.com.apirestkotlin.services.FuncionarioService
import br.com.apirestkotlin.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.*
import org.springframework.data.domain.Sort.Direction.*
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController constructor(val lancamentoService: LancamentoService, val funcionarioService: FuncionarioService) {

private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Value("\${paginacao.qtd_por_pagina}")
     var qtdPorPagina: Int = 15

    @PostMapping
    fun add(@Valid @RequestBody lancamentoDto: LancamentoDto, result: BindingResult) : ResponseEntity<Response<LancamentoDto>>{
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        validarFuncionario(lancamentoDto, result)
        if (result.hasErrors()){
            for (erro in result.allErrors)
                response.erros.add(erro.defaultMessage!!)
            return ResponseEntity.badRequest().body(response)
        }

        val lancamento: Lancamento = convertDtoParaLancamento(lancamentoDto, result)
        var lancamentoSave = lancamentoService.addLancamento(lancamento)
        response.data = convertLancamentoDto(lancamentoSave)
        return ResponseEntity.ok(response)

    }

    @GetMapping(value = ["/{id}"])
    fun findByIdLacamento(@PathVariable("id") id: String): ResponseEntity<Response<LancamentoDto>>{
       val response: Response<LancamentoDto> = Response<LancamentoDto>()
        val lancamento: Optional<Lancamento> = lancamentoService.findByLancamentoId(id)
        if (lancamento == null){
            response.erros.add("Lacamento nao encontrado para id: $id")
            return ResponseEntity.badRequest().body(response)
        }
        response.data = convertLancamentoDto(lancamento.get())
        return ResponseEntity.ok(response)

    }

    @GetMapping(value = ["/funcionario/{funcionarioId}"])
    fun listByFuncionarioId(@PathVariable("funcionarioId") funcionarioId: String,
                            @RequestParam(value = "pag", defaultValue = "0") pag: Int,
                            @RequestParam(value = "ord", defaultValue = "id") ord: String): ResponseEntity<Response<Page<LancamentoDto>>> {
        val response: Response<Page<LancamentoDto>> = Response<Page<LancamentoDto>>()
        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.DESC, ord)

        val lancamentos: Page<Lancamento>? = lancamentoService.findByFuncionanrioId(funcionarioId, pageRequest)

        val lancamentosDto: Page<LancamentoDto> = lancamentos?.map { lancamento -> convertLancamentoDto(lancamento) } as Page<LancamentoDto>
        response.data = lancamentosDto
        return ResponseEntity.ok(response)

    }

    @PutMapping(value = ["/{id}"])
    fun update(@PathVariable("id") id: String, @Valid @RequestBody lancamentoDto: LancamentoDto,
                result: BindingResult): ResponseEntity<Response<LancamentoDto>>{
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        validarFuncionario(lancamentoDto, result)
        lancamentoDto.id = id
        val lancamento: Lancamento =  convertDtoParaLancamento(lancamentoDto, result)
        if (result.hasErrors()){
            for (erro in result.allErrors)
                response.erros.add(erro.defaultMessage!!)
            return ResponseEntity.badRequest().body(response)
        }

        var lancamentoSave = lancamentoService.addLancamento(lancamento)
        response.data = convertLancamentoDto(lancamentoSave)
        return ResponseEntity.ok(response)

    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable("id") id: String): ResponseEntity<Response<String>>{
        val response: Response<String> = Response<String>()
        val lancamento: Optional<Lancamento> = lancamentoService.findByLancamentoId(id)
        if (lancamento == null){
            response.erros.add("Error remover lacamento. Registro nao encontrado para id: $id")
            return ResponseEntity.badRequest().body(response)
        }
        lancamentoService.remover(id)
        return ResponseEntity.ok(Response<String>())

    }

    private fun convertDtoParaLancamento(lancamentoDto: LancamentoDto, result: BindingResult): Lancamento {

        if (lancamentoDto.id != null) {
            var lanc: Optional<Lancamento> = lancamentoService.findByLancamentoId(lancamentoDto.id!!)
            if (lanc == null) result.addError(ObjectError("lancamento", "Lacamento nao encontrado"))
        }
        return Lancamento(dateFormat.parse(lancamentoDto.data),
        TipoEnum.valueOf(lancamentoDto.tipo!!),
        lancamentoDto.funcionarioId!!,
        lancamentoDto.descricao,
        lancamentoDto.localizacao,
        lancamentoDto.id)
    }

    private fun convertLancamentoDto(lancamento: Lancamento): LancamentoDto? =
            LancamentoDto(dateFormat.format(lancamento.data),
            lancamento.tipo.toString(),
            lancamento.descricao,
            lancamento.localizacao,
            lancamento.funcionarioId,
            lancamento.id)

    private fun validarFuncionario(lancamentoDto: LancamentoDto, result: BindingResult) {
        if (lancamentoDto.funcionarioId == null){
            result.addError(ObjectError("funcionario",
                    "Funcionario nao informado."))
            return
        }
        val funcionario: Optional<Funcionario> = funcionarioService.findById(lancamentoDto.funcionarioId)
        if (funcionario == null){
            result.addError(ObjectError("funcionario",
                    "Funcionario nao encontrado. ID inexistente"))
            return
        }

    }


}