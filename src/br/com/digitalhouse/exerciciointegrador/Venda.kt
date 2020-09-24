package br.com.digitalhouse.exerciciointegrador

class Venda(val codVenda: Int,
            val itens: MutableList<ArtefatoItem> = mutableListOf() ){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Venda) return false

        if (codVenda != other.codVenda) return false

        return true
    }
}