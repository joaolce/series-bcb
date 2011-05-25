package br.com.sunlive.series.erro;

import java.io.Serializable;

public class Erro implements Serializable {

  private int codigo;
  private String mensagem;

  public Erro(TipoErro tipo, String mensagem) {
    this.codigo = tipo.getCodigo();
    this.mensagem = mensagem;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getMensagem() {
    return mensagem;
  }
}
