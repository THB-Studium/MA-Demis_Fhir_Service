package de.rki.demis.fhir.service;

import com.datastax.oss.driver.api.core.CqlSession;
import de.rki.demis.fhir.exception.ParsingException;
import de.rki.demis.fhir.exception.ResourceBadRequestException;
import de.rki.demis.fhir.exception.ResourceNotFoundException;
import de.rki.demis.fhir.model.table.BinaryMod;
import de.rki.demis.fhir.repository.BinaryRepository;
import de.rki.demis.fhir.search.criteria.BinaryCriteria;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.udt;

@Service
@RequiredArgsConstructor
public class BinaryService {
    private final BinaryRepository repository;


    public List<BinaryMod> listAll() {
        return repository.findAll();
    }

    public BinaryMod getOne(UUID binaryModId) {
        Optional<BinaryMod> binaryOp = repository.findById(binaryModId);

        if (binaryOp.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("::: A Binary with 'id = %s' does not exist :::", binaryModId)
            );
        }

        return binaryOp.get();
    }

    public BinaryMod create(@NotNull BinaryMod newBinaryMod) {
        if (Objects.isNull(newBinaryMod.getId())) {
            newBinaryMod.setId(UUID.randomUUID());
        }
        checkForUniqueness(newBinaryMod);
        return repository.save(newBinaryMod);
    }

    public void update(UUID binaryId, @NotNull BinaryMod update)
            throws ResourceNotFoundException, ParsingException {

        // to check if the update exist
        getOne(binaryId);

        // to check the uniqueness of the update
        if (!Objects.equals(binaryId, update.getId())) {
            checkForUniqueness(update);
        }

        update.setId(binaryId);
        repository.save(update);
    }

    public void delete(UUID binaryId) {
        getOne(binaryId);
        repository.deleteById(binaryId);
    }

    public List<BinaryMod> search(BinaryCriteria criteria) {
//        return repository.findAll(new BinarySpecs(criteria));

        return test(criteria); // todo
    }

    private void checkForUniqueness(@NotNull BinaryMod binary) {
        if (repository.existsById(binary.getId())) {
            throw new ResourceBadRequestException(
                    String.format("::: A BinaryMod with the id=%s already exist :::", binary.getId())
            );
        }
    }

    @NotNull
    private List<BinaryMod> test(@NotNull BinaryCriteria criteria) {
        try (CqlSession session = CqlSession.builder().withKeyspace("spring_cassandra").build()) {
            UUID myId = UUID.fromString("b5c6bf85-c904-4774-a9fb-e716e82cf223");
            CassandraOperations cassandraOperationsTemplate = new CassandraTemplate(session);


//            Select query = selectFrom("spring_cassandra", "BinaryMod")
//                    .all()
//                    .whereColumn("meta")
//                    .where(Relation.columns("meta", "tag", "System", "myStringValue"))
//                    .whereCustomIndex("binarymod_meta_idx", QueryBuilder.literal(criteria.getSystem()))
//                    .where(Relation.columns("meta", "tag", "system", "myStringValue").in(QueryBuilder.literal(criteria.getSystem())))
//                    .whereColumn("tag").isNotNull()
//                    .whereColumn("system").isNotNull()
//                    .whereColumn("myStringValue").like(QueryBuilder.literal(criteria.getSystem()))
//                    .allowFiltering();
//                    .column("meta")
//                    .field("meta","tag")
//                    .field("tag","system")
//                    .field("system","myStringValue");

//            System.out.println(query);


////            queryTest.where(QueryBuilder.c).equals(criteria.getSystem());
//
//            String cql = query.toString() + "= " + criteria.getSystem();
//                    .stream().skip(0).limit(20).toList();
//
//            System.out.println(result);


//            List<BinaryMod> result = cassandraOperationsTemplate.select(query.asCql(), BinaryMod.class)

            String query = "select * from spring_cassandra.binarymod where meta.tag.system.mystringvalue:" + criteria.getSystem();
            List<BinaryMod> result = cassandraOperationsTemplate.select(query, BinaryMod.class);
            System.out.println(result);


//            List<BinaryMod> binaryMods = cassandraOperationsTemplate.select(
//                    Query.query(Criteria.where("meta").like(criteria.getSystem())),
//                    BinaryMod.class)
//                    ;
//
//            System.out.println(binaryMods);


//            List<BinaryMod> binaryMods = template.select(Query.query(
//                    Criteria.where("id")
//                            .like(myId)), BinaryMod.class);

//            System.out.println(binaryMods);


//            String cql = "SELECT * FROM spring_cassandra.binarymod WHERE meta.implicitrules.mystringvalue = ? ";
//            List<BinaryMod> result = cassandraTemplate.select(cql, new Object[]{criteria.getSystem()}, BinaryMod.class);

//            String cql = "meta.mystringvalue = '" + criteria.getSystem() + "'";
//            List<BinaryMod> result = template.select(cql, BinaryMod.class)
//                    .stream().skip(0).limit(20).toList();


//            System.out.println(result);


//            System.out.println(binaryMods);
//            template.truncate(BinaryMod.class);
            session.close();

//            ResultSet rs = session.execute(query.build());
//            Row row = rs.one();
//            assert row != null;
//            System.out.println(row.getColumnDefinitions());
        }

        return repository.findAll();
    }

}
