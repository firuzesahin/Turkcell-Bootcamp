package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//sen bir controller'sın @RestController
//localhost'a geldiğimizde, port:8080/brands'e erişimi gösterir @RequestMapping("/brands")
@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController
{
    private final BrandService service;
    //@Autowired //brandService'in somutuna giderek bağlama işlemi yapacak, constructor yazsaydık.

    //get işlemi için @GetMapping yapılır. localhost:8080/brands/getAll ile bu metot çağırılır.
    @GetMapping()
    public List<GetAllBrandsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetBrandResponse /*Brand*/ getById(@PathVariable int id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    {
        service.delete(id);
    }
}