package assignment;

final public class EngineeringFaculty extends Faculty {

    public void engineering() {
        System.out.println("We teach computer science, civil engineering etc...");
    }

    @Override
    public String toString() {
        return "Engineering";
    }
}
