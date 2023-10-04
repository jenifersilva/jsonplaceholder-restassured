package DataFactory;

import pojo.ObjectPojo;

public class ObjectDataFactory {
    public static ObjectPojo createNewObject(String title, String body){
        ObjectPojo object = new ObjectPojo();
        object.setTitle(title);
        object.setBody(body);

        return object;
    }
}
