package register;

import framwork.URL;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Register {

    private static Map<String,Map<URL,Class>> REGISTER = new HashMap<String, Map<URL, Class>>();

    public static void register(URL url,String interfaceName,Class implClass){

        Map<URL,Class> map = new HashMap<URL, Class>();
        map.put(url,implClass);
        REGISTER.put(interfaceName,map);

        saveFile();
    }

    public static Class getClass(URL url,String interfaceName){
        REGISTER = getFile();
        return REGISTER.get(interfaceName).get(url);
    }


    //负载均衡(随机模式)
    public static URL Random(String interfaceName){
        REGISTER = getFile();
        return (URL) REGISTER.get(interfaceName).keySet().iterator().next();
    }

    public static void saveFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("d://test.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(REGISTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,Map<URL,Class>> getFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream(("d://test.txt"));
            ObjectInputStream ooi = new ObjectInputStream(fileInputStream);
            return (Map<String, Map<URL, Class>>) ooi.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
