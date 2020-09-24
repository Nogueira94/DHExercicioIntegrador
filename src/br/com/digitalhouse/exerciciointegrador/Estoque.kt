package br.com.digitalhouse.exerciciointegrador

import br.com.digitalhouse.exerciciointegrador.exceptions.SemEstoqueException
import br.com.digitalhouse.exerciciointegrador.exceptions.ProdutoNaoEncontradoException

class Estoque {

    companion object {
        val itens = mutableListOf<ArtefatoItem>()

        fun consultar(codigo: Int) : ArtefatoItem {
            itens.forEach { if (it.artefato.codigo == codigo) return it }
            throw ProdutoNaoEncontradoException()
        }

        fun adicionar(item: ArtefatoItem) {
            itens.forEach {
                if (it.artefato == item.artefato) {
                    it.qnt += item.qnt
                    return
                }
            }
            itens.add(item)
        }

        fun remover(item: ArtefatoItem) {
            itens.forEach {
                if (it.artefato == item.artefato) {
                    if (it.qnt >= item.qnt) {
                        it.qnt -= item.qnt
                        return
                    } else
                        throw SemEstoqueException()
                }
            }
            throw ProdutoNaoEncontradoException()
        }

    }

}