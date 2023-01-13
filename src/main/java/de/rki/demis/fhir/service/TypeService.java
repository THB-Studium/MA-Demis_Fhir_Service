package de.rki.demis.fhir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeService {
//    private final TypeRepository repository;
//
//
//
//    public List<Type> listAll() {
//        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
//    }
//
//    public Type getOne(UUID typeId) {
//        Optional<Type> typeOp = repository.findById(typeId);
//
//        if (typeOp.isEmpty()) {
//            throw new ResourceNotFoundException(
//                    String.format("::: A Type with 'id = %s' does not exist :::", typeId)
//            );
//        }
//
//        return typeOp.get();
//    }
//
//    public Type create(@NotNull Type newType) {
//        return repository.save(newType);
//    }
//
//    public void update(UUID typeId, @NotNull Type update) throws ResourceNotFoundException {
//        Type typeFound = getOne(typeId);
//
//        if (!Objects.equals(typeFound.getId(), update.getId())) {
//            checkForUniqueness(update);
//        }
//
//        update.setId(typeId);
//        repository.save(update);
//    }
//
//    public void delete(UUID typeId) {
//        getOne(typeId);
//        repository.deleteById(typeId);
//    }
//
//    private void checkForUniqueness(@NotNull Type type) {
//        if (repository.existsById(type.getId())) {
//            throw new ResourceBadRequestException(
//                    String.format("::: A Type with the id=%s already exist :::", type.getId())
//            );
//        }
//    }

}
