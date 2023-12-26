package com.ecommerce.ecommnerce.category.serviceimpl;

import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.request.CategoryAttributeRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryGetAllResponseDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;
import com.ecommerce.ecommnerce.category.dtoconverter.CategoryDtoConverter;
import com.ecommerce.ecommnerce.category.entity.Category;
import com.ecommerce.ecommnerce.category.entity.CategoryAttribute;
import com.ecommerce.ecommnerce.category.enums.CategorySortField;
import com.ecommerce.ecommnerce.category.repository.CategoryAttributeRepository;
import com.ecommerce.ecommnerce.category.repository.CategoryRepository;
import com.ecommerce.ecommnerce.category.service.CategoryService;
import com.ecommerce.ecommnerce.category.specification.CategorySpecifications;
import com.ecommerce.ecommnerce.common.dto.request.AllDataGetRequestDto;
import com.ecommerce.ecommnerce.common.dto.request.IdDto;
import com.ecommerce.ecommnerce.common.enums.ExceptionMessage;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.common.enums.SuccessMessage;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.product.dto.request.SearchCriteria;
import com.ecommerce.ecommnerce.product.entity.Attribute;
import com.ecommerce.ecommnerce.product.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryAttributeRepository categoryAttributeRepository;

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
            category.setParentCategoryTitle(parentCategory.getTitle());

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

    @Override
    public CategoryGetAllResponseDto getAllCategory(AllDataGetRequestDto allDataGetRequestDto) {
        return getCategories(allDataGetRequestDto);
    }

    @Override
    public CategoryGetAllResponseDto getAllCategoryTree() {
        List<Category> allCategoryFromDB = getAllCategoryFromDB(null, null);
        Map<Long, List<CategoryResponseDto>> categoryMap = new HashMap<>();

        for (Category category : allCategoryFromDB) {
            CategoryResponseDto categoryResponseDto = CategoryDtoConverter.convertCategoryEntityToResponseDto(category);
            categoryMap.computeIfAbsent(category.getParentCategoryId(), k -> new ArrayList<>())
                    .add(categoryResponseDto);
        }

        List<CategoryResponseDto> rootCategories = categoryMap.getOrDefault(-1L, new ArrayList<>());
        for (CategoryResponseDto rootCategory : rootCategories) {
            populateChildren(rootCategory, categoryMap);
        }

        return CategoryGetAllResponseDto.builder()
                .categoryResponseDtoList(rootCategories)
                .build();
    }

    @Override
    @Transactional
    public String deleteCategoryById(IdDto idDto) {


        Category category = getCategoryByIdFromDB(idDto.getId());

        List<CategoryAttribute> attributes = getAttributeListOfCategoryByCategoryIdFromDB(idDto.getId());

        for(CategoryAttribute attribute :  attributes){
            attribute.setStatus(Status.DELETE.getValue());
        }

        category.setStatus(Status.DELETE.getValue());

        categoryRepository.save(category);

        categoryAttributeRepository.saveAll(attributes);

        return SuccessMessage.DATE_DELETED.getValue();
    }

    private void populateChildren(CategoryResponseDto categoryResponseDto, Map<Long, List<CategoryResponseDto>> categoryMap) {
        List<CategoryResponseDto> children = categoryMap.getOrDefault(categoryResponseDto.getId(), new ArrayList<>());
        for (CategoryResponseDto child : children) {
            populateChildren(child, categoryMap);
        }
        categoryResponseDto.setChildCategories(children);
    }
//    public CategoryGetAllResponseDto getAllCategoryTree() {
//
//        List<Category> allCategoryFromDB = getAllCategoryFromDB(null, null);
//
//        List<Category> rootCategories = allCategoryFromDB.stream().filter(category -> category.getRootParentCategoryId().equals(-1l)).collect(Collectors.toList());
//
//        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
//
//        for(Category category : rootCategories){
//
//            CategoryResponseDto categoryResponseDto = findChildCategory(category, allCategoryFromDB);
//
//            categoryResponseDtoList.add(categoryResponseDto);
//
//        }
//
//        CategoryGetAllResponseDto categoryGetAllResponseDto = CategoryGetAllResponseDto
//                .builder()
//                .categoryResponseDtoList(categoryResponseDtoList)
//                .build();
//
//
//        return categoryGetAllResponseDto;
//    }
//
//
//    private CategoryResponseDto findChildCategory(Category parent,List<Category> categoryList){
//
//
//
//        CategoryResponseDto parentCategoryResponseDto = CategoryDtoConverter.convertCategoryEntityToResponseDto(parent);
//
//        List<Category> childList = categoryList.stream().filter(category -> category.getParentCategoryId().equals(parent.getId())).collect(Collectors.toList());
//
//
//
//        if(childList.isEmpty()){
//            return parentCategoryResponseDto;
//        }else{
//
//            List<CategoryResponseDto> childCategoryResponseDtoList = new ArrayList<>();
//
//            for(Category category : childList){
//
//                CategoryResponseDto childCategoryDto = findChildCategory(category, categoryList);
//
//                childCategoryResponseDtoList.add(childCategoryDto);
//
//            }
//            parentCategoryResponseDto.setChildCategories(childCategoryResponseDtoList);
//
//            return  parentCategoryResponseDto;
//        }
//
//
//    }


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



    private Pageable getPageInfo(Integer offset, Integer pageSize ,String sortBy, String sortOrder){

        System.out.println("+++++++++++++  "+sortBy + "  " + sortOrder);

        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equalsIgnoreCase(sortOrder)) {
            direction = Sort.Direction.DESC;
        }



        if(Objects.isNull(sortBy) || sortBy.trim().equals("")){
            sortBy = "id";
        }else {

            try {

                CategorySortField categorySortField = CategorySortField.valueOf(sortBy.trim().toUpperCase());

                sortBy = categorySortField.getValue();

            }catch (Exception e){

            }

        }


        Sort sort = Sort.by(direction, sortBy);

        return PageRequest.of(offset,pageSize,sort);
    }


    private List<Category> getAllCategoryFromDB(CategorySpecifications categorySpecifications, Pageable pageable){

        if(!Objects.isNull(categorySpecifications) && !Objects.isNull(pageable)){
            return categoryRepository.findAll(categorySpecifications,pageable);
        } else if (!Objects.isNull(categorySpecifications) ) {
            return categoryRepository.findAll(categorySpecifications);
        }else if (!Objects.isNull(pageable)) {
            return categoryRepository.findAllByStatus(Status.ACTIVE.getValue(),pageable);
        }else {
            return categoryRepository.findAllByStatus(Status.ACTIVE.getValue());
        }

    }


    private CategoryGetAllResponseDto getCategories(AllDataGetRequestDto request){

        List<SearchCriteria> searchCriteriaList = request.getSearchCriteriaList();





        Pageable pageInfo = getPageInfo(request.getOffset(),request.getPageSize(), request.getOrderByField(), request.getOrderType());

        List<Category> categoryFromDBWithPage = null;
        List<Category> categoryFromDB = null;

        if(searchCriteriaList.size()>0){
            // filter apply
            CategorySpecifications categorySpecifications = new CategorySpecifications(searchCriteriaList);

            categoryFromDBWithPage = getAllCategoryFromDB(categorySpecifications, pageInfo);

            categoryFromDB = getAllCategoryFromDB(categorySpecifications, null);

        }else {

            // get all

            categoryFromDBWithPage = getAllCategoryFromDB(null, pageInfo);

            categoryFromDB = getAllCategoryFromDB(null, null);

        }

        List<CategoryResponseDto> categoryResponseDtoList = convertCategoryEntityToResponseDto(categoryFromDBWithPage);

        List<Long> categoryIdList = categoryResponseDtoList.stream().map(CategoryResponseDto::getId).collect(Collectors.toList());

        List<Category> categoryList = getCategoryByParentIdIn(categoryIdList);

        checkAndProcessChildCategories(categoryResponseDtoList,categoryList);


        Map<String, Object> metaData = Map.of("totalSize", categoryFromDB.size());

        CategoryGetAllResponseDto responseDto = new CategoryGetAllResponseDto(metaData, categoryResponseDtoList);

        return responseDto;
    }


    private List<CategoryResponseDto> convertCategoryEntityToResponseDto( List<Category>  categoryList){


        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();


        for(Category category : categoryList){
            CategoryResponseDto categoryResponseDto = CategoryDtoConverter.convertCategoryEntityToResponseDto(category);
            categoryResponseDtoList.add(categoryResponseDto);
        }

        return categoryResponseDtoList;


    }

    private List<Category> getCategoryByParentIdIn(List<Long> categoryIdList){
      return  categoryRepository.findAllByParentCategoryIdInAndStatus(categoryIdList,Status.ACTIVE.getValue());
    }

    private void checkAndProcessChildCategories(List<CategoryResponseDto> categoryResponseDtoList,List<Category> categoryList){

        Map<Long, List<Category>> categoryMap = new HashMap<>();

        for (Category category : categoryList) {
            categoryMap.computeIfAbsent(category.getParentCategoryId(), k -> new ArrayList<>())
                    .add(category);
        }


        for(CategoryResponseDto categoryResponseDto : categoryResponseDtoList){
            if(categoryMap.containsKey(categoryResponseDto.getId())){
                categoryResponseDto.setNoOfChildCategories(categoryMap.get(categoryResponseDto.getId()).size());
            }
        }
    }


    private Category getCategoryByIdFromDB(Long id){
        return categoryRepository.findByIdAndStatus(id,Status.ACTIVE.getValue()).orElseThrow(()->new ServiceException(ExceptionMessage.CATEGORY_NOT_FOUND.getMessage()));
    }

    private List<CategoryAttribute> getAttributeListOfCategoryByCategoryIdFromDB(Long categoryId){
       return categoryAttributeRepository.findByStatusAndCategoryIdAndCategoryStatus(Status.ACTIVE.getValue(), categoryId,Status.ACTIVE.getValue());
    }


}
