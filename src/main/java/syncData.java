import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


public class syncData {

    public static void main(String[] args) throws SQLException, IOException, InvalidFormatException {
        test();
    }

    private static Connection getDatabaseConnection() {
        Connection connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://192.168.1.219/vhr","vhr","vhr");
            System.out.println("Success:  " + connection);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    /**
     * read data from excel file
     * @return list string data
     * @throws IOException
     * @throws InvalidFormatException
     */
    private static List excelRead() throws IOException, InvalidFormatException {
        String EXCEL_FILE_LOCATION = "C:/Users/PGT/Desktop/ghr3_sys_cat_type.xlsx";
        List<String> lst = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(new File(EXCEL_FILE_LOCATION));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

        // Getting the Sheet at index zero
        //Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

        /*sheet.forEach(row -> {
            row.forEach(cell -> {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            });
            System.out.println();
        });*/

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            int rowFirst = workbook.getSheetAt(0).getRow(0).getLastCellNum() -1;
            for (Row row: sheet) {
                row.getLastCellNum();
                String cellValue = "(";
                /*for (Cell cell: row) {
                    System.out.println(cell.getColumnIndex());
                    cellValue += dataFormatter.formatCellValue(cell) + "/";
                }*/
                for (int limit = 0; limit <= rowFirst; limit++) {
                    Cell cell = row.getCell(limit);
                    if (cell != null) {
                        if (dataFormatter.formatCellValue(cell).trim() == "") {
                            cellValue += "NULL" + ", ";
                        } else {
                            cellValue += "'" + dataFormatter.formatCellValue(cell) + "', ";
                        }
                    } else {
                        cellValue += "NULL" + ", ";
                    }
                }
                cellValue = cellValue.substring(0, cellValue.length()-2) + ")";
                lst.add(cellValue);
            }
        }

        lst.forEach(t -> {
            System.out.println(t);
        });
        return lst;
    }

    private static String getQueryFromExcelFile() throws IOException, InvalidFormatException {
        String alias = (String) excelRead().get(0);
        String[] aliasArr = alias.split("/");
        String sqlInsert = "INSERT INTO sys_cat_type_itsol";
        for (String a : aliasArr) {
            sqlInsert += a + ",";
        }
        String sqlSelect = sqlInsert.substring(0, sqlInsert.length()-3) + ") VALUES ";
        System.out.println(sqlSelect);
        return sqlSelect.replaceAll("'", "");
    }

    /**
     *
     * insert
     * @throws SQLException
     */
    private static void test() throws SQLException, IOException, InvalidFormatException {
        Connection connection = getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        List lstQuery = excelRead();
        lstQuery.remove(0);
        String compiledQuery = getQueryFromExcelFile();

        for(Object sql : lstQuery) {
            sql = compiledQuery + sql;
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.execute();
            /*preparedStatement.addBatch();*/
        }
        /*preparedStatement = connection.prepareStatement(compiledQuery);
        preparedStatement.execute(compiledQuery);
        preparedStatement.executeBatch();*/
        connection.close();
    }



}
