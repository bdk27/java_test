import java.util.ArrayList;

public interface Downloadable {
    void downloadMaterials();
}

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

    void start();
    void enrollStudent();

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getPrice() {
        return price;
    }
}

public class LiveCourse {

}

public class RecordedCourse {

}

public class platform {
    private static int totalCourses = 0;
    private ArrayList<Course> courseList = new ArrayList<>();

    public void addCourse(Course course) {
        courseList.add(course);
        totalCourses++;
        System.out.println("課程已上架: " + course.getTitle());
    }

    public void listAllCourse() {
        System.out.println("所有課程: ");
        for(Course c : courseList) {
            System.out.println("- " + c.getTitle() + "(老師:" + c.getTeacher() + ", 價格:$" + c.getPrice() + ")");
        }
    }

    public static int getTotalCourses() {
        return totalCourses;
    }
}

public class OnlineCoursePlatform {
    public static void main(String[] args) {
        
    }
}
