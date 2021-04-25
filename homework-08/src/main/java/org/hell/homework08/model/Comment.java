package org.hell.homework08.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String id;

    private String reply;

    public Comment(String reply) {
        this.id = ObjectId.get().toString();
        this.reply = reply;
    }
}
