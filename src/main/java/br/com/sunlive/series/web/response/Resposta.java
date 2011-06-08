package br.com.sunlive.series.web.response;

import br.com.sunlive.series.erro.Erro;
import br.com.sunlive.series.erro.SeriesException;
import br.com.sunlive.series.erro.TipoErro;
import org.apache.wicket.IRequestTarget;

public enum Resposta {

  JSON("json", JSONRequestTarget.class), XML("xml", XmlRequestTarget.class);

  private String nome;
  private Class<? extends IRequestTarget> clazz;

  private Resposta(String nome, Class<? extends IRequestTarget> clazz) {
    this.nome = nome;
    this.clazz = clazz;
  }

  public String nome() {
    return nome;
  }

  @SuppressWarnings("unchecked")
  public Class<IRequestTarget> implementacao() {
    return (Class<IRequestTarget>) clazz;
  }

  public static Resposta padrao() {
    return JSON;
  }

  public static Resposta get(String nomeResposta) {
    for (Resposta resposta : values()) {
      if (resposta.nome().equalsIgnoreCase(nomeResposta)) {
        return resposta;
      }
    }
    TipoErro tipoErro = TipoErro.RESPOSTA_NAO_IMPLEMENTADA;
    throw new SeriesException(new Erro(tipoErro, tipoErro.mensagem(nomeResposta)), padrao());
  }
}
