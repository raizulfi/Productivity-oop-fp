import java.util.Scanner;

class UntimedTask extends Task {
    private String category;

    public UntimedTask(String title, String category) {
        super(title);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String getDetails() {
        return "Title: " + getTitle() + ", Category: " + category;
    }
}
