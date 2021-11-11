package com.oscar.web.WebVertx;

import com.oscar.web.WebVertx.implehandleserviceuser.ImpleHandleUserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {
  private ImpleHandleUserService impleHandleUserService = new ImpleHandleUserService();
  private static final int PORT = 8888;


  public static void main(String[] args) {
    Vertx vertxx = Vertx.vertx();
    vertxx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    //Inicializa los valores de los usuarios
    this.impleHandleUserService.initialData();

    //Router
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    //GetHome
    router.get("/").handler(a -> {
      a.response()
        .putHeader("content-type", "text/html")
        .end("<a href=\"http://localhost:8888/usuario\">List User</a>");
    });


    //GetList
    router.get("/usuario").handler(context -> {
      this.impleHandleUserService.allUsers(context);
    });


    //GetUser
    router.get("/usuario/:id").handler(context -> {
      this.impleHandleUserService.handleGetUser(context);
    });

    //PutUser
    router.put("/usuario/:id").handler(context -> {
      this.impleHandleUserService.handleAddUser(context);
    });

    //Post
    router.post("/usuario").handler(context -> {
      impleHandleUserService.handlePostUser( context);
    });

    //DeleteUser
    router.delete("/usuario/:id").handler(context -> {
      impleHandleUserService.handleDeleteUser(context);
    });

    //Server
    vertx.createHttpServer().requestHandler(router).listen(this.PORT);
    System.out.println("ON");

  }
}
