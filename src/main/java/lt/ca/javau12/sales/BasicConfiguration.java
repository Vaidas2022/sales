package lt.ca.javau12.sales;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lt.ca.javau12.sales.entities.Goods;
import lt.ca.javau12.sales.repositories.GoodsRepository;

@Configuration
public class BasicConfiguration {

	
	@Bean
	CommandLineRunner seedGoods(GoodsRepository repo) {
		return args -> {
			if( repo.count() == 0) {
			}
			
		};
	}
	
	
}
