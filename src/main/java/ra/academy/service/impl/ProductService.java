package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.academy.dao.IProductDao;
import ra.academy.dto.request.ProductRequest;
import ra.academy.model.Product;
import ra.academy.service.IProductService;
import ra.academy.service.UploadService;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private UploadService uploadService;
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void save(ProductRequest productRequest) {
        String imageUrl = null;
        if (!productRequest.getImage().isEmpty()){
            // xử lí upload file
            imageUrl = uploadService.uploadFile(productRequest.getImage());
        }
        // chuyển đỏi từ product request thành Product
        Product p = new Product(productRequest.getName(), imageUrl, productRequest.getPrice(), productRequest.getDescription(), productRequest.getStock());
        productDao.save(p);

    }

    @Override
    public void update(Product product, MultipartFile file) {
        if (!file.isEmpty()){
            // xử lí upload file
           String imageUrl = uploadService.uploadFile(file);
           product.setImageUrl(imageUrl);
        }
        // chuyển đỏi từ product request thành Product
        productDao.save(product);
    }

    @Override
    public void delete(Long id) {
        productDao.deleteById(id);
    }
}
