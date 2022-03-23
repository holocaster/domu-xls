package br.com.prcompany.domu.utils;

import br.com.prcompany.domu.model.Acao;
import java.time.LocalDate;

public final class DomuUtils {

  public static final String CLEAR_CORRETORA = "CLEAR CORRETORA";
  public static final String AVENUE_SECURITIES = "AVENUE SECURITIES";
  public static final String MODAL_CORRETORA = "MODAL DTVM LTDA";

  private DomuUtils() {}

  public static Acao criarAcao(String ticker, LocalDate data, boolean compra, double quantidade,
      Double valor, String corretora) {
    Acao acao = new Acao();
    acao.setNome(ticker);
    acao.setTicker(ticker);
    acao.setData(data);
    acao.setCompra(compra);
    acao.setQuantidade(quantidade);
    acao.setValor(valor);
    acao.setCorretora(corretora);
    return acao;
  }

  public static String montarNomeTitulo(String[] lines) {
    int init = 3;
    int end = lines.length - 5;
    StringBuilder sb = new StringBuilder();
    for (int i = init; i <= end; i++) {
      sb.append(lines[i].trim()).append(" ");
    }
    return sb.toString();
  }
}
