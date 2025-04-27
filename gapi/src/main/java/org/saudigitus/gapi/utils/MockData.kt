package org.saudigitus.gapi.utils

import org.saudigitus.gapi.data.models.Option

object MockData {
    val singleChoiceQuestions = listOf(
        Pair(
            "1. Assuntos de Monitoria",
            listOf(
                Option(
                    id = "11",
                    code = "1A",
                    name = "Multas por duplicacao de facturas"
                ),
                Option(
                    id = "12",
                    code = "1B",
                    name = "Muitas contas a cobrar dos clientes"
                ),
                Option(
                    id = "13",
                    code = "1C",
                    name = "Melhoria nas instalações em comparação à última visita realizada."
                )
            )
        ),
        Pair(
            "2. Pontos de Acção",
            listOf(
                Option(
                    id = "21",
                    code = "2A",
                    name = "Realizacao da compra e venda do gergelim"
                ),
                Option(
                    id = "22",
                    code = "2B",
                    name = "Realiza a gestão do seu negócio por meio da contabilidade e cumpre as obrigações fiscais obrigatórias."
                ),
                Option(
                    id = "23",
                    code = "2C",
                    name = "Produto adquirido e vendido na cidade da beira e exportado para a India e Dubai"
                )
            )
        ),
        Pair(
            "3. Recomendações",
            listOf(
                Option(
                    id = "31",
                    code = "3A",
                    name = "Ter domínio na determinação dos custos operacionais para avaliar se as taxas definidas tornam a empresa lucrativa."
                ),
                Option(
                    id = "32",
                    code = "3B",
                    name = "Ter caderno de Inventario e da Caixa."
                ),
                Option(
                    id = "33",
                    code = "3C",
                    name = "Suporte no preenchimento de modelos de pedido de financiamento e area legal."
                )
            )
        )
    )
}