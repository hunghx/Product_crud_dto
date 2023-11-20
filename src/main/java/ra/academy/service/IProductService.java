package ra.academy.service;

import org.springframework.web.multipart.MultipartFile;
import ra.academy.dto.request.ProductRequest;
import ra.academy.model.Product;

public interface IProductService extends IGenericService<Product, ProductRequest,Long> {
     void update(Product product , MultipartFile file);
}
