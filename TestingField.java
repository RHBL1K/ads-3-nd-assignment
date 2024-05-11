import java.util.Objects;

public class TestingField {
    private String id;

    public TestingField(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (int i = 0; i < id.length(); i++) {
            hash = 31 * hash + id.charAt(i);
        }
        return hash;
    }

    @Override
    public String toString() {
        return "MyTestingClass{" + "id='" + id + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestingField that = (TestingField) o;
        return Objects.equals(id, that.id);
    }

}