package com.ecommerce.ecommnerce.category.serviceimpl;

import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.request.CategorySearchFiledRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;
import com.ecommerce.ecommnerce.category.dtoconverter.CategoryDtoConverter;
import com.ecommerce.ecommnerce.category.entity.Category;
import com.ecommerce.ecommnerce.category.entity.CategorySearchField;
import com.ecommerce.ecommnerce.category.repository.CategoryRepository;
import com.ecommerce.ecommnerce.category.service.CategoryService;
import com.ecommerce.ecommnerce.common.enums.ExceptionMessage;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

        Optional<List<CategorySearchField>> categorySearchFieldOptional = getCategorySearchField(categoryAddRequestDto.getSearchFields(),category);

        if(categorySearchFieldOptional.isEmpty()){
            category.setSearchFields(null);
        }else{
            category.setSearchFields(categorySearchFieldOptional.get());
        }


        if(!categoryAddRequestDto.getParentCategoryId().equals(-1L)){
            Category parentCategory = getParentCategory(categoryAddRequestDto.getParentCategoryId());
            category.setParentCategoryId(parentCategory.getId());

            if(parentCategory.getRootParentCategoryId().equals(-1L)){
                category.setRootParentCategoryId(parentCategory.getId());
            }else{
                category.setRootParentCategoryId(-1L);
            }

        }else {
            category.setParentCategoryId(-1L);
        }


        Category savedCategory = categoryRepository.save(category);


        CategoryResponseDto categoryResponseDto = CategoryDtoConverter.convertCategoryEntityToResponseDto(category);


        return categoryResponseDto;
    }


    private Optional<List<CategorySearchField>> getCategorySearchField(List<CategorySearchFiledRequestDto> categorySearchFiledRequestDtoList,Category category){

        if (Objects.isNull(categorySearchFiledRequestDtoList) || categorySearchFiledRequestDtoList.size()==0){
            return Optional.empty();
        }

        List<CategorySearchField> searchFieldList = new ArrayList<>();

        for(CategorySearchFiledRequestDto searchFiledRequestDto : categorySearchFiledRequestDtoList){
            CategorySearchField searchField = CategoryDtoConverter.convertCategorySearchFieldRequestDtoToEntity(searchFiledRequestDto);
            searchField.setCategory(category);
            searchFieldList.add(searchField);
        }
        return Optional.of(searchFieldList);
    }


    private Category getParentCategory(Long categoryId){
       return categoryRepository.findByIdAndStatus(categoryId, Status.ACTIVE.getValue()).orElseThrow(()->new ServiceException(ExceptionMessage.CATEGORY_NOT_FOUND.getMessage()));
    }
}
