package org.example

import org.example.data.Cotacao
import org.example.data.Dividendo
import org.example.network.RetrofitClient

class AcaoRepository {

    suspend fun getDadosAcao(codigo: String): Pair<Cotacao?, List<Dividendo>?> {
        val cotacao: Cotacao? = try {
            val responseCotacao = RetrofitClient.instance.getCotacaoAtual(codigo)
            if (responseCotacao.isSuccessful) {
                responseCotacao.body()
            } else {
                println("Erro na resposta da cotação (Código: ${responseCotacao.code()}): ${responseCotacao.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            println("Erro ao fazer a requisição da cotação: ${e.message}")
            null
        }

        val dividendos: List<Dividendo>? = try {
            val responseDividendos = RetrofitClient.instance.getDividendos(codigo)
            if (responseDividendos.isSuccessful) {
                responseDividendos.body()
            } else {
                println("Erro na resposta dos dividendos (Código: ${responseDividendos.code()}): ${responseDividendos.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            println("Erro ao fazer a requisição dos dividendos: ${e.message}")
            null
        }

        return Pair(cotacao, dividendos)
    }
}
