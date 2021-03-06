package br.com.exceljava;

import java.util.List;

import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GerenciadorCheques {

    public List<Cheque> criar() throws IOException {

        List<Cheque> cheques = new ArrayList<>();
        //Recuperando o arquivo
        @Cleanup FileInputStream file = new FileInputStream("PlaniliaJava.xlsx");

        Workbook workbook = new XSSFWorkbook(file);
        //setando a aba
        Sheet sheet = workbook.getSheetAt(0);
        // setando as linhas
        List<Row> rows = (List<Row>) toList(sheet.iterator());

        //remove os cabecalho
        rows.remove(0);

        rows.forEach(row -> {
            //Seteando as celulas
            List<Cell> cells = (List<Cell>) toList(row.cellIterator());

            Cheque cheque = Cheque.builder()
                    //Atribuiu os valores para classe chaque
                    .nomes(cells.get(0).getStringCellValue())
                    .ultomoNome(cells.get(1).getStringCellValue())
                    .email(cells.get(2).getStringCellValue())
                    .date(cells.get(3).getDateCellValue())
                    .build();
            cheques.add(cheque);
        });


        return cheques;
    }

    public List<?> toList(Iterator<?> iterator) {
        return IteratorUtils.toList(iterator);

    }

    public void imprimir(List<Cheque> cheques) {
        cheques.forEach(System.out::println);
    }

}
