package ra.academy.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {
    private String name;
    private MultipartFile image;
    private double price;
    private String description;
    private int stock;

    public ProductRequest() {
    }

    public ProductRequest(String name, MultipartFile image, double price, String description, int stock) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
