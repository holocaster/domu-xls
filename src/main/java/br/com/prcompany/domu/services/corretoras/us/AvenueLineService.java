package br.com.prcompany.domu.services.corretoras.us;

import br.com.prcompany.domu.model.Acao;
import br.com.prcompany.domu.services.ILineService;
import br.com.prcompany.domu.utils.DateUtils;
import br.com.prcompany.domu.utils.DomuUtils;
import br.com.prcompany.domu.utils.TickerUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AvenueLineService implements ILineService {

  private static final List<String> avenueStocks = Arrays.asList("GOOGL ALPHABET", "AMZN AMAZON",
      "BRK.B BERKSHIRE", "FB FACEBOOK", "CRM SALESFORCE", "AAPL APPLE", "TSLA TESLA", "VNQI VANGUARD", "VNQ VANGUARD",
      "XLK SELECT SECTOR");

  @Override
  public List<Acao> parse(String[] lines) {
    List<Acao> acoesDaNota = new ArrayList<>();

    for (String line : lines) {
      String lineTrim = line.trim();
      final String lambdaLine = lineTrim;

      /**
       * Para linhas GOOGL ALPHABET INC    CAP STK CL A C Buy 0.1703 1460.74 6/9/2020 6/11/2020 Principal
       * As taxas vem nas linhas dessa forma
       * Commission or Equivalent $1.50
       * Transaction Fee $0.00
       * Other Fees / Credits $0.00
       * Net Amount $250.26
       */
      if (avenueStocks.stream().anyMatch(item -> lambdaLine.startsWith(item))) {
        System.out.println(lineTrim);
        String ticker = avenueStocks.stream().filter(item -> lambdaLine.startsWith(item))
            .findFirst().get();
        String[] lineSplit = lineTrim.split("\\s");
        Acao acao = new Acao();
        acao.setCompra("C".equals(lineSplit[lineSplit.length - 7]));
        acao.setData(LocalDate.parse(DateUtils.transformaDataAvenue(lineSplit[lineSplit.length - 3]),
            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        acao.setNome(ticker);
        acao.setTicker(ticker.split("\\s")[0]);
        acao.setQuantidade(Double.valueOf(lineSplit[lineSplit.length - 5]));
        acao.setValor(Double.valueOf(lineSplit[lineSplit.length - 4]));
        acao.setCorretora(DomuUtils.AVENUE_SECURITIES);
        acoesDaNota.add(acao);
      }

      /**
       * Para linhas 1 B 09/16/21 09/20/21 0.33329 TSLA 749.9899000 249.96 0.00 0.00 0.00 P3069 249.96 AVA0920 6 1
       * Neste tipo de nota de corretagem vem na própria linha
       */
      if (lineTrim.startsWith("1 B") || lineTrim.startsWith("1 S")) {
        System.out.println(lineTrim);
        String[] lineSplit = lineTrim.split("\\s");
        String nome = TickerUtils.extrairNomeStock(lineSplit[5]);
        Acao acao = new Acao();
        acao.setCompra("B".equals(lineSplit[1]));
        acao.setNome(nome);
        acao.setTicker(nome);
        acao.setData(LocalDate.parse(lineSplit[2], DateTimeFormatter.ofPattern("MM/dd/yy")));
        acao.setQuantidade(Double.valueOf(lineSplit[4]));
        acao.setValor(Double.valueOf(lineSplit[6].replace(",", "")));
        acao.setCorretora(DomuUtils.AVENUE_SECURITIES);
        acoesDaNota.add(acao);
      }
    }
    return acoesDaNota;
  }

  @Override
  public LocalDate getDataNotaCorretagem() {
    return null;
  }
}
