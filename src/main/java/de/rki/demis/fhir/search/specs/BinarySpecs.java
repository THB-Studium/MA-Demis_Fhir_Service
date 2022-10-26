package de.rki.demis.fhir.search.specs;

import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BinarySpecs implements Specification<BinaryMod> {
    private BinaryCriteria criteria;

    @Override
    public Predicate toPredicate(Root<BinaryMod> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        final List<Predicate> predicates = new ArrayList<>();

//        // by tag:
//        if (!Objects.isNull(criteria.getTag())) {
//            Predicate tagPredicate = builder.like(
//                    builder.upper(root.get(BinaryMod_.tag)), criteria.getTag().toUpperCase());
//            predicates.add(tagPredicate);
//        }
//
//        // by lastUpdated:
//        if (!Objects.isNull(criteria.getLastUpdated())) {
//            Predicate lastUpdatedPredicate = builder.greaterThanOrEqualTo(
//                    root.get(BinaryMod_.lastUpdated), criteria.getLastUpdated());
//            predicates.add(lastUpdatedPredicate);
//        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
