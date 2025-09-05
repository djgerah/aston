package module_1;

public record Book(String title, int pages, int year) {

    @Override
    public String toString() {
        return title() + ": " + pages() + " pages, " + year();
    }
}