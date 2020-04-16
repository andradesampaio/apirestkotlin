package br.com.apirestkotlin.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Empresa (
        val razaoSocial: String,
        val cnpj: String,
        @Id val id: String? = null
)