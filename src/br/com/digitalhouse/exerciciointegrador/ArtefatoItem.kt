package br.com.digitalhouse.exerciciointegrador

class ArtefatoItem(val artefato: Artefato,
                   val qnt: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ArtefatoItem) return false

        if (artefato.codigo != other.artefato.codigo && qnt != other.qnt) return false

        return true
    }
}