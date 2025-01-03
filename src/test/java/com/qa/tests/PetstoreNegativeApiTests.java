package com.qa.tests;

import com.qa.classes.Category;
import com.qa.classes.Pet;
import com.qa.classes.Status;
import com.qa.classes.Tags;
import com.qa.base.BaseTest;
import com.qa.methods.Methods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetstoreNegativeApiTests extends BaseTest {
    Methods methods = new Methods();

    @Test(priority = 1)
    public void testCreatePet_Negative() {
        Pet invalidPet = new Pet(-1, new Category(0, null), null, new Tags(0, null), new Status(null)); // Eksik bilgilerle
        Response response = methods.postMethod(invalidPet);

        JsonPath json = response.jsonPath();
        long id = json.getLong("id");
        Assert.assertFalse(id == -1);

    }

    @Test(priority = 2)
    public void testGetPetById_Negative() {
        long invalidId = -1;
        Response response = methods.getMethod(invalidId);
        JsonPath json = response.jsonPath();

        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertTrue(json.getString("message").contains("Pet not found"));
    }

    @Test(priority = 3)
    public void testUpdatePet_Negative() {
        Pet invalidPet = new Pet(-1, new Category(0, null), null, new Tags(0, null), new Status(null)); // Eksik bilgilerle
        Response response = methods.putMethod(invalidPet);

        JsonPath json = response.jsonPath();
        long id = json.getLong("id");
        Assert.assertFalse(id == -1);
    }

    @Test(priority = 4)
    public void testDeletePet_Negative() {
        long invalidId = -1;
        Response response = methods.deleteMethod(invalidId);

        Assert.assertEquals(response.statusCode(), 404, "Status code is not 404 as expected");

        String responseBody = response.getBody().asString();
        if (!responseBody.isEmpty() && response.getContentType().contains("application/json")) {
            JsonPath json = response.jsonPath();
            Assert.assertTrue(json.getString("message").contains("Pet not found"), "Error message does not match");
        } else {
            System.out.println("Response body is empty or not in JSON format");
        }
    }
}