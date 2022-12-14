import java.util.*;
public abstract class Course {
    private  String name;
    private long course_code;
    private int credit;
    private Classroom  location;

    protected Lecturer lecturer;
    private String notes;           //notes about course (only the lecturer of the course can add notes)
    private long[] idArr=new long[100];     //to store students' ids taking the course (100 students max can take the course)
    private int total_course_hour_in_aweek;    /*
     * variable that holds how many hours a course have in a week
     * This variable is useful when calculating whether a student fails because lack of attendance
     *  (this is done in Student class)
     */
    private List<String> topics = new ArrayList<>();


    Course(String nme,long cc,int crdt,int hour)  //When creating a course through the system, it will be created together with the course code, name, and credit information.
    {
        setName(nme);
        setCourse_code(cc);
        setCredit(crdt);
        setCourseHour(hour);
    }


    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setCourse_code(long course_code) {this.course_code = course_code;}
    public long getCourse_code() {return course_code;}
    public void setCredit(int credit) {this.credit = credit;}
    public int getCredit() {return credit;}
    public void setLocation(Classroom loc){
        this.location=loc;
    }   //setting course's location
    public Classroom getLocation() {return location;}
    public Lecturer getLecturer(){return lecturer;}
    //setting is done in child classes and Secretary
    public void setCourseHour(int ch){
        total_course_hour_in_aweek=ch;
    }
    public int getCourseHour(){
        return this.total_course_hour_in_aweek;
    }

    public String getNotes() {      //there is no regular set method for notes because adding a note has conditions
        return notes;
    }

    public List<String> getTopics() {return topics;}

    private int i=0;    //initial value of the idArr's index

    public int getI() {
        return i;
    }

    public void setstdID(long id){
        try
        {
            idArr[i]=id;        //Set the student id to their respective index.
            i++;
        }
        catch(ArrayIndexOutOfBoundsException ex){ System.out.println("This course has reached maximum number of students.");}

    }
    public void getstdIDs(){
        for (long element: idArr) {
         if(element!=0)     //Print the id if the element is initialized other than its default value 0.
            System.out.println(element);
        }
    }

    public long[] getidArr(){return this.idArr;}     //we set the id arr above

    /*
     * Check if the lecturer trying to add a note is the lecturer
     * that gives this course, if so, add the note.
     */
    public void addNotes(Lecturer lec, String note){
        if(lec==lecturer)
            this.notes=note;
        else
            System.out.println("This instructor is not the lecturer of this course.");

    }

    public boolean CourseforProject(Project p,Laboratory lab){
        if(lab.getCourse()==this)
            return true;
        return false;
    }

    public boolean AssignUgStdToProj(UndergradStudent std,Project project){

            if(std.getUndgradCourses().contains(project.getCo()))
                return true;
            else
                System.out.println("This student does not take this project's course.");

        return false;
    }

    public boolean AssignGradStdToProj(GradStudent std,Project project){
            if(std.getGradCourses().contains(project.getCo()))
                return true;
            else
                System.out.println("This student does not takes this project's course.");

        return false;
    }





}
