//package com.ecommerce.ecommnerce.product.specifications;
//
//
//import com.ecommerce.ecommnerce.product.dto.request.SearchCriteria;
//import com.ecommerce.ecommnerce.product.entity.Attribute;
//import com.ecommerce.ecommnerce.product.enums.AttributeEnum;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AttributeSpecifications implements Specification<Attribute> {
//
//
//    private List<SearchCriteria> criteriaList;
//
//    private final Integer deleteStatus = 1;
//
//    public AttributeSpecifications(List<SearchCriteria> criteriaList) {
//        this.criteriaList = criteriaList;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Attribute> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        List<Predicate> predicateList = new ArrayList<>();
//
//        buildPredicateList(root,criteriaBuilder,predicateList);
//
//        Predicate predicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
//
//        return predicate;
//    }
//
//
//    private void  buildPredicateList(Root<Attribute> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList){
//
//        predicateList.add(criteriaBuilder.notEqual( root.get("isDeleted"), deleteStatus));
//
//        for (SearchCriteria criteria : criteriaList){
//
//            AttributeEnum searchKey = AttributeEnum.valueOf(criteria.getKey().toUpperCase());
//
//            switch (searchKey){
//
//                case SIZE:
//
//                case COLOR:
//
//                case FABRIC:
//
////                case STARTDATE:
////
////                case ENDDATE: {
////                    likeSearch(root,criteriaBuilder,predicateList,criteria,searchKey);
////                    break;
////                }
////
////                case CAUSEOFLOSS: {
////                    causeOfLossSearch(root,criteriaBuilder,predicateList,criteria,searchKey);
////                    break;
////                }
////
////                case STATUS: {
////                    AttributeStatusSearch(root,criteriaBuilder,predicateList,criteria,searchKey);
////                    break;
////                }
////
////                case CLIENTCODE: {
////                    clientCodeSearch(root,criteriaBuilder,predicateList,criteria,searchKey);
////                    break;
////                }
//
//            }
//
//        }
//
//    }
//
//
//    private void likeSearch(Root<Attribute> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, AttributeSearchField searchField){
//
//        predicateList.add(criteriaBuilder.like( criteriaBuilder.lower(root.get(searchField.getValue())), "%"+String.valueOf(searchCriteria.getValue()).toLowerCase()+"%"));
//
//    }
//
//    private void causeOfLossSearch(Root<Attribute> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, AttributeSearchField searchField){
//
//        predicateList.add(criteriaBuilder.like( criteriaBuilder.lower(root.get(searchField.getValue()).get("description")), "%"+String.valueOf(searchCriteria.getValue())+"%"));
//
//    }
//
//    private void AttributeStatusSearch(Root<Attribute> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList, SearchCriteria searchCriteria, AttributeSearchField searchField){
//
//        predicateList.add(criteriaBuilder.equal( root.get(searchField.getValue()), AttributeStatus.valueOf(String.valueOf(searchCriteria.getValue()))));
//
//    }
//
//    private void clientCodeSearch(Root<Attribute> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList,SearchCriteria searchCriteria, AttributeSearchField searchField){
//
//        predicateList.add(criteriaBuilder.equal( root.get("quotePolicyId").get("clientCode"), String.valueOf(searchCriteria.getValue())));
//
//    }
//
////    private void clientCodeSearch(Root<Attribute> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicateList, SearchCriteria searchCriteria, AttributeSearchField searchField) {
////        Join<Attribute, QuotePolicyDetailsModel> quotePolicyJoin = root.join("quote_policy_id");
////        predicateList.add(criteriaBuilder.like(quotePolicyJoin.get("client_code"), "%" + String.valueOf(searchCriteria.getValue()) + "%"));
////    }
//
//
//}
