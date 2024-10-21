import kotlinx.coroutines.runBlocking
import org.example.AcaoRepository
import org.example.data.Cotacao
import org.example.data.Dividendo

fun main() = runBlocking {
    println("Buscando dados da ação VALE3...")

    // Cria uma instância de AcaoRepository
    val acaoRepository = AcaoRepository()

    // Obtendo a cotação
    acaoRepository.getCotacao("VALE3") { cotacao ->
        cotacao?.let {
            println("A cotação da ação é: R$ ${it.valor}")
        } ?: println("Não foi possível obter a cotação para a ação VALE3.")
    }

    // Obtendo dividendos
    acaoRepository.getDividendos("VALE3") { dividendos ->
        dividendos?.let {
            if (it.isNotEmpty()) {
                println("Dividendos da ação:")
                for (dividendo in it) {
                    println("Tipo: ${dividendo.tipo}, Data COM: ${dividendo.dataCom}, Pagamento: ${dividendo.pagamento}, Valor: R$ ${dividendo.valor}")
                }
            } else {
                println("Não foram encontrados dividendos para a ação VALE3.")
            }
        } ?: println("Não foi possível obter os dividendos para a ação VALE3.")
    }
}
