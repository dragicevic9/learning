package jdbc;

import java.sql.*;

public class CallingStoredProcedures {


    private static Connection con;
    private static CallingStoredProcedures bank = new CallingStoredProcedures();    // connect to db

    public CallingStoredProcedures() {
        try {
            con = DriverManager
                    .getConnection("jdbc:h2:mem:BANK_DB;DB_CLOSE_DELAY=-1", "admin", "admin");
            System.out.println("DB connection is OK!");
        } catch (SQLException ex) {
            System.err.println("Exception.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        BankService.initSql();
        noParams();
        inParam();
        outParam();
        inOutParam();
    }

    private static void noParams() {
        // DB - "CREATE PROCEDURE read_dublin_addresses()"
        String noParamsSQL = "{call read_dublin_addresses()}";
        // try-with-resources will tidy up
        // PreparedStatement ps = con.prepareStatement(sql)

        try (CallableStatement cs = con.prepareCall(noParamsSQL);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next())
                System.out.println(rs.getString("CUST_ADDRESS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void inParam() {
        String inParamSQL = "{call read_adresses(?)}";
        // DB - "CREATE PROCEDURE read_addresses(IN address VARCHAR(50))"
        try (CallableStatement cs = con.prepareCall(inParamSQL)) {

            cs.setString(1, "Dublin");

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next())
                    System.out.println(rs.getString("CUST_ADDRESS"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void outParam() {
        String outParamSQL = "{?= call count_customers(?)}";
        // DB - "CREATE PROCEDURE count_customers(OUT count INT)"
        try (CallableStatement cs = con.prepareCall(outParamSQL)) {

            cs.registerOutParameter(1, Types.INTEGER); // do this for each OUT, INOUT parameter
            cs.execute(); // no ResultSet coming back this time
            System.out.println(cs.getInt("count"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void inOutParam() {
        String inOutParamSQL = "{call squar_number(?)}";
        // DB - "CREATE PROCEDURE square_number(INOUT number INT)"

        try (CallableStatement cs = con.prepareCall(inOutParamSQL)) {

            cs.setInt(1, 5);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            System.out.println(cs.getInt("count"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
