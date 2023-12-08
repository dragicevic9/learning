package assignment;

public record LecturerRecord(String name, Integer age, Faculty faculty, Department dept) {

    public LecturerRecord {
        if (name.isBlank() || age < 0) {
            String errorMsg = """
                    Illegal argument passed:
                        "name": %s,
                        "age": %s
                    """.formatted(name, age);
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public boolean hasPhd() {
        return name.startsWith("Dr.") || name.endsWith("PhD");
    }

    public void whichFaculty() {
        switch (faculty) {
            case EngineeringFaculty ef -> {
                System.out.println("Faculty of: " + ef);
                ef.engineering();
            }
            case HumanitiesFaculty hf -> {
                System.out.println("Faculty of: " + hf);
                hf.humanities();
            }
            case BusinessFaculty bf -> {
                System.out.println("Faculty of: " + bf);
                bf.business();
            }
            default -> throw new IllegalArgumentException("Illegal argument: " + faculty);
        }
    }

    public void whichDept() {
        switch (dept) {
            case ComputerEngineeringDept ce -> {
                System.out.println("Dept of: " + ce);
                ce.compEng();
            }
            case SoftwareEngineeringDept se -> {
                System.out.println("Dept of: " + se);
                se.swEng();
            }
            case SocialCareDept sc -> {
                System.out.println("Dept of: " + sc);
                sc.socialCare();
            }
            case AccountingDept ad -> {
                System.out.println("Dept of: " + ad);
                ad.accounting();
            }
            default -> throw new IllegalArgumentException("Illegal argument: " + dept);
        }
    }
}
