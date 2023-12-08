package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankService {

    private static Connection con;
    private static final BankService bank = new BankService();    // connect to db

    public BankService() {
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
        initSql();
        bank.retrieveOne();
        System.out.println();
        bank.retrieveAll();
//        bank.deleteOne();
//        bank.retrieveAll();
//
//        bank.deleteAll();
//        bank.retrieveAll();

        bank.add();
        bank.retrieveAll();

        bank.update();
        bank.retrieveAll();

    }

    private void update() {
        BankAccount bankAccount = bank.getAccountDetails("123456", "12345678");
        System.out.println("BEFORE Update: " + bankAccount);

        bankAccount.setCustName("J. Bloggs");
        bankAccount.setCustAddress("London");
        int nRows = bank.updateBankAccount(bankAccount);
        if (nRows == 1) {
            System.out.println("Update OK: " + nRows);
            System.out.println("AFTER Update: " + bank.getAccountDetails("123456", "12345678"));
        } else {
            System.out.println("Update error: " + nRows);
        }
    }

    private int updateBankAccount(BankAccount bankAccount) {
        int nRows = -1;
        String updateSQL = "UPDATE BANK_TABLE " +
                "SET CUST_NAME=?, CUST_ADDRESS=?, BALANCE=? " +
                "WHERE (BRANCH_CODE = ? AND ACCOUNT_NUMBER=?)";

        try (PreparedStatement ps = con.prepareStatement(updateSQL)) {
            ps.setString(1, bankAccount.getCustName());
            ps.setString(2, bankAccount.getCustAddress());
            ps.setDouble(3, bankAccount.getBalance());
            ps.setString(4, bankAccount.getBranchCode());
            ps.setString(5, bankAccount.getAccountNo());

            nRows = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException in updateBankAccount()");
            e.printStackTrace();
        }
        return nRows;
    }

    private void add() {
        // add a bank account
        int nRows = bank.addBankAccount(
                new BankAccount("999999", "88888888", "SK", "Dublin", 100)
        );
        if (nRows == 1) System.out.println("Add OK: " + nRows);
        else System.out.println("Add error: " + nRows);

    }

    private int addBankAccount(BankAccount bankAccount) {
        int nRows = -1;
        String insertSQL = "INSERT INTO BANK_TABLE " +
                "(BRANCH_CODE, ACCOUNT_NUMBER, CUST_NAME," +
                "CUST_ADDRESS, BALANCE) " +
                "VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(insertSQL)) {

            ps.setString(1, bankAccount.getBranchCode());
            ps.setString(2, bankAccount.getAccountNo());
            ps.setString(3, bankAccount.getCustName());
            ps.setString(4, bankAccount.getCustAddress());
            ps.setDouble(5, bankAccount.getBalance());
            nRows = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException in addBankAccount()");
            e.printStackTrace();
        }
        return nRows;
    }

    private void retrieveOne() {
        // retrieve one Bank Account
        System.out.println(bank.getAccountDetails("123456", "12345678"));
    }

    private BankAccount getAccountDetails(String branchCode, String accountNo) {
        // A '?' is called a 'bind variable'. It is placeholder that we can populate at runtime
        String selectSQL = "SELECT * FROM BANK_TABLE WHERE (BRANCH_CODE = ? AND ACCOUNT_NUMBER = ?)";
        BankAccount bankAccount = null;

        // Cannot put the prepareStatement() and executeQuery() in the try-with-resources braces
        // as we have bind variables that must be set in between these 2 commands
        try (PreparedStatement ps = con.prepareStatement(selectSQL)) {

            ps.setString(1, branchCode);
            ps.setString(2, accountNo);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {   // no record there at all
                return bankAccount; // null
            }

            // process the record
            bankAccount = new BankAccount(
                    rs.getString("BRANCH_CODE"),
                    rs.getString(2), // "ACCOUNT_NUMBER
                    rs.getString("CUST_NAME"),
                    rs.getString("CUST_ADDRESS"),
                    rs.getDouble("BALANCE")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bankAccount;
    }

    private void retrieveAll() {
        // retrieve all bank accounts
        bank.getAllAccounts().forEach(System.out::println);
    }

    private List<BankAccount> getAllAccounts() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        String selectSQL = "SELECT * FROM BANK_TABLE";
        try (PreparedStatement ps = con.prepareStatement(selectSQL)) {
            boolean isResultSet = ps.execute();
            if (isResultSet) {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    // process the record
                    BankAccount bankAccount = new BankAccount(
                            rs.getString(1),
                            rs.getString("ACCOUNT_NUMBER"),
                            rs.getString("CUST_NAME"),
                            rs.getString("CUST_ADDRESS"),
                            rs.getDouble("BALANCE")
                    );
                    bankAccounts.add(bankAccount);
                }
            } else {
                System.out.println("Did an update!");
            }
        } catch (SQLException e) {
            System.err.println("SQLException in getAllAccounts");
            e.printStackTrace();
        }
        return bankAccounts;
    }

    public void deleteOne() {
        // delete one bank account
        int nRows = bank.deleteBankAccount("123456", "12345678");
        if (nRows == 1) {
            System.out.println("Delete OK: " + nRows);
        } else {
            System.out.println("Delete error: " + nRows);
        }
    }

    private int deleteBankAccount(String branchCode, String accountNo) {
        int nRows = -1;
        String deleteSQL = "DELETE FROM BANK_TABLE WHERE (BRANCH_CODE = ? AND ACCOUNT_NUMBER = ?)";

        try (PreparedStatement ps = con.prepareStatement(deleteSQL)) {
            ps.setString(1, branchCode);
            ps.setString(2, accountNo);

            nRows = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException in deleteBankAccount()");
            e.printStackTrace();
        }
        return nRows;
    }

    public void deleteAll() {
        // delete all bank accounts
        bank.deleteAllAccounts();
        System.out.println("Deleted all bank accounts");
    }

    private void deleteAllAccounts() {
        String deleteSQL = "DELETE FROM BANK_TABLE";

        try (PreparedStatement ps = con.prepareStatement(deleteSQL)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQLException in deleteAllAccounts()");
            e.printStackTrace();
        }
    }

    public static void initSql() throws SQLException {
        Statement s = con.createStatement();
        try {
            s.execute("DROP TABLE BANK_TABLE");
        } catch (SQLException sqle) {
            System.out.println("Table not found, not dropping");
        }

        s.execute("\n" +
                "CREATE TABLE BANK_TABLE\n" +
                "(\n" +
                "    BRANCH_CODE    VARCHAR(10),\n" +
                "    ACCOUNT_NUMBER VARCHAR(10),\n" +
                "    CUST_NAME      VARCHAR(20),\n" +
                "    CUST_ADDRESS   VARCHAR(50),\n" +
                "    BALANCE        DECIMAL(7),\n" +
                "    PRIMARY KEY (BRANCH_CODE, ACCOUNT_NUMBER)\n" +
                ");\n");

        s.execute("\n" +
                "INSERT INTO BANK_TABLE\n" +
                "VALUES ('123456',\n" +
                "        '12345678',\n" +
                "        'Joe Bloggs',\n" +
                "        'Athlone',\n" +
                "        300.0),\n" +
                "\n" +
                "       ('111111',\n" +
                "        '87654321',\n" +
                "        'Ann Bloggs',\n" +
                "        'Athlone',\n" +
                "        500.0),\n" +
                "\n" +
                "       ('222222',\n" +
                "        '67676767',\n" +
                "        'Jane Doe',\n" +
                "        'Dublin',\n" +
                "        200.0);\n");
    }
}
