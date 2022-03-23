package br.com.prcompany.domu.services;

import br.com.prcompany.domu.model.Acao;
import br.com.prcompany.domu.services.enums.LineServiceEnum;
import br.com.prcompany.domu.services.factory.LineServiceFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class FileService {

  private final List<Acao> acoes = new ArrayList<>();

  public void carregarAcoes(File rootFolder) throws IOException {
    File[] list = rootFolder.listFiles((file) -> {
      if (!file.isDirectory()) {
        return file.getName().endsWith(".pdf");
      }
      return true;
    });

    if (list == null) {
      return;
    }

    Arrays.sort(list, Comparator.comparing(File::getName));
    for (File f : list) {
      if (f.isDirectory()) {
        carregarAcoes(f);
      } else {
        System.out.println("Analisando arquivo: " + f.getName());
        try (PDDocument document = PDDocument.load(f)) {
          if (!document.isEncrypted()) {
            PDFTextStripper tStripper = new PDFTextStripper();
            tStripper.setSortByPosition(true);

            String pdfFileInText = tStripper.getText(document);

            String[] lines = pdfFileInText.split("\\r?\\n");

            ILineService lineService;
            try {
              lineService = LineServiceFactory.getFactory(this.findType(lines));
            } catch (IllegalArgumentException e) {
              System.out.println(e.getMessage());
              System.out.println("Ignorando arquivo");
              continue;
            }
            List<Acao> acoesDaNota = lineService.parse(lines);
            for (Acao acao : acoesDaNota) {
              if (acao.getData() == null) {
                acao.setData(lineService.getDataNotaCorretagem());
              }
              acoes.add(acao);
            }
          }
        }
      }
    }
  }

  private LineServiceEnum findType(String[] lines) {
    for (String line : lines) {
      String upper = line.toUpperCase();
      if (upper.contains("AVENUE SECURITIES")) {
        return LineServiceEnum.AVENUE;
      }
      if (upper.contains("CLEAR CORRETORA")) {
        return LineServiceEnum.CLEAR;
      }
      if (upper.contains("MODAL DTVM LTDA")) {
        return LineServiceEnum.MODAL;
      }
    }

    throw new IllegalArgumentException("Tipo de nota de corretagem não encontrado nas linhas");
  }

  public List<Acao> getAcoes() {
    return acoes;
  }
}
