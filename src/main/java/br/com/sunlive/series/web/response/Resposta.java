package br.com.sunlive.series.web.response;

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

  public static Resposta get(String nomeResposta) {
    for (Resposta resposta : values()) {
      if (resposta.nome().equalsIgnoreCase(nomeResposta)) {
        return resposta;
      }
    }
    return null;
  }
}
