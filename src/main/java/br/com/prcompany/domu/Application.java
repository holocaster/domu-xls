package br.com.prcompany.domu;

import br.com.prcompany.domu.enums.ExitCodeEnum;
import br.com.prcompany.domu.services.DomuService;
import br.com.prcompany.domu.services.FileService;
import java.io.File;
import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println("Necessário informar 1 parâmetros");
      System.out.println("1 - Pasta com todas as notas de corretagem");
      System.exit(ExitCodeEnum.ARGUMENTOS_TAMANHO_INVALIDOS.getId());
      return;
    }
    File entryFolder = new File(args[0]);
    if (!entryFolder.isDirectory()) {
      System.out.println("Primeiro parâmetro não corresponde a uma pasta");
      System.exit(ExitCodeEnum.ARGUMENTOS_INVALIDOS.getId());
      return;
    }

    final FileService fileService = new FileService();
    fileService.carregarAcoes(entryFolder);
    System.out.println("------------------------");
    final DomuService domuService = new DomuService();
    domuService.analisarAcoes(fileService.getAcoes(), entryFolder);
  }

}
