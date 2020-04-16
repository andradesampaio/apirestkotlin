package br.com.apirestkotlin.utils


import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilsTest {

    private val SENHA = "123456"
    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun `should_create_hash_password`(){
       val hash = SenhaUtils().gerarBcrypt(SENHA)
        assert(bCryptEncoder.matches(SENHA,hash))
    }
}