package br.com.digitalhouse.exerciciointegrador

class Colecao (codigo: Int, preco: Double,val descricao: String, val livros: MutableList<Livro>) : Artefato(codigo, preco) {
    override fun toString(): String {
        return "Colecao(codigo=$codigo, descricao='$descricao')"
    }

}