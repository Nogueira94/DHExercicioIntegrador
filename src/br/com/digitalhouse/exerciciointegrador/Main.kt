package br.com.digitalhouse.exerciciointegrador

import br.com.digitalhouse.exerciciointegrador.exceptions.ProdutoNaoEncontradoException
import br.com.digitalhouse.exerciciointegrador.exceptions.SemEstoqueException
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import java.lang.NumberFormatException
import kotlin.random.Random

fun menu() {
    println("Bem vindos a livraria, selecione um opção: 1) Cadastrar Livro, 2) Cadastrar Coleção, 3) Consultar Livro, 4)Venda")

    when (readLine()){
        "1" -> menuCadastrarProduto()
        "2" -> menuCadastrarColecao()
        "3" -> menuConsultar()
        "4" -> menuVenda()
        else -> {
            println("Opcao inexistente")
            return menu()
        }
    }

}

fun menuCadastrarProduto() {

    var cod : Int? = null
    while (true) {
        try {
            println("Digite o codigo do livro")
            cod = parseInt(readLine())
            break
        } catch (e: NumberFormatException) {
            println("Codigo do livro precisa ser um numero inteiro")
        }
    }

    var preco : Double? = null
    while (true) {
        try {
            println("Digite o preco do livro")
            preco = parseDouble(readLine())
            break
        } catch (e: NumberFormatException) {
            println("preco precisa ser um numero decimal")
        }
    }

    println("Digite o titulo do livro")
    val titulo = readLine()

    println("Digite o autor do livro")
    val autor = readLine()

    var anoLanc: Int? = null
    while (true) {
        try {
            println("Digite o ano de lançamento do livro")
            anoLanc = parseInt(readLine())
            break
        } catch (e: NumberFormatException) {
            println("Ano de lancamento precisa ser um inteiro")
        }
    }

    val book = Livro(cod!!, preco!!, titulo, autor, anoLanc!!)

    var qnt: Int? = null
    while (true) {
        try {
            println("Digite a quantidade a ser adicionada")
            qnt = parseInt(readLine())
            break
        } catch (e: NumberFormatException) {
            println("Quantidade precisa ser um numero inteiro")
        }
    }

    Estoque.adicionar(ArtefatoItem(book, qnt))

    println("Livro cadastrado com sucesso $book")

    return menu()
}

fun menuCadastrarColecao() {

    var cod : Int? = null
    while (true) {
        try {
            println("Digite o codigo da colecao")
            cod = parseInt(readLine())
            break
        } catch (e: NumberFormatException) {
            println("codigo precisa ser um numero inteiro")
        }
    }

    var preco : Double? = null
    while (true) {
        try {
            println("Digite o preco da colecao")
            preco = parseDouble(readLine())
            break
        } catch (e: NumberFormatException) {
            println("Preco precisa ser um numero decimal")
        }
    }

    println("Digite o nome da colecao")
    val descricao = readLine() as String

    val livros = adicionarLivroALista(mutableListOf())

    val colecao = Colecao (cod!!, preco!!, descricao, livros)
    Estoque.adicionar(ArtefatoItem(colecao))

    println("Colecao cadastrada com sucesso $colecao")

    return menu()
}

fun adicionarLivroALista(lista: MutableList<Livro>) : MutableList<Livro> {
    if (lista.size > 0) {
        println("Deseja adicionar mais um livro a colecao? [S/N]")
        val resp = readLine()

        if (resp == "N") return lista
    }

    println("Digite o codigo do livro para adicionar a colecao")
    var codLivro : Int? = null
    while (true) {
        try {
            codLivro = parseInt(readLine())
            break
        } catch (e: NumberFormatException) {
            println("Codigo do livro precisa ser um numero inteiro")
        }
    }

    try {
        val livro = Estoque.consultar(codLivro!!).artefato
        lista.add(livro as Livro)
    } catch (e: ProdutoNaoEncontradoException) {
        println("Produto nao encontrado")
    }

    return adicionarLivroALista(lista)
}

fun menuConsultar() {
    println("Digite o codigo do produto que quer consultar")
    var cod : Int? = null
    while (true) {
        try {
            cod = parseInt(readLine())
            break
        } catch (e: NumberFormatException) {
            println("codigo do produto precisa ser um numero inteiro")
        }
    }

    try {
        println(Estoque.consultar(cod!!))
    } catch (e: ProdutoNaoEncontradoException) {
        println("Produto nao encontrado")
    } finally {
        menu()
    }
}

fun menuVenda(venda: Venda = Venda(Random.nextInt(999999))){
    println("Selecione a opção: 1)Adicionar item, 2)Finalizar venda, 3) Voltar ao menu inicial")
    when (readLine()) {
        "1" -> {
            println("Digite o codigo do item: ")
            var cod : Int? = null
            while (true) {
                try {
                    cod = parseInt(readLine())
                    break
                }  catch (e: NumberFormatException) {
                    println("Codigo do item precisa ser um numero inteiro")
                }
            }

            try {
                val artefato = Estoque.consultar(cod!!).artefato
                println("Insira a quantidade do produto")
                val qnt = parseInt(readLine())
                venda.itens.add(ArtefatoItem(artefato, qnt))
            } catch (e : ProdutoNaoEncontradoException){
                println("Produto não encontrado")
            } finally {
                return menuVenda(venda)
            }
        }
        "2" -> {
            try {
                PDV.vender(venda)
                println("Venda finalizada com sucesso")
            } catch (e : SemEstoqueException){
                println("Erro ao finalizar a venda. Produto sem estoque")
            }
            finally {
                return menu()
            }
        }
        "3" -> return menu()
    }
}

fun main() {

    val livro1 = Livro (1,10.50,"Harry Potter 1", "JK",1994)
    val livro2 = Livro (2,12.50,"Harry Potter 2", "JK",1996)
    val livro3 = Livro (3,14.80,"Harry Potter 3", "JK",1998)
    val livro4 = Livro (4,18.99,"Harry Potter 4", "JK",2000)

    val colecaoHP = Colecao (9,50.00, "Colecao HP" , mutableListOf(livro1,livro2,livro3,livro4))

    val livro5 = Livro (5,6.50,"Senhor do aneis 1", "Victor",1994)
    val livro6 = Livro (6,10.00,"Senhor do aneis 2", "Victor",1996)
    val livro7 = Livro (7,15.00,"Senhor do aneis 3", "Victor",1998)
    val livro8 = Livro (8,6.99,"Senhor do aneis 4", "Victor",2000)

    val colecaoSA = Colecao (10,50.00, "Colecao SA" , mutableListOf(livro5,livro6,livro7,livro8))

    Estoque.adicionar(ArtefatoItem(livro1,5))
    Estoque.adicionar(ArtefatoItem(livro2,5))
    Estoque.adicionar(ArtefatoItem(livro3,5))
    Estoque.adicionar(ArtefatoItem(livro4,5))
    Estoque.adicionar(ArtefatoItem(colecaoHP))

    Estoque.adicionar(ArtefatoItem(livro5,5))
    Estoque.adicionar(ArtefatoItem(livro6,5))
    Estoque.adicionar(ArtefatoItem(livro7,5))
    Estoque.adicionar(ArtefatoItem(livro8,5))
    Estoque.adicionar(ArtefatoItem(colecaoSA))


/*    try {
        println(Estoque.consultar(10))
    } catch (e : ProdutoNaoEncontradoException) {
        println("Produto nao encontrado")
    }
    try {
        Estoque.remover(ArtefatoItem(livro1, 6))
    } catch (e : SemEstoqueException) {
        println("Quantidade indisponivel")
    }
    println(Estoque.consultar(1))*/

   /* val venda1 = Venda (1, mutableListOf(ArtefatoItem(livro1,1), ArtefatoItem(colecaoHP,2)))

    PDV.vender(venda1)

    println(Estoque.consultar(1))*/

    menu()

}