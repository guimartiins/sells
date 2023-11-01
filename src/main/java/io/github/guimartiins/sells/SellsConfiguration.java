package io.github.guimartiins.sells;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SellsConfiguration {

    @Bean
    public String applicationName() {
        return "Sells system";
    }
}
