package kodlama.io.rentacar.configuration.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //bu anotasyon ile bean araması gerçekleşir.
public class ModelMapperConfig
{
    @Bean //metot olduğu için bean yazıyoruz.
    public ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }
}