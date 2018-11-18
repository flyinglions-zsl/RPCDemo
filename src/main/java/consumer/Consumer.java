package consumer;

import framwork.ProxyFactory;
import provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("RPCTEST");
        System.out.println(result);
    }
}
