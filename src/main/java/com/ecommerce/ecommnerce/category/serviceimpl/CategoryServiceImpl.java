package com.ecommerce.ecommnerce.category.serviceimpl;

import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.request.CategoryAttributeRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;
import com.ecommerce.ecommnerce.category.dtoconverter.CategoryDtoConverter;
import com.ecommerce.ecommnerce.category.entity.Category;
import com.ecommerce.ecommnerce.category.entity.CategoryAttribute;
import com.ecommerce.ecommnerce.category.repository.CategoryRepository;
import com.ecommerce.ecommnerce.category.service.CategoryService;
import com.ecommerce.ecommnerce.common.enums.ExceptionMessage;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryAddRequestDto categoryAddRequestDto) {



        Category category = CategoryDtoConverter.convertCategoryAddRequestDtoToEntity(categoryAddRequestDto);

        Optional<List<CategoryAttribute>> categoryAttributeOptional = getCategoryAttribute(categoryAddRequestDto.getSearchFields(),category);

        if(categoryAttributeOptional.isEmpty()){
            category.setAttributes(null);
        }else{
            category.setAttributes(categoryAttributeOptional.get());
        }


        if(!categoryAddRequestDto.getParentCategoryId().equals(-1L)){
            Category parentCategory = getParentCategory(categoryAddRequestDto.getParentCategoryId());
            category.setParentCategoryId(parentCategory.getId());

            if(parentCategory.getRootParentCategoryId().equals(-1L)){
                category.setRootParentCategoryId(parentCategory.getId());
            }else{
                category.setRootParentCategoryId(parentCategory.getRootParentCategoryId());
            }

        }else {
            category.setParentCategoryId(-1L);
            category.setRootParentCategoryId(-1L);
        }


        Category savedCategory = categoryRepository.save(category);


        CategoryResponseDto categoryResponseDto = CategoryDtoConverter.convertCategoryEntityToResponseDto(savedCategory);


        return categoryResponseDto;
    }


    private Optional<List<CategoryAttribute>> getCategoryAttribute(List<CategoryAttributeRequestDto> categoryAttributeRequestDtoList, Category category){

        if (Objects.isNull(categoryAttributeRequestDtoList) || categoryAttributeRequestDtoList.size()==0){
            return Optional.empty();
        }

        List<CategoryAttribute> attributeList = new ArrayList<>();

        for(CategoryAttributeRequestDto searchFiledRequestDto : categoryAttributeRequestDtoList){
            CategoryAttribute searchField = CategoryDtoConverter.convertCategorySearchFieldRequestDtoToEntity(searchFiledRequestDto);
            searchField.setCategory(category);
            attributeList.add(searchField);
        }
        return Optional.of(attributeList);
    }


    private Category getParentCategory(Long categoryId){
       return categoryRepository.findByIdAndStatus(categoryId, Status.ACTIVE.getValue()).orElseThrow(()->new ServiceException(ExceptionMessage.CATEGORY_NOT_FOUND.getMessage()));
    }
}
