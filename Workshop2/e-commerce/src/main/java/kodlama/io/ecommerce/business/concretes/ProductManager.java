package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductManager implements ProductService
{
    private final ProductRepository repository;

    //@Autowired işlemi yapılıyor, yazadabiliriz.
    public ProductManager(ProductRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll()
    {
        return repository.findAll();
    }

    @Override
    public Product getById(int id)
    {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Product add(Product product)
    {
        validateProduct(product);
        return repository.save(product);
    }

    @Override
    public Product update(int id, Product product)
    {
        validateProduct(product);
        product.setId(id); //nesnenin id'sini değiştirmek için
        return repository.save(product);
    }

    @Override
    public void delete(int id)
    {
        repository.deleteById(id);
    }

    //! Buisness rules

    private void validateProduct(Product product)
    {
        checkIfUnitPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }
    private void checkIfUnitPriceValid(Product product)
    {
        if(product.getUnitPrice() <= 0)
        {
            //runtime ile aynı çalıştığı için kullandık.
            throw new IllegalArgumentException("Price cannot be less than or equal to zero.");
        }
    }

    private void checkIfQuantityValid(Product product)
    {
        if(product.getQuantity() < 0 )
            throw new IllegalArgumentException("Quantity cannot be less than zero");
    }

    private void checkIfDescriptionLengthValid(Product product) {
        if(product.getDescription().length() < 10 || product.getDescription().length() > 50)
            throw new IllegalArgumentException("Description length must be between 10 and 50 characters.");
    }
}