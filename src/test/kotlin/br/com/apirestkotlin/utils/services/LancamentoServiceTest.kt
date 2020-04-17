package br.com.apirestkotlin.utils.services

import br.com.apirestkotlin.documents.Lancamento
import br.com.apirestkotlin.enum.TipoEnum
import br.com.apirestkotlin.repository.LancamentoRepository
import br.com.apirestkotlin.services.LancamentoService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.junit4.SpringRunner
import java.util.*


@RunWith(SpringRunner::class)
class LancamentoServiceTest {

    @Mock
    lateinit var lancamentoRepository: LancamentoRepository

    @Mock
    lateinit var pageableMock: Pageable

    @InjectMocks
    lateinit var lancamentoService: LancamentoService

    private val ID: String = "1"

    @Before
    fun setup(){
        lancamentoService = LancamentoService(lancamentoRepository)
        val pageable: Pageable = PageRequest.of(0, 10)
        val tasks: Page<Lancamento> = Mockito.mock(Page::class.java) as Page<Lancamento>

        Mockito.`when`(lancamentoRepository.save(Mockito.any(Lancamento::class.java))).thenReturn(lancamento())
        Mockito.`when`(lancamentoRepository.findById(ID)).thenReturn(Optional.of(lancamento()))
        Mockito.`when`(lancamentoRepository.findByFuncionarioId(ID, pageable)).thenReturn(tasks)
    }



    @Test
    fun `should_save_lancamento`() {
        val lancamento: Lancamento? = lancamentoService?.addLancamento(lancamento())
        Assert.assertNotNull(lancamento)
    }

    @Test
    fun `should_find_by_id_lancamento`() {
        val lancamento: Optional<Lancamento>? = lancamentoService?.findByLancamentoId(ID)
        Assert.assertNotNull(lancamento)
    }

    @Test
    fun `should_find_by_id_funcionario`() {
        val pageable: Pageable = PageRequest.of(0, 10)
        val lancamento: Page<Lancamento>? = lancamentoService?.findByFuncionanrioId(ID, pageable)
        Assert.assertNotNull(lancamento)
    }

 private fun lancamento(): Lancamento = Lancamento(Date(), TipoEnum.INICIO_TRABALHO, ID)


}