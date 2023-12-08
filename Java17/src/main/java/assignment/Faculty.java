package assignment;

sealed interface Educational permits Faculty {
}

sealed public abstract class Faculty implements Educational permits EngineeringFaculty, HumanitiesFaculty, BusinessFaculty {

}

final class EngineeringFaculty extends Faculty {
    void engineering() {
        System.out.println("We teach computer science, civil engineering etc...");
    }

    @Override
    public String toString() {
        return "Engineering";
    }
}

final class HumanitiesFaculty extends Faculty {
    void humanities() {
        System.out.println("We teach social care, European studies etc...");
    }

    @Override
    public String toString() {
        return "Humanities";
    }
}

final class BusinessFaculty extends Faculty {
    void business() {
        System.out.println("We teach accountancy, law, economics etc....");
    }

    @Override
    public String toString() {
        return "Business";
    }
}


