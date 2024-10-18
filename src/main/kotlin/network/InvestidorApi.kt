package org.example.network

import org.example.data.Cotacao
import org.example.data.Dividendo
import retrofit2.Response // Certifique-se de importar a classe Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InvestidorApi {
    @GET("acoes/{codigo}/")
    suspend fun getCotacaoAtual(@Path("codigo") codigo: String): Response<Cotacao>

    @GET("acoes/{codigo}/dividendos")
    suspend fun getDividendos(@Path("codigo") codigo: String): Response<List<Dividendo>>
}
