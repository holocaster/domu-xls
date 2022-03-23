package br.com.prcompany.domu.enums;

public enum ExitCodeEnum {

  ARGUMENTOS_TAMANHO_INVALIDOS(1, "Número de argumentos inválidos"),
  ARGUMENTOS_INVALIDOS(2, "Argumentos inválidos");

  private int id;
  private String descricao;

  ExitCodeEnum(int id, String descricao) {
    this.id = id;
    this.descricao = descricao;
  }


  public int getId() {
    return id;
  }

  public String getDescricao() {
    return descricao;
  }
}
