import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.io.FileInputStream;
import java.io.IOException;

public class Extractor {

    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        try{
        // copiar o path do arquivo test.xls
        extractor.printXls("/workspace/xlsx-extractor/teste.xls");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void printXls(String path) throws IOException {
        FileInputStream file = new FileInputStream(path);
        HSSFWorkbook excelFile = new HSSFWorkbook(file);
        HSSFSheet excelSheet = excelFile.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = excelFile.getCreationHelper().createFormulaEvaluator();
        excelSheet.forEach(sheet -> {
            sheet.forEach(cell -> {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                }
            });
            System.out.println();
        });
    }


}
