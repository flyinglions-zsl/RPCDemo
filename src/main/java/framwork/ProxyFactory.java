package framwork;

import protocol.http.HttpClient;
import provider.api.HelloService;
import register.Register;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    public static <T> T getProxy(final Class interfaceClass){

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient();
                Invocation invocation = new Invocation(HelloService.class.getName(),
                        "sayHello",new Object[]{"rpcTest"},new Class[]{String.class});

                URL url = Register.Random(interfaceClass.getName());
                return httpClient.post(url.getHostname(),url.getPort(),invocation);
            }
        });
    }
}
