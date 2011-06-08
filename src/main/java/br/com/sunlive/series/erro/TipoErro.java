package br.com.sunlive.series.erro;

import org.apache.commons.lang.StringUtils;

public enum TipoErro {

  PARAMETRO_NAO_INFORMADO(1, "O parâmetro não foi informado"),
  PARAMETRO_INCORRETO(2, "%complemento%"),
  RESPOSTA_NAO_IMPLEMENTADA(3, "A resposta '%complemento%' não está implementada"),
  INTERNO(4, "%complemento%");

  private int codigo;
  private String mensagem;

  private TipoErro(int codigo, String mensagem) {
    this.codigo = codigo;
    this.mensagem = mensagem;
  }

  public int getCodigo() {
    return codigo;
  }

  public String mensagem(String complemento) {
    return mensagem.replaceAll("%complemento%", StringUtils.trimToEmpty(complemento));
  }
}
