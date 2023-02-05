package com.mclebtec.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    private ObjectId id;
    @JsonProperty(value = "first_name", required = true)
    @Field(name = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name", required = true)
    @Field(name = "last_name")
    private String lastName;
    @JsonProperty(value = "email_id", required = true)
    @Field(name = "email_id")
    private String emailId;

}
