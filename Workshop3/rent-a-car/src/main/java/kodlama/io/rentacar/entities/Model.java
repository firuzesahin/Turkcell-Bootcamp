package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "models")
public class Model
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id") //tabloyu bağlamak için yazılır.
    private Brand brand; //bir markaya ait birden fazla model olabilir.

    @OneToMany(mappedBy = "model")//bir modelin, birden çok arabası olabilir.
    private List<Car> cars;
}