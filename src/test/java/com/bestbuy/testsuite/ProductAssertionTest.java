package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductAssertionTest extends TestBase {

    static ValidatableResponse response;                                                          //GV declaration

    @Test
    public void productsAssert() {
        response =
                given()
                        .when()
                        .get("/products")
                        .then().statusCode(200);

//        11. Verify if the total is equal to 51957
        response.body("total", equalTo(51957));

//        12. Verify  if the stores of limit is equal to 10
        response.body("limit", equalTo(10));

//        13. Check the single ‘Name’ in the Array list Duracell - AA 1.5V CopperTop Batteries (4-Pack)
        response.body("data.name" ,hasItem("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"));


//        14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
//            Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))

        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));

//        15. Verify the productId=185230 inside categories of the third name is “Household Batteries”
        response
                .body("data[3].id", equalTo(185230))                                    //Don't close with ; continues until last
                .body("data[3].categories[2]", hasEntry("name", "Household Batteries"));


//        16. Verify the categories second name = “Car Installation Parts & Accessories” of productID = 333179
        response.body("data[8].categories[1].name", equalTo("Car Installation Parts & Accessories"));
        //Expected: a collection containing "Housewares" (if you do hasItem its error)
        //  Actual: Housewares
        //response.body("data[8].categories[1]", hasEntry("name", "Housewares"));             //can use either


//        17. Verify the price = 4.99 of forth product
        response.body("data[3].price", equalTo(8.99f));


//        18. Verify the Product name = Pioneer - 4" 3-Way Surface-Mount Speakers with IMPP Composite Cones (Pair) - Black
        response.body("data[5].name", equalTo("Pioneer - 4\" 3-Way Surface-Mount Speakers with IMPP Composite Cones (Pair) - Black"));


//        19. Verify the ProductId = 346575 for the 9th product
        response.body("data[8].id", equalTo(346575));

//        20. Verify the prodctId = 346575 have 5 categories
        response.body("data[9].categories", hasSize(5));                                     //data[9].categories.length in JSON path
    }
}
