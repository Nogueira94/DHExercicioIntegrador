package br.com.digitalhouse.exerciciointegrador

import br.com.digitalhouse.exerciciointegrador.exceptions.SemEstoqueException
import br.com.digitalhouse.exerciciointegrador.exceptions.ProdutoNaoEncontradoException

class Estoque {

    companion object {
        val itens = mutableListOf<ArtefatoItem>()

        fun consultarEstoque(codigo: Int) : Int {
            itens.forEach {
                if (it.artefato.codigo == codigo) {
                    when (it.artefato) {
                        is Livro -> return it.qnt!!
                        is Colecao -> {
                            var minEstoque : Int? = null
                            for (livro in it.artefato.livros) {
                                val estoqueLivro = consultarEstoque(livro.codigo)
                                minEstoque = minEstoque ?: estoqueLivro
                                if (estoqueLivro < minEstoque!!) {
                                    minEstoque = estoqueLivro
                                }
                            }
                            return minEstoque ?: 0
                        }
                    }
                }
            }
            throw ProdutoNaoEncontradoException()
        }

        fun consultar(codigo: Int) : ArtefatoItem {
            itens.forEach { if (it.artefato.codigo == codigo) return it }
            throw ProdutoNaoEncontradoException()
        }

        fun adicionar(item: ArtefatoItem) {
            itens.forEach {
                if (it.artefato == item.artefato) {
                    it.qnt = it.qnt?.plus(item.qnt!!)
                    return
                }
            }
            itens.add(item)
        }

        fun remover(item: ArtefatoItem) {
            if (item.qnt!! > consultarEstoque(item.artefato.codigo))
                throw SemEstoqueException()

            when (item.artefato) {
                is Livro -> removerLivro(item)
                is Colecao -> item.artefato.livros.forEach{livro -> removerLivro(ArtefatoItem(livro, item.qnt)) }
            }
        }

        private fun removerLivro(item: ArtefatoItem) {
            itens.forEach {
                if (it.artefato == item.artefato) {
                    if (it.qnt!! >= item.qnt!!) {
                        it.qnt = it.qnt?.minus(item.qnt!!)
                        return
                    } else
                        throw SemEstoqueException()
                }
            }
            throw ProdutoNaoEncontradoException()
        }

    }

}