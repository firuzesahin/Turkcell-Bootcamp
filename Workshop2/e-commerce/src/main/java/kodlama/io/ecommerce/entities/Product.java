package kodlama.io.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kodlama.io.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product //sistemde ürünler tutulmalıdır. entities > concretes
{
    //id, name, quantity, unitPrice, description özellikleri olacak.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;

    //enum tipini belirterek, isminin görünmesini sağlıyoruz. .ordinal sayısal şekilde .string değer karşılığını görüntüler.
    @Enumerated(EnumType.STRING)
    private Status status;

    //parent hangisiyse jointable onda tanımlanır.
      /*@JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    ) //jointable kullanmasak da olur */
    @ManyToMany(fetch = FetchType.LAZY) //ManytoMany'de, fetch özelliği ile iki sınıf ilişkisi kurduğumuzda; eager, ne zaman bu sınıfa sorgu atsak diğer sınıfa da aratma yapar. lazy tek sınıf sorgusu sağlar.

    @JsonManagedReference //bu anotasyon sonsuz döngüyü engeller. json dönüşümünü düzgün hale getirir.
    private Set<Category> categories = new HashSet<>();
    //set olarak kullanma sebebimiz, manytomany için oluşturduğumuz idler'de tekrar olmaması adına listeyi tercih etmedik.
}