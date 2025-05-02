public class RecordedCourse extends Course implements Downloadable{
    public RecordedCourse(String title, String teacher, int price) {
        super(title, teacher, price);
    }

    @Override
    public void start() {
        System.out.println("播放錄音課程:" + getTitle() + " | 老師" + getTeacher());
    }

    @Override
    public void downloadMaterials() {
        System.out.println("課程: " + getTitle() + "教材完成下載!");
    }
}
