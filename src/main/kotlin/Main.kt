import kotlinx.coroutines.runBlocking
import org.example.AcaoRepository

fun main() = runBlocking {
    println("Buscando dados da ação VALE3...")

    // Cria uma instância de AcaoRepository
    val acaoRepository = AcaoRepository()

    // Chama a função getDadosAcao passando o código da ação
    val (cotacao, dividendos) = acaoRepository.getDadosAcao("VALE3")

    // Exibe os dados obtidos
    cotacao?.let {
        println("A cotação da ação é: R$ ${it.valor}")
    } ?: println("Não foi possível obter a cotação para a ação VALE3.")

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
