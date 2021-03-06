package br.com.digitalhouse.exerciciointegrador

class ArtefatoItem(val artefato: Artefato,
                   var qnt: Int? = null) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ArtefatoItem) return false

        if (artefato.codigo != other.artefato.codigo && qnt != other.qnt) return false

        return true
    }

    override fun toString(): String {
        when (this.artefato) {
            is Livro -> return "${artefato.toString()}: Estoque: $qnt"
            is Colecao -> return "${artefato.toString()}"
        }
        return "${artefato.toString()}"
    }
}