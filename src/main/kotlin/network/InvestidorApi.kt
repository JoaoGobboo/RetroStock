package org.example.network

import org.example.data.Cotacao
import org.example.data.Dividendo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface InvestidorApi {
    @GET("acoes/{acao}/") // Ajuste para refletir a estrutura da URL para cotação.
    fun getCotacao(@Path("acao") acao: String): Call<Cotacao> // Passa o parâmetro 'acao' na URL.

    @GET("acoes/{acao}/dividendos/") // Ajuste para refletir a estrutura da URL para dividendos.
    fun getDividendos(@Path("acao") acao: String): Call<List<Dividendo>> // Passa o parâmetro 'acao' na URL.
}
