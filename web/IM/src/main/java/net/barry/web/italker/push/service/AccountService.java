package net.barry.web.italker.push.service;

import net.barry.web.italker.push.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//127.0.0.1/api/account/...
@Path("/account")
public class AccountService {

    //GET   127.0.0.1/api/account/login
    @GET
    @Path("/login")
    public  String get()
    {
        return "you get the login";
    }
    //POST   127.0.0.1/api/account/login
    @POST
    @Path("/login")
    //指定请求相应体为JSON格式
    @Consumes(MediaType.APPLICATION_JSON)
    //指定返回相应体为输出JSON格式
    @Produces(MediaType.APPLICATION_JSON)
    public User post()
    {
        User user=new User();
        user.setName("barry");
        user.setSex(2);
        return user;
    }

}
