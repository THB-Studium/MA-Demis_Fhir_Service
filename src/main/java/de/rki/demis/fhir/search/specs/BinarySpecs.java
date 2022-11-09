package de.rki.demis.fhir.search.specs;

import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.BinaryMod;
import de.rki.demis.fhir.model.BinaryMod_;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Coding_;
import de.rki.demis.fhir.model.Meta;
import de.rki.demis.fhir.model.Meta_;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.model.UriType_;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class BinarySpecs implements Specification<BinaryMod> {
    private BinaryCriteria criteria;

    @Override
    public Predicate toPredicate(@NotNull Root<BinaryMod> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        final List<Predicate> predicates = new ArrayList<>();

        //::: by tag:.......

        /* for criteria: system */
        if (Objects.nonNull(criteria.getSystem())) {
            Join<Coding, UriType> binary2UriTypeJoin = root
                    .join(BinaryMod_.meta).join(Meta_.tag).join(Coding_.system);

            predicates.add(builder.like(
                    builder.lower(binary2UriTypeJoin.get(UriType_.myStringValue)),
                    criteria.getSystem().toLowerCase()));
        }

        /* for criteria: code */
        if (Objects.nonNull(criteria.getCode())) {
            Join<Coding, CodeType> binary2CodeTypeJoin = root
                    .join(BinaryMod_.meta).join(Meta_.tag).join(Coding_.code);

            predicates.add(builder.like(
                    builder.lower(binary2CodeTypeJoin.get(UriType_.myStringValue)),
                    criteria.getCode().toLowerCase()));
        }

        /* for criteria: display */
        if (Objects.nonNull(criteria.getDisplay())) {
            SetJoin<Meta, Coding> binary2codingJoin = root.join(BinaryMod_.meta).join(Meta_.tag);
            predicates.add(builder.like(
                    builder.lower(binary2codingJoin.get(Coding_.display)),
                    criteria.getDisplay().toLowerCase()));
        }


        //::: by lastUpdated:.......
        if (Objects.nonNull(criteria.getLastUpdated())) {
            Join<BinaryMod, Meta> metaJoin = root.join(BinaryMod_.meta);

            // to select the correct search date operation
            switch (criteria.getSearchDateOp()) {
                case AP, EQ -> predicates.add(builder.equal(
                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated()));
                case EB, LT -> predicates.add(builder.lessThan(
                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated()));
                case GE -> predicates.add(builder.greaterThanOrEqualTo(
                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated()));
                case GT, SA -> predicates.add(builder.greaterThan(
                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated()));
                case LE -> predicates.add(builder.lessThanOrEqualTo(
                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated()));
                case NE -> predicates.add(builder.notEqual(
                        metaJoin.get(Meta_.lastUpdated), criteria.getLastUpdated()));
                default -> throw new ResourceNotFoundException(
                        String.format("::: Search date operation '%s' not allowed! :::", criteria.getSearchDateOp()));
            }
        }

        query.distinct(true);
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
