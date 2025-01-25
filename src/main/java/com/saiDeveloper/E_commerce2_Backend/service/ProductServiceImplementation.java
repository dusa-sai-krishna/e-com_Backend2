package com.saiDeveloper.E_commerce2_Backend.service;
import com.saiDeveloper.E_commerce2_Backend.request.createProductRequest;
import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Category;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.repo.CategoryRepo;
import com.saiDeveloper.E_commerce2_Backend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {


    @Autowired
    private ProductRepo repo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Product createProduct(createProductRequest req) throws ProductException {

        Product isPresent= repo.isProductExist(req.getTitle(), req.getBrand(), req.getColor(),req.getThirdLavelCategory()).orElse(null);

        if (isPresent != null) {
            throw new ProductException("Product already exist");
        }

        Category topLevel = categoryRepo.findByName(req.getTopLavelCategory()).orElse(null);

        if (topLevel == null) {
            Category topLavelCategory = new Category();
            topLavelCategory.setName(req.getTopLavelCategory());
            topLavelCategory.setLevel(1);
            categoryRepo.save(topLavelCategory);

            topLevel = topLavelCategory;
        }

        Category secondLevel = categoryRepo.findByNameAndParentName(req.getSecondLavelCategory(), topLevel.getName()).orElse(null);

        if (secondLevel == null) {
            Category secondLavelCategory = new Category();
            secondLavelCategory.setName(req.getSecondLavelCategory());
            secondLavelCategory.setLevel(2);
            secondLavelCategory.setParentCategory(topLevel);
            categoryRepo.save(secondLavelCategory);

            secondLevel = secondLavelCategory;
        }


        Category thirdLevel = categoryRepo.findByNameAndParentName(req.getThirdLavelCategory(), secondLevel.getName()).orElse(null);

        if (thirdLevel == null) {
            Category thirdLavelCategory = new Category();
            thirdLavelCategory.setName(req.getThirdLavelCategory());
            thirdLavelCategory.setLevel(3);
            thirdLavelCategory.setParentCategory(secondLevel);
            categoryRepo.save(thirdLavelCategory);

            thirdLevel = thirdLavelCategory;
        }


        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setPrice(req.getPrice());
        product.setImageUrl(req.getImageUrl());
        product.setCategory(thirdLevel);
        product.setBrand(req.getBrand());
        product.setSize(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCreatedAt(LocalDateTime.now());
        product.setDiscountPercentage(req.getDiscountPercentage());
        return repo.save(product);
    }


    @Override
    public String deleteProduct(Long id) throws ProductException {
        Product product = findProductById(id); // if not found throws exception
        // delete the associated value objects
        product.getSize().clear();
        repo.delete(product);
        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductException {
        Product existingProduct = findProductById(id); // if not found throws exception

        //update quantity
        if (product.getQuantity() != 0) {
            existingProduct.setQuantity(product.getQuantity());
        }

        return repo.save(existingProduct);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {

        Product product = repo.findById(id).orElse(null);
        if (product != null) {
            return product;
        }
        throw new ProductException("Product not found with id:" + id);
    }



    /**
     * Get all products based on the given criteria.
     * <p>
     * The method can be used to fetch all the products based on the given criteria.
     * The criteria can be a combination of category, colors, sizes, min and max price, min discount, sort and stock.
     * <p>
     * The method first fetches all the products based on the given category, min and max price, min discount, and sort.
     * Then it filters the products based on the given colors and sizes.
     * Then it filters the products based on the given stock criteria.
     * <p>
     * The method then paginates the results based on the given page number and page size.
     * <p>
     * The method returns a page of products.
     *
     * @param category    the category of the products to fetch
     * @param colors      the colors of the products to fetch
     * @param sizes       the sizes of the products to fetch
     * @param minPrice    the minimum price of the products to fetch
     * @param maxPrice    the maximum price of the products to fetch
     * @param minDiscount the minimum discount of the products to fetch
     * @param sort        the sort criteria of the products to fetch
     * @param stock       the stock criteria of the products to fetch
     * @param pageNumber  the page number of the products to fetch
     * @param pageSize    the page size of the products to fetch
     * @return a page of products
     * @throws ProductException if any error occurs
     */
    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, int minPrice, int maxPrice, int minDiscount, String sort, String stock, int pageNumber, int pageSize) throws ProductException {

        Pageable pageable = PageRequest.of(pageNumber, pageSize); // used for paginated results

        List<Product> products = repo.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products = products.stream()
                    .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .toList();

        }
        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream()
                        .filter(p -> p.getQuantity() > 0)
                        .toList();
            } else if (stock.equals("out_of_stock")) {
                products = products.stream()
                        .filter(p -> p.getQuantity() == 0)
                        .toList();
            }
        }

        //if current page Number is 2 and pageSize is 10 then startIndex will be 10
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

        return filteredProducts;
    }



}



















