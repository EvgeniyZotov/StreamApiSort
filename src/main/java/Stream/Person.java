package Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String nationally;

}
