import lombok.SneakyThrows;

import java.sql.*;
import java.util.Date;

public class testPerformance {
    @SneakyThrows
    public static void main(String[] args) {
        Connection con = getDatabaseConnection();
        System.out.println("con is:  " + con);
//        writeWithCompileQuery(10);
//        writeInABatchWithCompiledQuery(1000000);

        writeInABatchWithCompiledQuery(10);
        con.close();


    }

    public static void writeWithCompileQuery(int records) {

        PreparedStatement statement;

        try {
            Connection connection = getDatabaseConnection();
            connection.setAutoCommit(true);

            String compiledQuery = "INSERT INTO SYS.USER_TABLE(ID, ADDRESS, AGE, EMAIL, USER_NAME)" +
                    " VALUES" + "(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(compiledQuery);

            long start = System.currentTimeMillis();

            for(int index = 1; index < records; index++) {
                statement.setInt(1, index );
                statement.setString(2, "emp number-"+index);
                statement.setInt(3, index);
                statement.setString(4, "index");
                statement.setString(5, "username");

                long startInternal = System.currentTimeMillis();
                statement.executeUpdate();
                System.out.println("each transaction time taken = " + (System.currentTimeMillis() - startInternal) + " ms");
            }

            long end = System.currentTimeMillis();
            System.out.println("total time taken = " + (end - start) + " ms");
            System.out.println("avg total time taken = " + (end - start)/ records + " ms");

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.err.println("SQLException information");
            while (ex != null) {
                System.err.println("Error msg: " + ex.getMessage());
                ex = ex.getNextException();
            }
        }
    }

    public static int[] writeInABatchWithCompiledQuery(int records) {
        PreparedStatement preparedStatement;
        int count = 0;
        int batchSize = 1000;

        try {
            Connection connection = getDatabaseConnection();
            connection.setAutoCommit(true);

            String compiledQuery = "INSERT INTO USER_TABLE(ID, ADDRESS, AGE, EMAIL, USER_NAME)" +
                    " VALUES" + "(?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(compiledQuery);

            for(int index = 1; index <= records; index++) {
                preparedStatement.setInt(1, index);
                preparedStatement.setString(2, "empo number-"+index);
                Date date = new Date();
                date.getTime();
//                preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
                preparedStatement.setTimestamp(3, new Timestamp(date.getTime()));
                preparedStatement.setInt(4, index+200);
                preparedStatement.setString(5, "usernames");
                preparedStatement.addBatch();
                count ++;

                if (count % batchSize == 0) {
                    int[] inserted = preparedStatement.executeBatch();
                    System.out.println("Insert 1000 records successfully!!!");
                }
            }

            long start = System.currentTimeMillis();
            int[] inserted = preparedStatement.executeBatch();
            long end = System.currentTimeMillis();

            System.out.println("total time taken to insert the batch = " + (end - start) + " ms");
            System.out.println("total time taken = " + (end - start)/records + " s");

            preparedStatement.close();
            connection.close();

            return inserted;

        } catch (SQLException ex) {
            System.err.println("SQLException information");
            while (ex != null) {
                System.err.println("Error msg: " + ex.getMessage());
                ex = ex.getNextException();
            }
            throw new RuntimeException("Error");
        }
    }

    private static Connection getDatabaseConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ctct","ctct#2020");
            System.out.println("Success:  " + con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }


}
