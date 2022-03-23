package br.com.prcompany.domu.model;

import java.time.LocalDate;

public class Acao {

  private boolean compra;
  private String nome;
  private LocalDate data;
  private Double quantidade;
  private Double valor;
  private String ticker;
  private String corretora;

  public String getCorretora() {
    return corretora;
  }

  public void setCorretora(String corretora) {
    this.corretora = corretora;
  }

  public boolean isCompra() {
    return compra;
  }

  public void setCompra(boolean compra) {
    this.compra = compra;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public Double getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Double quantidade) {
    this.quantidade = quantidade;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public String toString() {
    return "Acao{" +
        "compra=" + compra +
        ", nome='" + nome + '\'' +
        ", data=" + data +
        ", quantidade=" + quantidade +
        ", valor=" + valor +
        ", ticker='" + ticker + '\'' +
        '}';
  }
}
