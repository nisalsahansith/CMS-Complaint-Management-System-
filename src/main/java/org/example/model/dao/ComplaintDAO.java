package org.example.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComplaintDAO {
    String id;
    String description;
    String date;
    String status;
    String reply;
    String uId;

    public ComplaintDAO(String date, String description, String id, String reply, String uId) {
        this.date = date;
        this.description = description;
        this.id = id;
        this.reply = reply;
        this.uId = uId;
    }
}
