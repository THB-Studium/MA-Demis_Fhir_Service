package de.rki.demis.fhir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtensionService {
//    private final ExtensionRepository repository;
//    private final UriTypeService uriTypeService;
//    private final TypeService typeService;
//
//
//    public List<Extension> listAll() {
//        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
//    }
//
//    public Extension getOne(UUID extensionId) {
//        Optional<Extension> extensionOp = repository.findById(extensionId);
//
//        if (extensionOp.isEmpty()) {
//            throw new ResourceNotFoundException(
//                    String.format("::: A Extension with 'id = %s' does not exist :::", extensionId)
//            );
//        }
//
//        return extensionOp.get();
//    }
//
//    public Extension create(@NotNull Extension newExtension) {
//        if (Objects.nonNull(newExtension.getUrl())) {
//            newExtension.setUrl(uriTypeService.create(newExtension.getUrl()));
//        }
//        if (Objects.nonNull(newExtension.getValue())) {
//            newExtension.setValue(typeService.create(newExtension.getValue()));
//        }
//
//        return repository.save(newExtension);
//    }
//
//    public void update(UUID extensionId, @NotNull Extension update) throws ResourceNotFoundException {
//        Extension extensionFound = getOne(extensionId);
//
//        if (!Objects.equals(extensionFound.getId(), update.getId())) {
//            checkForUniqueness(update);
//        }
//
//        update.setId(extensionId);
//        repository.save(update);
//    }
//
//    public void delete(UUID extensionId) {
//        getOne(extensionId);
//        repository.deleteById(extensionId);
//    }
//
//    private void checkForUniqueness(@NotNull Extension extension) {
//        if (repository.existsById(extension.getId())) {
//            throw new ResourceBadRequestException(
//                    String.format("::: A Extension with the id=%s already exist :::", extension.getId())
//            );
//        }
//    }

}
