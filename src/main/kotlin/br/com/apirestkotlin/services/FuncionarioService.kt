package br.com.apirestkotlin.services

import br.com.apirestkotlin.documents.Funcionario
import br.com.apirestkotlin.repository.FuncionarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FuncionarioService constructor(val funcionarioRepository: FuncionarioRepository) {

    fun findByCpf(cpf: String): Funcionario? = funcionarioRepository.findByCpf(cpf)
    fun findByEmail(email: String): Funcionario? = funcionarioRepository.findByEmail(email)
    fun findById(id: String): Optional<Funcionario> = funcionarioRepository.findById(id)
    fun addFuncionario(funcionario: Funcionario): Funcionario = funcionarioRepository.save(funcionario)
}