package org.example

import data.DataScraper
import org.example.data.Cotacao
import org.example.data.Dividendo
import org.example.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AcaoRepository {

    private val api = RetrofitClient.createApi() // Certifique-se de que esta função existe
    private val scraper = DataScraper()

    fun getCotacao(acao: String, callback: (Cotacao?) -> Unit) {
        val call = api.getCotacao(acao) // Verifique se getCotacao(acao) está definido em InvestidorApi
        call.enqueue(object : Callback<Cotacao> {
            override fun onResponse(call: Call<Cotacao>, response: Response<Cotacao>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    // Se a API falhar, tenta fazer scraping
                    val scrapedCotacao = scraper.getCotacao(acao)
                    // Passando a ação e o valor corretamente
                    callback(Cotacao(acao = acao, valor = scrapedCotacao?.toDoubleOrNull() ?: 0.0))
                }
            }

            override fun onFailure(call: Call<Cotacao>, t: Throwable) {
                // Se a API falhar, tenta fazer scraping
                val scrapedCotacao = scraper.getCotacao(acao)
                // Passando a ação e o valor corretamente
                callback(Cotacao(acao = acao, valor = scrapedCotacao?.toDoubleOrNull() ?: 0.0))
            }
        })
    }

    fun getDividendos(acao: String, callback: (List<Dividendo>?) -> Unit) {
        val call = api.getDividendos(acao) // Verifique se getDividendos(acao) está definido em InvestidorApi
        call.enqueue(object : Callback<List<Dividendo>> {
            override fun onResponse(call: Call<List<Dividendo>>, response: Response<List<Dividendo>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    // Se a API falhar, tenta fazer scraping
                    val scrapedDividendos = scraper.getDividendos(acao)
                    callback(scrapedDividendos)
                }
            }

            override fun onFailure(call: Call<List<Dividendo>>, t: Throwable) {
                // Se a API falhar, tenta fazer scraping
                val scrapedDividendos = scraper.getDividendos(acao)
                callback(scrapedDividendos)
            }
        })
    }
}
