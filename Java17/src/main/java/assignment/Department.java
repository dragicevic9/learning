package assignment;

sealed public abstract class Department permits
        ComputerEngineeringDept, SoftwareEngineeringDept, SocialCareDept, AccountingDept {
}

final class ComputerEngineeringDept extends Department {

    void compEng() {
        System.out.println("Custom computer engineering");
    }

    @Override
    public String toString() {
        return "Computer Engineering";
    }
}

final class SoftwareEngineeringDept extends Department {

    void swEng() {
        System.out.println("Custom software engineering");
    }

    @Override
    public String toString() {
        return "Software Engineering";
    }
}

final class SocialCareDept extends Department {

    void socialCare() {
        System.out.println("Custom social care");
    }

    @Override
    public String toString() {
        return "Social Care";
    }
}

final class AccountingDept extends Department {
    void accounting() {
        System.out.println("Custom accounting");
    }

    @Override
    public String toString() {
        return "Accounting";
    }
}