package org.hell.homework08.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    private String id;

    private String name;

    public Genre(String name) {
        this.id = ObjectId.get().toString();
        this.name = name;
    }
}
