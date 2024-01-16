package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public static void storesExtractTest()
    {
        response =
                given()
                        .when()
                        .get("stores")
                        .then().statusCode(200);

//        1. Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("Limit is : " + limit);

//        2. Extract the total
        int total =  response.extract().path("total");
        System.out.println("Total is :" + total);

//        3. Extract the name of 5th store
        String nameOfTheStore =  response.extract().path("data[4].name");
        System.out.println("Name of the 5th store is :" +nameOfTheStore);


//        4. Extract the names of all the store
        List<String> namesOfAllStores = response.extract().path("data.name ");
        System.out.println("Names of all stores :" + namesOfAllStores);

//        5. Extract the storeId of all the store
        List<Integer> storeId = response.extract().path("data.id");                                                  //ClassCastException
        System.out.println("Store Id of all stores : " + storeId );

//        6. Print the size of the data list
        List<Integer> listOfData = response.extract().path("data");
        System.out.println("Size of the data list : " + listOfData.size());

//        7. Get all the value of the store where store name = St Cloud
        List<Integer> valuesOfTheStore = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("Values of the store : " + valuesOfTheStore);

//        8. Get the address of the store where store name = Rochester
        List<String> addressOfRochester =  response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("Address of Rochester is : " +addressOfRochester);


//        9. Get all the services of 8th store
        List<String> allServices =  response.extract().path("data[7].services");
        System.out.println("All services of 8th store : " + allServices );


//        10. Get storeservices of the store where service name = Windows Store
        HashMap<String,String> storeServices = response.extract().path("data[7].services[5].storeservices");
        System.out.println("StoreServices of the store :" + storeServices );

//        11. Get all the storeId of all the store
        List<Integer> allStoreId =  response.extract().path("data.services.storeservices.storeId");
        System.out.println("All StoreIds of all stores : " + allStoreId);

//        12. Get id of all the store
        List<Integer> idOfAllStores = response.extract().path("data.id");
        System.out.println("Id of all stores : " +idOfAllStores);


//        13. Find the store names Where state = ND
        List<String> storeNames =  response.extract().path("data.findAll{it.state == 'ND'}.services.name");
        System.out.println("Store names : " + storeNames);

//        14. Find the Total number of services for the store where store name = Rochester
        List <List<Integer>> totalServices =  response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("Total number of services : " +totalServices.get(0).size());

//        15. Find the createdAt for all services whose name = “Windows Store”
        List<String> createdAtForAllServices = response.extract().path("data.find{it.services}.services.findAll{it.name =='Windows Store'}.storeservices.createdAt");
        System.out.println("Created At For all services : " + createdAtForAllServices);

//        16. Find the name of all services Where store name = “Fargo”
        List<String> nameOfAllServices =  response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("Name of all services : " + nameOfAllServices);


//        17. Find the zip of all the store
        List<String> zipOfAllStores = response.extract().path("data.zip");
        System.out.println("Zip of all stores : " +zipOfAllStores);

//        18. Find the zip of store name = Roseville
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("Zip of the store Roseville is :" + zip);

//        19. Find the storeservices details of the service name = Magnolia Home Theater
        List<String> storeServicesDetails = response.extract().path("data.find{it.services}.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("Store Services details : " + storeServicesDetails);


//        20. Find the lat of all the stores
        List<Double> latOfAllStores = response.extract().path("data.lat");
        System.out.println("Lat of all stores : " +latOfAllStores);
    }
}
