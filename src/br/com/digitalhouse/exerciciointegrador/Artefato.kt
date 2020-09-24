package br.com.digitalhouse.exerciciointegrador

abstract class Artefato (val codigo: Int, val preco: Double ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Livro) return false
        if (codigo != other.codigo) return false
        return true
    }
}