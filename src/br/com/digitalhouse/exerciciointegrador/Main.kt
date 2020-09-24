package br.com.digitalhouse.exerciciointegrador

import br.com.digitalhouse.exerciciointegrador.exceptions.ProdutoNaoEncontradoException
import br.com.digitalhouse.exerciciointegrador.exceptions.SemEstoqueException
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import kotlin.random.Random

fun main() {

    val livro1 = Livro (1,10.50,"Harry Potter 1", "JK",1994);
    val livro2 = Livro (2,12.50,"Harry Potter 2", "JK",1996);
    val livro3 = Livro (3,14.80,"Harry Potter 3", "JK",1998);
    val livro4 = Livro (4,18.99,"Harry Potter 4", "JK",2000);

    val colecaoHP = Colecao (1,50.00, "Colecao HP" , mutableListOf(livro1,livro2,livro3,livro4));

    val livro5 = Livro (5,6.50,"Senhor do aneis 1", "Victor",1994);
    val livro6 = Livro (6,10.00,"Senhor do aneis 2", "Victor",1996);
    val livro7 = Livro (7,15.00,"Senhor do aneis 3", "Victor",1998);
    val livro8 = Livro (8,6.99,"Senhor do aneis 4", "Victor",2000);

    val colecaoSA = Colecao (1,50.00, "Colecao SA" , mutableListOf(livro5,livro6,livro7,livro8));

    Estoque.adicionar(ArtefatoItem(livro1,5))
    Estoque.adicionar(ArtefatoItem(livro2,5))
    Estoque.adicionar(ArtefatoItem(livro3,5))
    Estoque.adicionar(ArtefatoItem(livro4,5))

    Estoque.adicionar(ArtefatoItem(livro5,5))
    Estoque.adicionar(ArtefatoItem(livro6,5))
    Estoque.adicionar(ArtefatoItem(livro7,5))
    Estoque.adicionar(ArtefatoItem(livro8,5))

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

    println("Bem vindos a livraria, selecione um opção: 1) Cadastrar Livro, 2) Cadastrar Coleção, 3) Consultar Livro, 4)Venda")
    var resp = readLine();

    fun menuVenda(venda: Venda = Venda(Random.nextInt(999999))){
        println("Selecione a opção: 1)Adicionar item, 2)Finalizar venda")
        resp = readLine();
        if (resp == "1"){
            println("Digite o codigo do item: ")
            var cod = parseInt(readLine());
            try {
                var artefato = Estoque.consultar(cod).artefato
                println("Insira a quantidade do produto")
                var qnt = parseInt(readLine());
                venda.itens.add(ArtefatoItem(artefato, qnt))
                return menuVenda(venda)
            } catch (e : ProdutoNaoEncontradoException){
                println("Produto não encontrado")
                return menuVenda(venda)
            }
        }
        if (resp == "2"){
            try {
                PDV.vender(venda)
            } catch (e : SemEstoqueException){
                println("Erro ao finalizar a venda")
            }
            finally {
                return
            }
        }
    }

    fun menuConsultar() {
        println("Digite o codigo do produto que quer consultar")
        val cod = parseInt(readLine())
        try {
            println(Estoque.consultar(cod))
        } catch (e: ProdutoNaoEncontradoException) {
            println("Produto nao encontrado")
        }
    }

    fun menu() {
        when(resp){
            "1" -> {
                println("Digite o codigo do livro")
                var cod = parseInt(readLine());
                println("Digite o preco do livro")
                var preco = parseDouble(readLine());
                println("Digite o titulo do livro")
                var titulo = readLine();
                println("Digite o autor do livro")
                var autor = readLine();
                println("Digite o ano de lançamento do livro")
                var anoLanc = parseInt(readLine());
                val book = Livro(cod,preco,titulo,autor,anoLanc)
                println("Digite a quantidade a ser adicionada")
                var qnt = parseInt(readLine())
                var artefatoItem = ArtefatoItem(book,qnt)
            }
            "2" -> {
                println("Digite o codigo da colecao")
                var cod = parseInt(readLine());
                println("Digite o preco da colecao")
                var preco = parseDouble(readLine());
                println("Digite os codigos dos livros")
            }
            "3" ->{
                menuConsultar();
            }
            "4" -> {
                menuVenda();

            }
        }

    }
}