package stepdefinitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.*;
import org.json.simple.JSONObject;

public class Products {
    public RequestSpecification httpuReqest;
    public Response response;
    public int ResponseCode;
    public int StatusCode;
    public ResponseBody body ;
    public String s;

    public  JSONObject requestParams;

    @Given("I hit the url of get products api endpoint")
    public void I_hit_the_url_of_get_products_api_endpoint()
    {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of products in  the request")
    public void I_pass_the_url_of_products_in_the_request()
    {
        httpuReqest = RestAssured.given();
        response = httpuReqest.get("products");
    }

    @Then("I received the response code as {int}")
    public void I_received_the_response_code_as(Integer int1)
    {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode,200);

    }

    @Then("I verify that the rate of the first product is {}")
    public void I_verify_that_the_rate_of_the_first_product_is_(String rate)
    {
        body = response.getBody();
        // convert response body to string
        String responseBody = body.asString();

        //JSON Representation from Response Body
        JsonPath jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("rating[0].rate").toString();

        assertEquals(rate, s);


    }

    @Given("I hit the url of post  product api endpoint")
    public void iHitTheUrlOfPostProductApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpuReqest=RestAssured.given();
        requestParams = new JSONObject();





    }

    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitle(String title)
    {
        requestParams.put("title",title);
        requestParams.put("price","13.5");
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");
        httpuReqest.body(requestParams.toJSONString());
        Response response = httpuReqest.post("products");
        ResponseBody body = response.getBody();
        JsonPath jsnpath = response.jsonPath();
        s= jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

    @Then("I received the response body with id as {}")
    public void iReceivedTheResponseBodyWithIdAs(String id)
    {
        assertEquals(id,s);
    }

    @Given("I hit the url of put  product api endpoint")
    public void iHitTheUrlOfPutProductApiEndpoint()
    {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();

    }

    @When("I pass the url of products in  the request with {}")
    public void iPassTheUrlOfProductsInTheRequestWith(String productnumber) {
        httpuReqest = RestAssured.given();
        requestParams.put("title","test product");
        requestParams.put("price","13.5");
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");

        httpuReqest.body(requestParams.toJSONString());
        response = httpuReqest.put("products/"+productnumber);
        ResponseBody body = response.getBody();
        JsonPath jsnpath = response.jsonPath();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }


    @Given("I hit the url of delete  product api endpoint")
    public void iHitTheUrlOfDeleteProductApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();

    }

    @When("I pass the url of delete products in  the request with {}")
    public void iPassTheUrlOfDeleteProductsInTheRequestWith(String productnumber) {

        httpuReqest = RestAssured.given();
        requestParams.put("title","test product");
        requestParams.put("price","13.5");
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");

        httpuReqest.body(requestParams.toJSONString());
        response = httpuReqest.delete("products/"+productnumber);
        ResponseBody body = response.getBody();
        JsonPath jsnpath = response.jsonPath();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }
}
