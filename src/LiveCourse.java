public class LiveCourse extends Course {
    private String liveTime;

    public LiveCourse(String title, String teacher, int price, String liveTime) {
        super(title, teacher, price);
        this.liveTime = liveTime;
    }

    @Override
    public void start() {
        System.out.println("正在直播課程：" + getTitle() + " | 老師：" + getTeacher());
    }
}
