package kodlama.io.ecommerce.repository.concretes;

import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository //ProductRepository'i service katmanında, tekrar tekrar new'lememek için.
public class InMemoryProductRepository implements ProductRepository
{
    //InMemory'de çalıştığımız için liste oluştururuz. Tüm metotlarda kullanmak için burada tanımlıyoruz.
    List<Product> products;

    public InMemoryProductRepository()
    {
        products = new ArrayList<>();
        products.add(new Product(1, "IPhone 14", 10, 30000.99,"apple's product"));
        products.add(new Product(2, "Monster", 10, 27000,"apple's product"));
        products.add(new Product(3, "XBox", 5, 25000,"microsoft's product"));
        products.add(new Product(4, "Dyson v15", 10, 14999.99,"dyson's product"));
    }

    @Override
    public List<Product> getAll()
    {
        return products;
    }

    @Override
    public Product getById(int id)
    {
        //.get liste fonksiyonu ile, listenin elemanlarının index aramasını yapabiliriz.
        return products.get(id-1);
    }

    @Override
    public Product add(Product product)
    {
        products.add(product);
        return product;
    }

    @Override
    public Product update(int id, Product product)
    {
        //.set list fonksiyonu ile, verilen id'deki nesneyi; verilen nesne ile değiştirir.
        return products.set(id-1, product);
    }

    @Override
    public void delete(int id)
    {
        products.remove(id);
    }
}
