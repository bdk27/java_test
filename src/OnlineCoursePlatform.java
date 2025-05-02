public class OnlineCoursePlatform {
    public static void main(String[] args) {
        Platform platform = new Platform();
        LiveCourse live = new LiveCourse("java", "brian", 2000, "每周一晚上8點");
        RecordedCourse recorded = new RecordedCourse("vue3", "amy", 1500);

        platform.addCourse(live);
        platform.addCourse(recorded);

        live.enrollStudent("小名");
        recorded.enrollStudent("小華");

        live.start();
        recorded.start();

        recorded.downloadMaterials();

        platform.listAllCourse();
        System.out.println("目前課程總數: " + Platform.getTotalCourses());
    }
}
