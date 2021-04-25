package org.hell.homework08.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    public Author(String firstName, String lastName) {
        this.id = ObjectId.get().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
