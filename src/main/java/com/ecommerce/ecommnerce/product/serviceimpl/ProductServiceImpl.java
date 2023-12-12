package com.ecommerce.ecommnerce.product.serviceimpl;

import com.ecommerce.ecommnerce.category.entity.Category;
import com.ecommerce.ecommnerce.category.repository.CategoryRepository;
import com.ecommerce.ecommnerce.common.enums.ExceptionMessage;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.product.dto.request.ProductAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.SubProductRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.ProductResponseDto;
import com.ecommerce.ecommnerce.product.dto.response.SubProductResponseDto;
import com.ecommerce.ecommnerce.product.dtoconverter.ProductDtoConverter;
import com.ecommerce.ecommnerce.product.entity.Product;
import com.ecommerce.ecommnerce.product.entity.SubProduct;
import com.ecommerce.ecommnerce.product.repository.ProductRepository;
import com.ecommerce.ecommnerce.product.repository.SubProductRepository;
import com.ecommerce.ecommnerce.product.service.ProductService;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.ErrorManager;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubProductRepository subProductRepository;

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductAddRequestDto requestDto) {

        Product product = convertProductRequestDtoToEntity(requestDto);

        Category categoryFromDB = getCategoryFromDB(requestDto.getCategoryId());

        product.setCategory(categoryFromDB);

        Product savedProduct = productRepository.save(product);

        return convertProductEntityToResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {

        Product productFromDB = getProductFromDB(productId);

        ProductResponseDto productResponseDto = convertProductEntityToResponseDto(productFromDB);

        return productResponseDto;
    }


    private Product convertProductRequestDtoToEntity(ProductAddRequestDto requestDto){
      return   ProductDtoConverter.convertProductRequestDtoToEntity(requestDto);
    }

    private Category getCategoryFromDB(Long categoryId){
       return categoryRepository.findByIdAndStatus(categoryId, Status.ACTIVE.getValue()).orElseThrow(()->new ServiceException(ExceptionMessage.CATEGORY_NOT_FOUND.getMessage()));
    }

    private ProductResponseDto convertProductEntityToResponseDto(Product product){
        return ProductDtoConverter.convertProductEntityToProductResponseDto(product);
    }

    private Product getProductFromDB(Long id){
       return productRepository.findProductByIdAndStatus(id,Status.ACTIVE.getValue()).orElseThrow(()-> new ServiceException(ExceptionMessage.INVALID_PRODUCT_ID.getMessage()));
    }

    private SubProduct getSubProductFromDB(Long id){
        return subProductRepository.findById(id).orElseThrow(()->new ServiceException(ExceptionMessage.INVALID_SUB_PRODUCT_ID.getMessage()));
    }

}
