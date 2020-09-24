package br.com.digitalhouse.exerciciointegrador

import br.com.digitalhouse.exerciciointegrador.exceptions.NaoEncontradoException

class PDV() {
    companion object {
        val vendas: MutableList<Venda> = mutableListOf()

        open fun vender(v: Venda) {
            v.itens.forEach { item ->
                val itemEst = Estoque.consultar(item.artefato.codigo)
                if (item.qnt > itemEst.qtd)
                    throw SemEstoqueException("Produto indisponível")
            }
            v.itens.forEach { item ->
                Estoque.remover(item.artefato.codigo)
            }

            vendas.add(v)
        }
    }
}