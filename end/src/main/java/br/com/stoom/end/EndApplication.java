package br.com.stoom.end;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(EnderecoRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
			.mapToObj(i -> {
				Endereco e = new Endereco();
				e.setStreetName("Geraldo Martins");
				e.setNumber((int) i);
				e.setComplement("");
				e.setNeighbourhood("Icarai");
				e.setCity("Niteroi");
				e.setState("RJ");
				e.setCountry("Brasil");
				e.setZipcode(24220380);
				return e;
			}).map(v -> repository.save(v))
			.forEach(System.out::println);
		};
	}

}
