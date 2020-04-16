package br.com.apirestkotlin.utils.services

import br.com.apirestkotlin.documents.Empresa
import br.com.apirestkotlin.repository.EmpresaRepository
import br.com.apirestkotlin.services.EmpresaService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EmpresaServiceTest {

    @Mock
    lateinit var empresaRepository: EmpresaRepository

    @InjectMocks
    lateinit var empresaService: EmpresaService

    private val CNPJ = "34545870001584"

    @Before
    fun setup(){
        empresaService = EmpresaService(empresaRepository)
        Mockito.`when`(empresaRepository.findByCnpj(CNPJ)).thenReturn(empresa())
        Mockito.`when`(empresaRepository.save(empresa())).thenReturn(empresa())
    }


    @Test
    fun `should_find_empresa_by_cnpj`(){
        val empresa: Empresa? = empresaService?.findByCnpj(CNPJ)
        assertNotNull(empresa)
    }

    @Test
    fun `should_save_empresa`(){
        val empresa: Empresa? = empresaService?.addEmpresa(empresa())
        assertNotNull(empresa)
    }

    private fun empresa(): Empresa = Empresa("TI SOLUTION", CNPJ)



}