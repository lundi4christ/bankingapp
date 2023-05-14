package security.app.secure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureApplication {


	public static void main(String[] args)
	{
		SpringApplication.run(SecureApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(UserService userService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
			File file = new File("D:\\monday-files\\createuser.json");
			//InputStream inputStream = TypeReference.class.getResourceAsStream("D:\\monday-files\\createuser.json");
			try {
				User datauser = mapper.readValue(file,User.class);
				System.out.println(datauser);
				userService.saveUser(datauser);
				System.out.println("===========Users Saved! ***********");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}*/

}
