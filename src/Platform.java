import java.util.ArrayList;

public class Platform {
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
