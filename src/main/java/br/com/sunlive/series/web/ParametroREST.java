package br.com.sunlive.series.web;

import org.apache.wicket.PageParameters;

public enum ParametroREST {

  PARAMETRO("parametro"), RESPOSTA("resposta");

  private String nome;

  private ParametroREST(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public String get(PageParameters pageParameters) {
    return pageParameters.getString(getNome());
  }
}
