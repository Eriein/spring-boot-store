package com.codewithmosh.store.products;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> getAllProducts(Byte categoryId) {
        List<Product> products;
        if(categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        }else {
            products = productRepository.findAllWithCategory();
        }
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductDto getProduct(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException();
        }
        return productMapper.toDto(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null) {
            throw new CategoryNotFoundException();
        }
        var product = productMapper.toEntity(productDto);
        product.setId(productDto.getId());
        product.setCategory(category);

        productRepository.save(product);
        return productMapper.toDto(product);
    }
}
