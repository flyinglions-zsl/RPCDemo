package provider;

import framwork.URL;
import protocol.http.HTTPServer;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.Register;

public class Provider {

    public static void main(String[] args) {
        //注册服务
        URL url = new URL("localhost",8080);
        Register.register(url, HelloService.class.getName(), HelloServiceImpl.class);

        //启动tomcat
        HTTPServer httpServer = new HTTPServer();
        httpServer.start(url.getHostname(),url.getPort());
    }
}
