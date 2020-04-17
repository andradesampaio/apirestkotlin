package br.com.apirestkotlin.services

import br.com.apirestkotlin.documents.Lancamento
import br.com.apirestkotlin.repository.LancamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*


@Service
class LancamentoService constructor(val lancamentoRepository: LancamentoRepository) {

    fun findByFuncionanrioId(id: String, pageable: Pageable): Page<Lancamento>? = lancamentoRepository?.findByFuncionarioId(id, pageable)
    fun addLancamento(lancamento: Lancamento): Lancamento = lancamentoRepository.save(lancamento)
    fun findByLancamentoId(id: String) : Optional<Lancamento> = lancamentoRepository.findById(id)
    fun remover(id: String) = lancamentoRepository.deleteById(id)
}