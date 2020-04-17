package br.com.apirestkotlin.dtos

import org.hibernate.validator.constraints.NotEmpty

data class LancamentoDto(
        @get:NotEmpty(message = "Data não pode ser vazio")
        val data: String? = null,

        @get:NotEmpty(message = "Tipo não pode ser vazio")
        val tipo: String? = null,

        val descricao: String? = null,
        val localizacao: String? = null,
        val funcionarioId: String? = null,
        val id: String? = null
) {
}