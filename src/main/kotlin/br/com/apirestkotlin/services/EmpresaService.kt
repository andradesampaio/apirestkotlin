package br.com.apirestkotlin.services

import br.com.apirestkotlin.documents.Empresa
import br.com.apirestkotlin.repository.EmpresaRepository
import org.springframework.stereotype.Service

@Service
class EmpresaService constructor(val empresaRepository: EmpresaRepository) {

    fun findByCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)
    fun addEmpresa(empresa: Empresa): Empresa? = empresaRepository.save(empresa)
}