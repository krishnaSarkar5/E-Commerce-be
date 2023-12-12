package com.ecommerce.ecommnerce.category.specification;

import com.ecommerce.ecommnerce.category.entity.Category;
import com.ecommerce.ecommnerce.category.enums.CategorySearchField;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.product.dto.request.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CategorySpecifications implements Specification<Category> {


    private List<SearchCriteria> criteriaList;

    private final Integer deleteStatus = 1;

    public CategorySpecifications(List<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        buildPredicateList(root,criteriaBuilder,predicateList);

        Predicate predicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        return predicate;
    }


    private void  buildPredicateList(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList){

        predicateList.add(criteriaBuilder.equal( root.get("status"), Status.ACTIVE.getValue()));

        for (SearchCriteria criteria : criteriaList){

            CategorySearchField searchKey = CategorySearchField.valueOf(criteria.getKey().toUpperCase());

        switch (searchKey){

            case TITLE -> {
                titleSearch(root,criteriaBuilder,predicateList,criteria,searchKey);
                break;
            }
            case PARENTCATEGORYTITLE -> {
                parentTitleSearch(root,criteriaBuilder,predicateList,criteria,searchKey);
                break;
            }
            case ID->{}
            case PARENTCATEGORYID -> {
                equalSearch(root,criteriaBuilder,predicateList,criteria,searchKey);
                break;
            }
        }

        }

    }



    private void titleSearch(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, CategorySearchField searchField){
        likeSearch(root,criteriaBuilder,predicateList,searchCriteria,searchField);
    }



    private void parentTitleSearch(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, CategorySearchField searchField){

//        Join<Category, Category> join = root.join("parentCategoryId", JoinType.LEFT);
//
//        Expression<String> parentCategoryTitleExpression = join.get("title");
//        criteriaBuilder.like(criteriaBuilder.lower(parentCategoryTitleExpression),"%"+String.valueOf(searchCriteria.getValue()).toLowerCase()+"%");
        likeSearch(root,criteriaBuilder,predicateList,searchCriteria,searchField);

    }






    private void likeSearch(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, CategorySearchField searchField){

        predicateList.add(criteriaBuilder.like( criteriaBuilder.lower(root.get(searchField.getValue())), "%"+String.valueOf(searchCriteria.getValue()).toLowerCase()+"%"));

    }

//    private void equalSearch(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, CategorySearchField searchField){
//
//        predicateList.add(criteriaBuilder.equal( root.get(searchField.getValue()), String.valueOf(searchCriteria.getValue())));
//
//    }
    private void equalSearch(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, CategorySearchField searchField){

        predicateList.add( criteriaBuilder.equal(root.get(searchField.getValue()), Long.valueOf(String.valueOf(searchCriteria.getValue()))));

    }

    private void causeOfLossSearch(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, CategorySearchField searchField){

        predicateList.add(criteriaBuilder.like( criteriaBuilder.lower(root.get(searchField.getValue()).get("description")), "%"+String.valueOf(searchCriteria.getValue())+"%"));

    }



    private void clientCodeSearch(Root<Category> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, CategorySearchField searchField){

        predicateList.add(criteriaBuilder.equal( root.get("quotePolicyId").get("clientCode"), String.valueOf(searchCriteria.getValue())));

    }

//    private void clientCodeSearch(Root<Claim> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList, SearchCriteria searchCriteria, ClaimSearchField searchField) {
//        Join<Claim, QuotePolicyDetailsModel> quotePolicyJoin = root.join("quote_policy_id");
//        predicateList.add(criteriaBuilder.like(quotePolicyJoin.get("client_code"), "%" + String.valueOf(searchCriteria.getValue()) + "%"));
//    }


}
