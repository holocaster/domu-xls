package br.com.prcompany.domu.services;

import br.com.prcompany.domu.model.Acao;
import br.com.prcompany.domu.utils.DomuUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DomuService {

  private static final String HEADERS[] = {"Ticker", "Data (DD/MM/YYYY)", "Tipo da Transação",
      "Quantidade", "Preço Unitário", "Taxas",
      "Corretora (Utilizar mesmo nome utilizado na plataforma"};
  private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static int rowId = 0;
  private XSSFWorkbook workbook;
  private XSSFSheet sheet;

  private void adicionaSubscricao(List<Acao> acoes) {
    acoes.add(
        DomuUtils.criarAcao("BCFF11", LocalDate.parse("04/10/2019", DTF), true, 10, 85.00,
            DomuUtils.CLEAR_CORRETORA));
    acoes.add(
        DomuUtils.criarAcao("BCFF11", LocalDate.parse("07/02/2020", DTF), true, 10, 91.39,
            DomuUtils.CLEAR_CORRETORA));
    acoes.add(
        DomuUtils.criarAcao("BCFF11", LocalDate.parse("30/03/2021", DTF), true, 25, 84.39,
            DomuUtils.CLEAR_CORRETORA));
    acoes.add(
        DomuUtils.criarAcao("MXRF11", LocalDate.parse("21/03/2021", DTF), true, 45, 10.15,
            DomuUtils.CLEAR_CORRETORA));
  }

  public void analisarAcoes(List<Acao> acoes, File entryFolder) throws IOException {
    this.adicionaSubscricao(acoes);
    Collections.sort(acoes, Comparator.comparing(Acao::getData));
    this.criarCabecalho();
    final XSSFCellStyle cellStyleData = workbook.createCellStyle();
    cellStyleData.setDataFormat(
        this.workbook.getCreationHelper().createDataFormat().getFormat("dd/MM/yyyy"));
    cellStyleData.setWrapText(true);
    acoes.stream().forEach(acao -> {
      this.criaCelulaDomu(acao, cellStyleData);
    });
    workbook.write(new FileOutputStream(new File(entryFolder, this.criarNomeXls())));
    workbook.close();
  }

  private String criarNomeXls() {
    return "domu-importacao-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".xlsx";
  }

  private void criaCelulaDomu(Acao acao, CellStyle cellStyleData) {
    final XSSFRow row = this.sheet.createRow(rowId++);
    row.createCell(0, CellType.STRING).setCellValue(acao.getTicker());
    final XSSFCell cell = row.createCell(1, CellType.NUMERIC);
    cell.setCellValue(acao.getData());
    cell.setCellStyle(cellStyleData);
    row.createCell(2, CellType.STRING).setCellValue(acao.isCompra() ? "APORTE" : "RETIRADA");
    row.createCell(3, CellType.NUMERIC).setCellValue(acao.getQuantidade());
    row.createCell(4, CellType.NUMERIC).setCellValue(acao.getValor());
    row.createCell(5, CellType.NUMERIC).setCellValue(0);
    row.createCell(6, CellType.STRING).setCellValue(acao.getCorretora());

  }

  private void criarCabecalho() {
    this.workbook = new XSSFWorkbook();
    this.sheet = workbook.createSheet();
    final XSSFRow row = sheet.createRow(rowId++);
    for (int i = 0; i < HEADERS.length; i++) {
      Cell cell = row.createCell(i, CellType.STRING);
      cell.setCellValue(HEADERS[i]);
    }
  }
}
