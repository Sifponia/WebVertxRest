package com.oscar.web.WebVertx.implehandleserviceuser;

import com.oscar.web.WebVertx.ihandlerouter.HandleRouter;
import com.oscar.web.WebVertx.model.User;
import io.netty.handler.codec.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;


public class ImpleHandleUserService implements HandleRouter {

  private Map<String, JsonObject> userMap = new HashMap<>();


  @Override
  public void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }

  @Override
  public void initialData() {

    User user = new User();
    user.setIdUsuario(1);
    user.setName("Evelyn");
    user.setPass("zsascsds.ew.43434");

    User userTwo = new User();
    userTwo.setIdUsuario(2);
    userTwo.setName("Marc");
    userTwo.setPass("z5454s.ew.43434");

    User userThree = new User();
    userThree.setIdUsuario(3);
    userThree.setName("Joan");
    userThree.setPass("s4344ew.43434");

    User userFour = new User();
    userFour.setIdUsuario(4);
    userFour.setName("Maria");
    userFour.setPass("Mariaew.43434");

    addUser(new JsonObject().mapFrom(user));
    addUser(new JsonObject().mapFrom(userTwo));
    addUser(new JsonObject().mapFrom(userThree));
    addUser(new JsonObject().mapFrom(userFour));

  }

  @Override
  public void addUser(JsonObject user) {
    userMap.put(user.getString("idUsuario"), user);
  }

  @Override
  public void allUsers(RoutingContext routingContext) {
    JsonArray arr = new JsonArray();
    userMap.forEach((k, v) -> arr.add(v));
    routingContext
      .response()
      .putHeader("content-type", "application/json")
      .end(arr.encodePrettily());


  }

  @Override
  public void handleGetUser(RoutingContext routingContext) {
    String productID = routingContext.request().getParam("id");
    HttpServerResponse response = routingContext.response();
    if (productID == null) {
      sendError(400, response);
    } else {
      JsonObject product = userMap.get(productID);
      if (product == null) {
        sendError(404, response);
      } else {
        response.putHeader("content-type", "application/json").end(product.encodePrettily());
      }
    }


  }

  @Override
  public void handleAddUser(RoutingContext routingContext) {
    String productID = routingContext.request().getParam("id");
    HttpServerResponse response = routingContext.response();
    if (productID == null) {
      sendError(400, response);
    } else {
      JsonObject product = routingContext.getBodyAsJson();
      if (product == null) {
        sendError(400, response);
      } else {
        userMap.put(productID, product);
        response.end();
      }
    }

  }

  @Override
  public void handleDeleteUser(RoutingContext routingContext) {
    String userId = routingContext.request().getParam("id");
    HttpServerResponse response = routingContext.response();
    if (userId == null) {
      sendError(400, response);
    } else {
      JsonObject product = userMap.remove(userId);

      if (product == null) {
        sendError(404, response);
      } else {
        response.putHeader("content-type", "application/json").end(product.encodePrettily());
      }
    }

  }

  @Override
  public void handlePostUser(RoutingContext routingContext) {
    final User user = Json.decodeValue(routingContext.getBodyAsString(),
      User.class);

    addUser(JsonObject.mapFrom(user));
    //userMap.put("idUser", JsonObject.mapFrom(user));
    routingContext.response()
      .setStatusCode(201)
      .putHeader("content-type", "application/json; charset=utf-8")
      .end(Json.encodePrettily(user));


  }
}
