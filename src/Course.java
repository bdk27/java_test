import java.util.ArrayList;

public abstract class Course {
    private String title;
    private String teacher;
    private int price;
    private ArrayList<String> studentList = new ArrayList<>();

    public Course(String title, String teacher, int price) {
        this.title = title;
        this.teacher = teacher;
        this.price = price;
    }

    public void enrollStudent(String studentName) {
        studentList.add(studentName);
        System.out.println(studentName + " 已成功報名 " + title);
    }

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public abstract void start();
}
