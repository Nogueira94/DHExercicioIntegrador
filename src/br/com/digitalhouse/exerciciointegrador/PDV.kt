package br.com.digitalhouse.exerciciointegrador

import br.com.digitalhouse.exerciciointegrador.exceptions.SemEstoqueException


class PDV() {
    companion object {
        val vendas: MutableList<Venda> = mutableListOf()

        open fun vender(v: Venda) {
            v.itens.forEach { item ->
                val itemEst = Estoque.consultar(item.artefato.codigo)
                if (item.qnt > itemEst.qnt)
                    throw SemEstoqueException()
            }
            v.itens.forEach { item ->
                Estoque.remover(item)
            }

            vendas.add(v)
        }
    }
}