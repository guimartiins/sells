package io.github.guimartiins.sells;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Development
public class SellsConfiguration {

    @Bean
    public String applicationName() {
        return "Sells system";
    }
}
