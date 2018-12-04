package com.zomato.generic;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UtilityManager 
{
	public static String responseBodyFromGetRequest(String header_key,String header_value,String uriAppend)
	{
		Response resp = given().headers(header_key, header_value).when().contentType(ContentType.JSON).get(URIBuilder.uri+uriAppend);
		String body=resp.getBody().asString();
		return body;
	}
	public static int responseStatusFromGetRequest(String header_key,String header_value,String uriAppend)
	{
		Response resp = given().headers(header_key, header_value).when().contentType(ContentType.JSON).get(URIBuilder.uri+uriAppend);
		int status=resp.getStatusCode();
		return status;
	}

}
