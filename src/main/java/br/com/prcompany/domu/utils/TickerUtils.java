package br.com.prcompany.domu.utils;

import java.util.HashMap;
import java.util.Map;

public final class TickerUtils {

  private static final Map<String,String> tickersAcoes = new HashMap<>();
  private static final Map<String,String> tickersFii = new HashMap<>();
  private static final Map<String,String> tickersEtf = new HashMap<>();
  private static final Map<String,String> tickersStocks = new HashMap<>();

  static {
    tickersAcoes.put("AMBEV S/A", "ABEV");
    tickersAcoes.put("BANCO INTER", "BIDI");
    tickersAcoes.put("ENERGIAS BR", "ENBR");
    tickersAcoes.put("ENGIE BRASIL", "EGIE");
    tickersAcoes.put("FLEURY", "FLRY");
    tickersAcoes.put("ITAUSA", "ITSA");
    tickersAcoes.put("ITSA4 - ITAUSA", "ITSA");
    tickersAcoes.put("LOJAS RENNER", "LREN");
    tickersAcoes.put("SINQIA", "SQIA");
    tickersAcoes.put("VIA", "VIIA");
    tickersAcoes.put("VIAVAREJO", "VIIA");
    tickersAcoes.put("WEG", "WEGE");
    tickersAcoes.put("WIZ S.A.", "WIZS");
    tickersAcoes.put("JHSF PART", "JHSF");
    tickersAcoes.put("MAGAZ LUIZA", "MGLU");
    tickersAcoes.put("GRENDENE", "GRND");
  }

  static {
    tickersFii.put("BCFF11" , "BCFF11");
    tickersFii.put("XPML11" , "XPML11");
    tickersFii.put("MXRF11" , "MXRF11");
    tickersFii.put("KNIP11" , "KNIP11");
    tickersFii.put("KNRI11" , "KNRI11");
    tickersFii.put("HLOG11" , "HLOG11");
    tickersFii.put("CPTS11" , "CPTS11");
  }

  static {
    tickersEtf.put("BOVV11", "BOVV11");
    tickersEtf.put("ITSA4 - ITAUSA", "ITSA4");
    tickersEtf.put("MATB11", "MATB11");
  }

  static {
    tickersStocks.put("BRKB", "BRK.B");
  }

  private TickerUtils() {}

  public static String extrairTicker(String nome) {
    if (nome.contains("ON")) {
      return tickersAcoes.get(nome.split("ON")[0].trim()) + "3";
    }
    if (nome.contains("PN")) {
      return tickersAcoes.get(nome.split("PN")[0].trim()) + "4";
    }

    String tickerName = tickersFii.keySet().stream().filter(ticker -> nome.contains(ticker)).findFirst().orElse(null);
    if (tickerName != null) {
      return tickersFii.get(tickerName);
    }

    tickerName = tickersEtf.keySet().stream().filter(ticker -> nome.contains(ticker)).findFirst().orElse(null);
    if (tickerName != null) {
      return tickersEtf.get(tickerName);
    }
    return nome;
  }

  public static String extrairNomeStock(String nome) {
    String tickerName = tickersStocks.keySet().stream().filter(ticker -> nome.contains(ticker)).findFirst().orElse(null);
    if (tickerName != null) {
      return tickersStocks.get(tickerName);
    }
    return nome;
  }
}
