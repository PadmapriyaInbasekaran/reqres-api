package api.endpoints;

import api.payload.User;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
    public static RequestLoggingFilter requestLoggingFilter;
    public static ResponseLoggingFilter responseLoggingFilter;

    public static void createUser(User Payload) throws FileNotFoundException {

        OutputStream outputStream = new FileOutputStream("PostData.txt", true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream, true);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given().log().all()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
                .contentType("application/json")
                .body(Payload)
                .when()
                .post(Constants.post_url);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);

    }

    public static void getUser(int id) throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("GetData.txt", true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream, false);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
                .when()
                .get(Constants.get_url + id);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    public static void updateUser(User Payload) throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("UpdateData.txt", true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream, false);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given().log().all()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
                .contentType("application/json")
                .body(Payload)
                .when()
                .put(Constants.put_url + Payload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    public static void deleteUser(int id) throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("DeleteData.txt", true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream, false);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
                .when()
                .delete(Constants.delete_url + id);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 204);
    }

}
