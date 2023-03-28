package com.kharitonov.personnel;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonnelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelApplication.class, args);
	}

}
