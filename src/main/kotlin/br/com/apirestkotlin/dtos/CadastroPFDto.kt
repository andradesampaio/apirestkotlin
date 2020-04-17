package br.com.apirestkotlin.dtos

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF

data class CadastroPFDto(
        @get:NotEmpty(message = "Nome não pode ser vazio")
        @get:Length(min = 3, max = 200, message = "Nome deve conter 3 e 200 caracteres.")
        val nome: String = "",

        @get:NotEmpty(message = "Email não pode ser vazio")
        @get:Length(min = 5, max = 200, message = "Email deve conter 3 e 200 caracteres.")
        @get:Email(message = "Email inválido")
        val email: String = "",

        @get:NotEmpty(message = "Senha não pode ser vazio")
        val senha: String = "",

        @get:NotEmpty(message = "Cpf não pode ser vazio")
        @get:CPF(message = "Cpf inválido")
        val cpf: String = "",

        @get:NotEmpty(message = "Cnpj não pode ser vazio")
        @get:CNPJ(message = "Cnpj inválido")
        val cnpj: String = "",

        val empresaId: String? = "",

        val valorHora: String? = null,
        val qtdHorasTrabalhoDia: String? = null,
        val qtdHorasAlmoco: String? = null,
        val id: String? = null

) {
}