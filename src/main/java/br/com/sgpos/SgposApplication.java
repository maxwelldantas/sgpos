package br.com.sgpos;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.sgpos.model.Estoque;
import br.com.sgpos.repository.EstoqueRepository;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class })
public class SgposApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgposApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(EstoqueRepository repository) {
        return args -> {
            repository.deleteAll();
            LongStream.range(1, 11)
                    .mapToObj(i -> {
                        Estoque e = new Estoque();
                        e.setProduto("Produto " + i);
                        e.setDescricao("Descrição" + i);
                        return e;
                    })
                    .map(v -> repository.save(v))
                    .forEach(System.out::println);
        };
    }

}
