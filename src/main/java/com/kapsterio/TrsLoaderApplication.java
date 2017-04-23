package com.kapsterio;

import com.kapsterio.mapper.PatentMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.nio.file.Paths;

@SpringBootApplication
public class TrsLoaderApplication implements CommandLineRunner{
    private final PatentMapper mapper;

	public TrsLoaderApplication(PatentMapper mapper) {
		this.mapper = mapper;
	}

	public static void main(String[] args) throws Exception{
		ApplicationContext context = SpringApplication.run(TrsLoaderApplication.class, args);
        DataLoader loader = (DataLoader)context.getBean(DataLoader.class);

        if (args.length == 0) {
            System.out.println("input TRS file as arg list!");
            System.exit(-1);
        } else {
            for (int i = 0; i < args.length; i++) {
                loader.loadFileToMysql(Paths.get(args[i]));
            }
        }
        //loader.loadFileToMysql(Paths.get("/Users/bj-m-206255a/Documents/test.TRS"));
        //System.out.println("...........................");
        loader.shutDownAndWait();

    }

	@Override
	public void run(String... args) throws Exception {
        //this.mapper.createPatentTableIfNotExist();
	}
}
