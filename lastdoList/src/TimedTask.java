import java.text.SimpleDateFormat;
import java.util.Date;

class TimedTask extends Task {
    private String date;
    private String category;

    public TimedTask(String title, Date date, String category) {
        super(title);
        this.date = date;
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String getDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Title: " + getTitle()
                + ", Date: " + dateFormat.format(date)
                + ", Category: " + category;
    }
}
