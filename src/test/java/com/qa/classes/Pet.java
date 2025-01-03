package com.qa.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    private long id;
    private Category category;
    private String name;
    private Tags tags;
    private Status status;

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": "+ getId() +",\n" +
                "  \"category\": {\n" +
                "    \"id\": "+getCategory().getId()+",\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \""+getName()+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": "+getTags().getId()+",\n" +
                "      \"name\": \""+getTags().getName()+"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+getStatus().getStatus()+"\"\n" +
                "}";
    }

}
