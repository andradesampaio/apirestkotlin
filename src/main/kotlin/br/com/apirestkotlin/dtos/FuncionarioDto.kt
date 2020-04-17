package br.com.apirestkotlin.dtos

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

data class FuncionarioDto(

        @get:NotEmpty(message = "Nome não pode ser vazio")
        @get:Length(min = 3, max = 200, message = "Nome deve conter 3 e 200 caracteres.")
        val nome: String? = null,

        @get:NotEmpty(message = "Email não pode ser vazio")
        @get:Length(min = 5, max = 200, message = "Email deve conter 3 e 200 caracteres.")
        @get:Email(message = "Email inválido")
        val email: String? = null,

        val senha: String? = null,
        val valorHora: String? = null,
        val qtdHorasTrabalhoDia: String? = null,
        val qtdHorasAlmoco: String? = null,
        val id: String? = null) {
}