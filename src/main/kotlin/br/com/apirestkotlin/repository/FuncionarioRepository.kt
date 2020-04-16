package br.com.apirestkotlin.repository

import br.com.apirestkotlin.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByEmail(email: String): Funcionario
    fun findByCpf(cpf: String): Funcionario
}