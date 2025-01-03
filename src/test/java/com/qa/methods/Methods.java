package com.qa.methods;

import com.qa.classes.Pet;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Methods {

    Gson gson = new Gson();

    public Response postMethod(Pet pet) {

        String body = pet.toString();

        Response response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .log().all()
                .request("POST", "/pet")
                .then()
                .extract().response();

        return response;
    }

    public Response getMethod(long id) {

        Response response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .log().all()
                .request("GET", "/pet/" + id)
                .then()
                .extract().response();

        return response;
    }

    public Response putMethod(Pet pet) {

        String body = pet.toString();

        Response response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .log().all()
                .request("PUT", "/pet")
                .then()
                .extract().response();

        return response;
    }

    public Response deleteMethod(long id) {

        Response response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .log().all()
                .request("DELETE", "/pet/" + id)
                .then()
                .extract().response();

        return response;
    }
}