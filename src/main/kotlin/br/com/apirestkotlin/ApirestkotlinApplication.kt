package br.com.apirestkotlin

import br.com.apirestkotlin.documents.Empresa
import br.com.apirestkotlin.documents.Funcionario
import br.com.apirestkotlin.enum.PerfilEnum
import br.com.apirestkotlin.repository.EmpresaRepository
import br.com.apirestkotlin.repository.FuncionarioRepository
import br.com.apirestkotlin.repository.LancamentoRepository
import br.com.apirestkotlin.services.FuncionarioService
import br.com.apirestkotlin.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApirestkotlinApplication (val empresaRepository: EmpresaRepository, val funcionarioRepository: FuncionarioRepository,
								val lancamentoRepository: LancamentoRepository) : CommandLineRunner{

	override fun run(vararg args: String?) {
		empresaRepository.deleteAll()
		funcionarioRepository.deleteAll()
		lancamentoRepository.deleteAll()

		val empresa: Empresa = Empresa("Empresa", "104438870000146")
		val empresaSave = empresaRepository.save(empresa)

		val admin: Funcionario = Funcionario("Admin", "admin@empresa.com",
				SenhaUtils().gerarBcrypt("123456"), "25708317000", PerfilEnum.ROLE_ADMIN, empresaSave.id!!)
		val adminSave = funcionarioRepository.save(admin)

		val funcionario: Funcionario = Funcionario("Funcionario", "funcionario@gmail.com",
				SenhaUtils().gerarBcrypt("123456"), "25708317000", PerfilEnum.ROLE_USUARIO, empresaSave.id!!)
		val funcionarioSave = funcionarioRepository.save(funcionario)

		println("Empresa ID ${empresaSave.id}")
		println("Admin ID ${adminSave.id}")
		println("Funcionario ID ${funcionarioSave.id}")

	}


}

fun main(args: Array<String>) {
	runApplication<ApirestkotlinApplication>(*args)
}
