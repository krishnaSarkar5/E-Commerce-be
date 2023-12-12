package com.ecommerce.ecommnerce.product.dtoconverter;

import com.ecommerce.ecommnerce.common.enums.CurrencyEnum;
import com.ecommerce.ecommnerce.common.enums.ExceptionMessage;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.product.dto.response.AttributeResponseDto;
import com.ecommerce.ecommnerce.product.dto.response.ProductResponseDto;
import com.ecommerce.ecommnerce.product.dto.response.SubProductResponseDto;
import com.ecommerce.ecommnerce.product.enums.AttributeEnum;
import com.ecommerce.ecommnerce.product.dto.request.AttributeRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.ProductAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.SubProductRequestDto;
import com.ecommerce.ecommnerce.product.entity.Attribute;
import com.ecommerce.ecommnerce.product.entity.Product;
import com.ecommerce.ecommnerce.product.entity.SubProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductDtoConverter {


    public static List<Attribute> convertAttributeRequestDtoListToEntityList(List<AttributeRequestDto> attributeRequestDtoList,Product product,SubProduct subProduct){

        List<Attribute> attributeList = new ArrayList<>();



        for(AttributeRequestDto attributeRequestDto : attributeRequestDtoList){

            try {
                AttributeEnum attributeEnum = AttributeEnum.valueOf(attributeRequestDto.getAttributeName().trim().toUpperCase());

                Attribute attribute = Attribute
                        .builder()
                        .name(attributeEnum.getValue())
                        .value(attributeRequestDto.getAttributeValue())
                        .product(product)
                        .subProduct(subProduct)
                        .status(Status.ACTIVE.getValue())
                        .build();
                attributeList.add(attribute);
            }catch (Exception e){
                throw  new ServiceException(ExceptionMessage.INVALID_ATTRIBUTE.getMessage());
            }



        }

       return attributeList;
    }




    public  static List<SubProduct> convertSubProductDtoToEntity(List<SubProductRequestDto> requestDtoList,Product product){

        List<SubProduct> subProductList = new ArrayList<>();

        try {

            for(SubProductRequestDto subProductRequestDto : requestDtoList){

                CurrencyEnum currencyEnum = CurrencyEnum.valueOf(subProductRequestDto.getCurrency().trim().toUpperCase());

                SubProduct subProduct = SubProduct
                        .builder()
                        .price(subProductRequestDto.getPrice())
                        .currency(currencyEnum.getValue())
                        .discount(subProductRequestDto.getDiscount())
                        .stock(subProductRequestDto.getStock())
                        .imageUrl(subProductRequestDto.getImage())
                        .status(Status.ACTIVE.getValue())
//                        .attributes(attributeList)
                        .product(product)
                        .build();



                List<Attribute> attributeList = convertAttributeRequestDtoListToEntityList(subProductRequestDto.getAttributeRequestDtos(),product,subProduct);

                subProduct.setAttributes(attributeList);

                subProductList.add(subProduct);
            }


        }catch (Exception e){
            throw new ServiceException(ExceptionMessage.INVALID_CURRENCY.getMessage());
        }


        return subProductList;


    }

    public static Product convertProductRequestDtoToEntity(ProductAddRequestDto productAddRequestDto){

        Product product = Product
                .builder()
                .title(productAddRequestDto.getTitle())
                .description(productAddRequestDto.getDescription())
                .brand(productAddRequestDto.getBrand())
                .status(Status.ACTIVE.getValue())
//                .subProducts(subProductList)
                .build();


        List<SubProduct> subProductList = convertSubProductDtoToEntity(productAddRequestDto.getSubProductList(),product);


        product.setSubProducts(subProductList);

        return product;
    }


    public static ProductResponseDto convertProductEntityToProductResponseDto(Product product){


        List<SubProductResponseDto> subProductResponseDtoList = convertSubProductEntityToResponseDto(product.getSubProducts());

        ProductResponseDto productResponseDto = ProductResponseDto
                .builder()
                .id(product.getId())
                .brand(product.getBrand())
                .description(product.getDescription())
                .title(product.getTitle())
                .categoryId(product.getCategory().getId())
                .subProductResponseDtoList(subProductResponseDtoList)
                .build();


        return productResponseDto;
    }

    public  static List<SubProductResponseDto> convertSubProductEntityToResponseDto(List<SubProduct> subProductList){

        List<SubProductResponseDto> subProductResponseDtoList = new ArrayList<>();


        for(SubProduct subProduct : subProductList){

            List<AttributeResponseDto> attributeResponseDtoList = convertAttributeEntityToResponseDto(subProduct.getAttributes());

            SubProductResponseDto subProductResponseDto = SubProductResponseDto
                    .builder()
                    .price(subProduct.getPrice())
                    .discount(subProduct.getDiscount())
                    .currency(subProduct.getCurrency())
                    .stock(subProduct.getStock())
                    .attributeResponseDtoList(attributeResponseDtoList)
                    .build();

            subProductResponseDtoList.add(subProductResponseDto);
        }

        return subProductResponseDtoList;
    }



    public static List<AttributeResponseDto> convertAttributeEntityToResponseDto(List<Attribute> attributeList){

        List<AttributeResponseDto> attributeResponseDtoList = new ArrayList<>();

        for(Attribute attribute:attributeList){

            AttributeResponseDto attributeResponseDto = AttributeResponseDto
                    .builder()
                    .attributeName(attribute.getName())
                    .attributeValue(attribute.getValue())
                    .build();

            attributeResponseDtoList.add(attributeResponseDto);
        }

        return attributeResponseDtoList;
    }

}
