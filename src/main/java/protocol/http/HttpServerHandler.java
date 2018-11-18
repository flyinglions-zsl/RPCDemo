package protocol.http;

import framwork.Invocation;
import framwork.URL;
import org.apache.commons.io.IOUtils;
import register.Register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){

        try {
            InputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) ois.readObject();

            //找实现类
            String interfaceName = invocation.getInterfaceName();
            URL url = new URL("localhost",8080);
            Class implClass = Register.getClass(url,interfaceName);

            Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParamTypes());
            String result = (String) method.invoke(implClass.newInstance(),invocation.getParams());

            OutputStream outputStream = resp.getOutputStream();

            IOUtils.write(result,outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
