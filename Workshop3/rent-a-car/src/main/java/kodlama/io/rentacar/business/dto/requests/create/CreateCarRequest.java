package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest
{
    private int modelId;
    private int modelYear;
    private String plate;
    private double dailyPrice;
}