package br.com.prcompany.domu.services.corretoras.brasil;

import br.com.prcompany.domu.model.Acao;
import br.com.prcompany.domu.services.ILineService;
import br.com.prcompany.domu.utils.DomuUtils;
import br.com.prcompany.domu.utils.TickerUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SinacorLineService implements ILineService {

  private static final List<String> subscricaoVenda = Arrays.asList("BCFF12");
  private LocalDate localDate;

  protected abstract String getCorretora();

  @Override
  public List<Acao> parse(String[] lines) {
    List<Acao> acoesDaNota = new ArrayList<>();

    boolean dataPregaoAchou = false;
    int tamanhoLinhaPregrao = 0;
    for (String line : lines) {
      String lineTrim = line.trim();
      if (dataPregaoAchou) {
        String[] split = lineTrim.split("\\s");
        localDate = LocalDate.parse(split[tamanhoLinhaPregrao],
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dataPregaoAchou = false;
        tamanhoLinhaPregrao = 0;
      }

      if (lineTrim.toUpperCase().contains("Nr. Nota Folha Data Pregão".toUpperCase())) {
        dataPregaoAchou = true;
        tamanhoLinhaPregrao = 2;

      }
      if (lineTrim.startsWith("BOVESPA 1") || lineTrim.startsWith("1-BOVESPA")) {
        lineTrim = lineTrim.replace("BOVESPA 1", "1-BOVESPA");
        System.out.println(lineTrim);
        String[] lineSplit = lineTrim.split("\\s");
        String nome = TickerUtils.extrairTicker(DomuUtils.montarNomeTitulo(lineSplit));
        if (subscricaoVenda.stream().anyMatch(item -> nome.contains(item))) {
          continue;
        }
        Acao acao = new Acao();
        acao.setCompra("C".equals(lineSplit[1]));
        acao.setNome(nome);
        acao.setTicker(nome);
        acao.setQuantidade(
            Double.parseDouble(lineSplit[lineSplit.length - 4].replaceAll(",", ".")));
        acao.setValor(Double.valueOf(lineSplit[lineSplit.length - 3].replaceAll(",", ".")));
        acao.setCorretora(this.getCorretora());
        acoesDaNota.add(acao);
      }
    }
    return acoesDaNota;
  }

  @Override
  public LocalDate getDataNotaCorretagem() {
    return localDate;
  }
}