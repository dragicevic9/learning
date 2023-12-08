package assignment;

public class University {
    public static void main(String[] args) {
//        forceExceptionForLecturerRecord();
        janeRecord();
        LecturerRecord anneRecord =
                new LecturerRecord("Dr. Anne Bloggs", 35, new BusinessFaculty(), new AccountingDept());
        testRecord(anneRecord);
        LecturerRecord joeRecord =
                new LecturerRecord("Joe Bloggs PhD", 54, new HumanitiesFaculty(), new SocialCareDept());
        testRecord(joeRecord);
    }

    private static void janeRecord() {
        LecturerRecord lecturerRecord =
                new LecturerRecord("Jane Bloggs", 24, new EngineeringFaculty(), new SoftwareEngineeringDept());
        System.out.println(lecturerRecord);

        String janeDetails = """
                Name is %s
                Age is %d
                Faculty is %s
                Department is %s
                """.formatted(lecturerRecord.name(), lecturerRecord.age(), lecturerRecord.faculty(), lecturerRecord.dept());
        System.out.println(janeDetails);

        lecturerRecord.whichFaculty();

        lecturerRecord.whichDept();

        System.out.println(lecturerRecord.hasPhd());
    }

    private static void testRecord(LecturerRecord lecturerRecord) {
        System.out.println(lecturerRecord);
        System.out.println((lecturerRecord.hasPhd()) ?
                lecturerRecord.name() + " has a Phd" : lecturerRecord.name() + " has not a PhD");
    }

    private static void forceExceptionForLecturerRecord() {
        LecturerRecord lecturerRecord = new LecturerRecord("Joe Bloggs", -4, null, null); // exception
    }
}
