package contacts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Contact {
    private int id;
    private String name;

    public static Contact valueOf(String name) {
        return new Contact(0, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (name == null || !(obj instanceof Contact)) {
            return false;
        }
        return name.equals(((Contact) obj).name);
    }
}
