package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //restcontroller kullanarak, dönüşün .json olacağını belirtmiş oluyoruz.
@RequestMapping("/api/products")
public class ProductsController
{
    private final ProductService service;

    public ProductsController(ProductService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) //gelen parametredeki değer olduğunu söylüyor.
    {
        return service.getById(id);
    }

    @GetMapping("/")
    public Product findById(@RequestParam int id) //birden fazla filtreleme olduğu zaman @RequestParam kullanılır.
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //farklı bir response status kullanacağız.
    public Product add(@RequestBody Product product)
    {
        return service.add(product);
    }

    @PutMapping("/{id}")
    public Product update(int id,@RequestBody Product product)
    {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    {
        service.delete(id);
    }
}