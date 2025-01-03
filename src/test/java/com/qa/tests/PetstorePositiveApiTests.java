package com.qa.tests;

import com.qa.classes.Pet;
import com.qa.base.BaseTest;
import com.qa.methods.Methods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetstorePositiveApiTests extends BaseTest {
    Methods methods = new Methods();
    private static final Pet PET = createPet();

    @Test(priority = 1)
    public void testCreatePet_Positive() {
        Response response = methods.postMethod(PET);
        JsonPath json= response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(json.getString("name"), PET.getName());
        Assert.assertEquals(json.getString("status"), PET.getStatus().getStatus());
    }

    @Test(priority = 2)
    public void testGetPetById_Positive() {
        Response response = methods.getMethod(PET.getId());
        JsonPath json= response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(json.getString("name"), PET.getName());
        Assert.assertEquals(json.getString("status"), PET.getStatus().getStatus());
    }

    @Test(priority = 3)
    public void testUpdatePet_Positive() {
        String updatedName = "Cango Updated";
        PET.setName(updatedName);
        Response response = methods.putMethod(PET);
        JsonPath json= response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(json.getString("name"), updatedName);
        Assert.assertEquals(json.getString("status"), PET.getStatus().getStatus());
    }

    @Test(priority = 4)
    public void testDeletePet_Positive() {
        Response response = methods.deleteMethod(PET.getId());
        JsonPath json= response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(json.getString("message"), String.valueOf(PET.getId()));
    }
}