package br.com.digitalhouse.exerciciointegrador

class Livro(codigo: Int, preco: Double, val titulo: String?, val autor: String?, val anoLanc: Int) : Artefato(codigo, preco) {
    override fun toString(): String {
        return "Livro(codigo=$codigo, titulo='$titulo')"
    }
}