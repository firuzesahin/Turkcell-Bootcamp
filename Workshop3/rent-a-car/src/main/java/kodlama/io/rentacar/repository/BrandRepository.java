package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD operotions otomatik sağlanır.
//Brand fonksiyonları direkt gelir
public interface BrandRepository extends JpaRepository<Brand, Integer> //(dao-data access object = repository), brand deposu
{
    //arrayler'lerde boyut sabit olduğundan problem yaşanıyor. collection/list bu yüzden kullanılır.

    // Custom queries
    // aynı markadan iki tane olmamasını istiyoruz, boolean ile işlem yapacağız.

    boolean existsByNameIgnoreCase(String name); //ignore case ile büyük küçük harf duyarlılığını sağladık





}