package br.com.apirestkotlin.response
import java.util.ArrayList

data class Response<T> (
        val erros: ArrayList<String> =  arrayListOf(),
        var data: T? =  null
){}