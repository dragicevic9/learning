package assignment;

final public class AccountingDept extends Department {

    public void accounting() {
        System.out.println("Custom social care");
    }

    @Override
    public String toString() {
        return "Accounting";
    }
}
