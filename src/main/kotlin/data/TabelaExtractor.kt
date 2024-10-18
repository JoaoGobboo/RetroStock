package org.example.data

import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

data class TabelaDados(
    val tipo: String,
    val dataCom: String,
    val pagamento: String,
    val valor: Double
)

class TabelaExtractor {

    fun extrairTabelaDeHtml(url: String): String {
        // Faz a conexão com a URL e obtém o documento HTML
        val document: Document = Jsoup.connect(url).get()

        // Seleciona a tabela do documento
        val tabela: Elements = document.select("table")

        // Cria uma lista para armazenar os dados da tabela
        val dados = mutableListOf<TabelaDados>()

        // Itera sobre as linhas da tabela
        for (linha: Element in tabela.select("tr")) {
            val colunas: Elements = linha.select("td")

            // Verifica se a linha tem 4 colunas
            if (colunas.size == 4) {
                val dado = TabelaDados(
                    tipo = colunas[0].text(),
                    dataCom = colunas[1].text(),
                    pagamento = colunas[2].text(),
                    valor = colunas[3].text().replace(",", ".").toDouble() // Converte o valor para Double
                )
                dados.add(dado)
            }
        }

        // Converte a lista de objetos TabelaDados para JSON usando Gson
        return Gson().toJson(dados)
    }
}
