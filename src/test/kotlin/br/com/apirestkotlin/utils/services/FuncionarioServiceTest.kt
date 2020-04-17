package br.com.apirestkotlin.utils.services

import br.com.apirestkotlin.documents.Funcionario
import br.com.apirestkotlin.enum.PerfilEnum
import br.com.apirestkotlin.repository.FuncionarioRepository
import br.com.apirestkotlin.services.FuncionarioService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
class FuncionarioServiceTest {

    @Mock
    lateinit var funcionarioRepository: FuncionarioRepository

    @InjectMocks
    lateinit var funcionarioService: FuncionarioService

    private val EMAIL: String =  "email@email.com"
    private val CPF: String = "32457845878"
    private val ID: String = "1"

    @Before
    fun setup(){
        funcionarioService = FuncionarioService(funcionarioRepository)
        Mockito.`when`(funcionarioRepository.save(Mockito.any(Funcionario::class.java))).thenReturn(funcionario())
        Mockito.`when`(funcionarioRepository.findById(ID)).thenReturn(Optional.of(funcionario()))
        Mockito.`when`(funcionarioRepository.findByEmail(EMAIL)).thenReturn(funcionario())
        Mockito.`when`(funcionarioRepository.findByCpf(CPF)).thenReturn(funcionario())
    }

    @Test
    fun `should_save_funcionario`() {
        val colaborador: Funcionario? = funcionarioService?.addFuncionario(funcionario())
        assertNotNull(colaborador)
    }

    @Test
    fun `should_find_by_id_funcionario`() {
        val colaborador: Optional<Funcionario> = funcionarioService?.findById(ID)
        assertNotNull(colaborador)
    }

    @Test
    fun `should_find_by_email_funcionario`() {
        val colaborador: Funcionario? = funcionarioService?.findByEmail(EMAIL)
        assertNotNull(colaborador)
    }

    @Test
    fun `should_find_by_cpf_funcionario`() {
        val colaborador: Funcionario? = funcionarioService?.findByCpf(CPF)
        assertNotNull(colaborador)
    }


   private fun funcionario(): Funcionario = Funcionario(
            "Raimundo Silva",
            "raimundo@gmail.com",
            "123456",
            "31522488596",
            PerfilEnum.ROLE_ADMIN,
            "01",
            10.00,
            8f,
            2f,
            "1")

}