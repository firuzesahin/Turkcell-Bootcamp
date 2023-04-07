package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
//repository.getAll().size() == 0 yerine listenin boş olup olmadığını kontrol ettik.

@Service //birçok newleme yerine tek newleme ile birçok referans kullanılır. Servis olduğu bilgisini veriyoruz.
@AllArgsConstructor
public class BrandManager implements BrandService
{
    private final BrandRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllBrandsResponse> getAll()
    {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand -> mapper.map(brand,GetAllBrandsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        // if (!repository.existsById(id)) throw new IllegalArgumentException("Böyle bir marka bulunmuyor");
        // ctrl alt m ile metoda çıkarabiliriz

        checkIfBrandExistsById(id);
        //return repository.findById(id).get(); varsa bul birini getir
        //return repository.findById(id).orElseThrow(); //varsa bul getir yoksa hata fırlat

        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand, GetBrandResponse.class);
        //mapper ile dönüşüm yapıyoruz. brand nesnesinin class'ını değiştirdik.

        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request)
    {
        checkIfBrandExistsByName(request.getName());

        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        Brand createdBrand = repository.save(brand);
        CreateBrandResponse response = mapper.map(createdBrand, CreateBrandResponse.class);

        return response;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        checkIfBrandExistsById(id);

        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);

        UpdateBrandResponse response = mapper.map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfBrandExistsById(id);
        repository.deleteById(id);
    }

    //Business rules

    private void checkIfBrandExistsById(int id)
    {
        //olup olmadığını soruyoruz
        if (!repository.existsById(id)) throw new RuntimeException("Böyle bir marka bulunmuyor");
    }

    private void checkIfBrandExistsByName(String name)
    {
        //ctrl alt m ile metot oluşturabiliyoruz
        if(repository.existsByNameIgnoreCase(name))
        {
            throw new RuntimeException("Böyle bir marka sistemde kayıtlı!");
        }
    }
}