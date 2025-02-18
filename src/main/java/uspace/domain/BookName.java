package uspace.domain;

public class BookName {

    private String name;

    public BookName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        BookName other = (BookName) obj;
        return this.name.equals(other.name);
    }
}
