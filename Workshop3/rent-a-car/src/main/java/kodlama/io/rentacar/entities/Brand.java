package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//lombok
@Entity //brand'ı bir veritabanı nesnesi yapıyoruz.
@Setter
@Getter
@NoArgsConstructor //parametresiz constructor
@AllArgsConstructor //parametreli constructor
@Table(name = "brands") //brands'ları tablo haline getiriyoruz
public class Brand
{
    @Id //Primary Key -> PK
    //id bilgisinin otomatik oluşarak artmasını sağlayalım
    //stratejimizi girerek identity ile bir bir artmasını sağladık.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id")
    private int id;
    private String name;

    @OneToMany(mappedBy = "brand") //brand'e göre mapleme işlemi.
    private List<Model> models; //bir markanın birden çok modeli olabilir.
}