package com.oscar.web.WebVertx.ihandlerouter;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public interface HandleRouter {


  void sendError(int statusCode, HttpServerResponse response);

  void initialData();

  void addUser(JsonObject user);

  public void handlePostUser(RoutingContext routingContext);

  void allUsers( RoutingContext routingContext );

  void handleGetUser(RoutingContext routingContext);

  void handleAddUser(RoutingContext routingContext);

  void handleDeleteUser(RoutingContext routingContext);


}
