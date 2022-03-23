package br.com.prcompany.domu.services;

import br.com.prcompany.domu.model.Acao;
import java.time.LocalDate;
import java.util.List;

public interface ILineService {

  List<Acao> parse(String[] lines);

  LocalDate getDataNotaCorretagem();
}
