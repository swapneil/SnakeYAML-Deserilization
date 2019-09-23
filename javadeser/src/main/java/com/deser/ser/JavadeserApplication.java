package com.deser.ser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavadeserApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(JavadeserApplication.class, args);
	}

	@RequestMapping(value="/uploadSer",method=RequestMethod.POST,produces="application/json")
	public Object ParseYaml(@RequestParam(value="serdata") String serdata) throws Exception
	{
		
		MyObject myObj = new MyObject();
		myObj.name = serdata;
		FileOutputStream fos = new FileOutputStream("object.ser");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(myObj);
        os.close();
 
        //Read the serialized data back in from the file "object.ser"
        FileInputStream fis = new FileInputStream("object.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
 
        //Read the object from the data stream, and convert it back to a String
        MyObject objectFromDisk = (MyObject)ois.readObject();
 
        //Print the result.
        System.out.println(objectFromDisk.name);
       
		ois.close();
		return objectFromDisk.name;
		
		
		
	}
}
class MyObject implements Serializable{
    public String name;
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        Runtime.getRuntime().exec(this.name);
    }
}