package assignment;

final public class HumanitiesFaculty extends Faculty {

    public void humanities() {
        System.out.println("We teach social care, European studies etc...");
    }

    @Override
    public String toString() {
        return "Humanities";
    }
}
