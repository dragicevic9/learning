package assignment;

public sealed abstract class Faculty
        implements Educational
        permits EngineeringFaculty, HumanitiesFaculty, BusinessFaculty {

}

