import assignment.*;

import java.util.*;

void main() {
//    seqCol();
//    seqSet();
//    seqMap();
    LecturerRecord mikeRecord = new LecturerRecord("Mike Bloggs", 44, new EngineeringFaculty(), new SoftwareEngineeringDept());
    recordPatterns(mikeRecord);
    LecturerRecord alanRecord = new LecturerRecord("Alan Austin", 64, new BusinessFaculty(), new AccountingDept());
    recordPatterns(alanRecord);
    LecturerRecord lisaRecord = new LecturerRecord("Lisa Bloggs", 65, new HumanitiesFaculty(), new SocialCareDept());
    recordPatterns(lisaRecord);
}

void seqCol() {
    SequencedCollection<LecturerRecord> records = new ArrayList<>();
    LecturerRecord janeRec = new LecturerRecord("Jane Bloggs", 24, new EngineeringFaculty(), new SoftwareEngineeringDept());
    LecturerRecord anneRec = new LecturerRecord("Dr. Anne Bloggs", 35, new EngineeringFaculty(), new SoftwareEngineeringDept());
    LecturerRecord joeRec = new LecturerRecord("Joe Bloggs PhD", 54, new EngineeringFaculty(), new SoftwareEngineeringDept());

    records.addFirst(janeRec);
    records.addFirst(anneRec);
    records.addLast(joeRec);

    System.out.println(records);
    System.out.println("getFirst(): " + records.getFirst());
    System.out.println("getLast(): " + records.getLast());
    System.out.println("removeLast(): " + records.removeLast());
    System.out.println(records);
    records.forEach(System.out::println);
    records.reversed().forEach(System.out::println);

}

void seqSet() {
    SequencedSet<LecturerRecord> recordsSet = new LinkedHashSet<>();
    LecturerRecord janeRec = new LecturerRecord("Jane Austin", 24, new BusinessFaculty(), new AccountingDept());
    LecturerRecord anneRec = new LecturerRecord("Anne Bronte PhD", 54, new BusinessFaculty(), new AccountingDept());
    LecturerRecord chaRec = new LecturerRecord("Dr. Charlotte Bronte", 35, new BusinessFaculty(), new AccountingDept());

    recordsSet.addFirst(janeRec);
    recordsSet.addFirst(janeRec);
    recordsSet.addFirst(janeRec);

    recordsSet.addFirst(chaRec);
    recordsSet.addLast(janeRec);
    recordsSet.addLast(anneRec);
    System.out.println(recordsSet);
    System.out.println("getFirst(): " + recordsSet.getFirst());
    System.out.println("getLast(): " + recordsSet.getLast());
    System.out.println("removeFirst(): " + recordsSet.removeFirst());
    System.out.println(recordsSet);
    recordsSet.forEach(System.out::println);
    recordsSet.reversed().forEach(System.out::println);

}

void seqMap() {
    SequencedMap<LecturerRecord, String> recordsMap = new LinkedHashMap<>();
    LecturerRecord kingRec = new LecturerRecord("King Lear", 88, new HumanitiesFaculty(), new SocialCareDept());
    LecturerRecord gonRec = new LecturerRecord("Goneril", 55, new HumanitiesFaculty(), new SocialCareDept());
    LecturerRecord regRec = new LecturerRecord("Regan", 50, new HumanitiesFaculty(), new SocialCareDept());
    LecturerRecord corRec = new LecturerRecord("Cordelia", 45, new HumanitiesFaculty(), new SocialCareDept());

    recordsMap.putFirst(gonRec, "Eldest");
    recordsMap.putFirst(regRec, "Middle");
    recordsMap.putLast(corRec, "Youngest");
    recordsMap.putLast(kingRec, "Father");

    System.out.println(recordsMap);
    System.out.println("firstEntry(): " + recordsMap.firstEntry());

    System.out.println("lastEntry(): " + recordsMap.lastEntry());
    System.out.println("pollLastEntry(): " + recordsMap.pollLastEntry());

    System.out.println(recordsMap);
    recordsMap.forEach((key, value) -> System.out.println(key + "; " + value));
    recordsMap.reversed().forEach((key, value) -> System.out.println(key + "; " + value));

}

void recordPatterns(LecturerRecord record) {

    System.out.println(
            switch (record) {
                case LecturerRecord r when r.age() >= 64 -> {
                    String txt = """
                            Name: %s,
                            Age: %d,
                            Faculty: %s,
                            Department: %s
                            """.formatted(r.name(), r.age(), r.faculty(), r.dept());
                    yield txt;
                }
                case null, default -> "";
            }
    );

}