package com.qa.base;

import com.qa.classes.Category;
import com.qa.classes.Pet;
import com.qa.classes.Status;
import com.qa.classes.Tags;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static final long id;
    public static final Category category;
    public static final String name;
    public static final Tags tags;
    public static final Status status;

    static {
        id = 1;
        category = new Category(1, "Dog");
        name = "Cango";
        tags = new Tags(1, "string");
        status = new Status("available");
    }

    public static Pet createPet() {
        return new Pet(id, category, name, tags, status);
    }



    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}