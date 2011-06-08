API rest para recuperar os valores de uma série temporal pública
Estes dados tem como base o sistema SGS do Banco Central do Brasil - http://www.bcb.gov.br

Para utilizar deve ser executado da seguinte forma:

<<app-home>>/método/parâmetro/resposta

Métodos disponíveis:

* ultimoValor
  recupera o último valor disponível da série

  parâmetro: numeroSerie
    numeroSerie: inteiro

* valores
  recupera os valores de uma série para um determinado período de tempo

  parâmetro: numeroSerie|dataInicio|dataFim
    numeroSerie: inteiro
    dataInicio: data (ddMMaaaa)
    dataFim: data (ddMMaaaa)

Tipos de resposta implementados
  JSON (padrão, caso não seja informado)
  XML

Para rodar em ambiente local é necessário:
* JDK 1.6
* Apache Maven 2.2.1 ou superior

* executar o comando 'mvn install'
* executar o arquivo server-start.sh

url para acesso: http://localhost:8080/series


Obs: para rodar local deverá ser importado na JVM os certificados (webservice do bacen é https)
