//package de.rki.demis.fhir.search.specs;
//
//import de.rki.demis.fhir.exception.ResourceNotFoundException;
//import de.rki.demis.fhir.model.BundleMod;
//import de.rki.demis.fhir.model.BundleMod_;
//import de.rki.demis.fhir.model.Meta;
//import de.rki.demis.fhir.model.Meta_;
//import de.rki.demis.fhir.search.criteria.BundleCriteria;
//import lombok.AllArgsConstructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Join;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@AllArgsConstructor
//public class BundleSpecs implements Specification<BundleMod> {
//    private BundleCriteria criteria;
//
//    @Override
//    public Predicate toPredicate(@NotNull Root<BundleMod> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
//        final List<Predicate> predicates = new ArrayList<>();
//
//        //::: by lastUpdated:.......
//        if (Objects.nonNull(criteria.getLastUpdated())) {
//            predicates.add(getLastUpdatedPredicate(root, builder));
//        }
//
//        query.distinct(true);
//        return builder.and(predicates.toArray(new Predicate[0]));
//    }
//
//
//    /*** criteria builder for: lastUpdated ***/
//    private Predicate getLastUpdatedPredicate(@NotNull Root<BundleMod> root, @NotNull CriteriaBuilder builder) {
//        Join<BundleMod, Meta> metaJoin = root.join(BundleMod_.meta);
//
//        // to select the correct search date operation
//        switch (criteria.getSearchDateOp()) {
//            case AP, EQ -> {
//                return builder.equal(
//                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated());
//            }
//            case EB, LT -> {
//                return builder.lessThan(
//                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated());
//            }
//            case GE -> {
//                return builder.greaterThanOrEqualTo(
//                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated());
//            }
//            case GT, SA -> {
//                return builder.greaterThan(
//                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated());
//            }
//            case LE -> {
//                return builder.lessThanOrEqualTo(
//                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated());
//            }
//            case NE -> {
//                return builder.notEqual(
//                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated());
//            }
//            default -> throw new ResourceNotFoundException(
//                    String.format("::: Search date operation '%s' not allowed! :::", criteria.getSearchDateOp()));
//        }
//    }
//}
