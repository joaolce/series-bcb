package br.com.sunlive.series.erro;

import br.com.sunlive.series.web.response.Resposta;

public class SeriesException extends RuntimeException {

  private Erro erro;
  private Resposta tipoResposta;

  public SeriesException(Erro erro, Resposta tipoResposta) {
    this.erro = erro;
    this.tipoResposta = tipoResposta;
  }

  public Erro erro() {
    return erro;
  }

  public Resposta tipoResposta() {
    return tipoResposta;
  }
}
