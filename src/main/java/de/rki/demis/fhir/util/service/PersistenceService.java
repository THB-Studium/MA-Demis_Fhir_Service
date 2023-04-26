package de.rki.demis.fhir.util.service;

import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.model.CodeableConcept;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.model.Identifier;
import de.rki.demis.fhir.model.Meta;
import de.rki.demis.fhir.model.Reference;
import de.rki.demis.fhir.model.Type;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.service.CanonicalTypeService;
import de.rki.demis.fhir.service.CodeTypeService;
import de.rki.demis.fhir.service.CodeableConceptService;
import de.rki.demis.fhir.service.CodingService;
import de.rki.demis.fhir.service.ExtensionService;
import de.rki.demis.fhir.service.IdentifierService;
import de.rki.demis.fhir.service.MetaService;
import de.rki.demis.fhir.service.ReferenceService;
import de.rki.demis.fhir.service.ResourceService;
import de.rki.demis.fhir.service.TypeService;
import de.rki.demis.fhir.service.UriTypeService;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;

@Service
public class PersistenceService {

    // to create or update CanonicalType entities
    public static CanonicalType persistCanonicalTypeEntity(
            CanonicalType newCanonicalType,
            CanonicalTypeService service,
            String requestOperation) {

        if (Objects.nonNull(newCanonicalType)) {
            if (Objects.nonNull(newCanonicalType.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newCanonicalType.getId(), newCanonicalType);
                newCanonicalType = service.getOne(newCanonicalType.getId());
            } else {
                newCanonicalType = service.create(newCanonicalType);
            }
        }

        return newCanonicalType;
    }

    // to create or update Coding entities
    public static Coding persistCodingEntity(Coding newCoding, CodingService service, String requestOperation) {
        if (Objects.nonNull(newCoding)) {
            if (Objects.nonNull(newCoding.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newCoding.getId(), newCoding);
                newCoding = service.getOne(newCoding.getId());
            } else {
                newCoding = service.create(newCoding);
            }
        }

        return newCoding;
    }

    // to create or update Coding entities
    public static CodeableConcept persistCodeableConceptEntity(
            CodeableConcept newCodeableConcept,
            CodeableConceptService service,
            String requestOperation) {

        if (Objects.nonNull(newCodeableConcept)) {
            if (Objects.nonNull(newCodeableConcept.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newCodeableConcept.getId(), newCodeableConcept);
                newCodeableConcept = service.getOne(newCodeableConcept.getId());
            } else {
                newCodeableConcept = service.create(newCodeableConcept);
            }
        }

        return newCodeableConcept;
    }

    // to create or update CodeType entities
    public static CodeType persistCodeTypeEntity(CodeType newCodeType, CodeTypeService service, String requestOperation) {
        if (Objects.nonNull(newCodeType)) {
            if (Objects.nonNull(newCodeType.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newCodeType.getId(), newCodeType);
                newCodeType = service.getOne(newCodeType.getId());
            } else {
                newCodeType = service.create(newCodeType);
            }
        }

        return newCodeType;
    }

    // to create or update Extension entities
    public static Extension persistExtensionEntity(Extension newExtension, ExtensionService service, String requestOperation) {
        if (Objects.nonNull(newExtension)) {
            if (Objects.nonNull(newExtension.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newExtension.getId(), newExtension);
                newExtension = service.getOne(newExtension.getId());
            } else {
                newExtension = service.create(newExtension);
            }
        }

        return newExtension;
    }

    // to create or update Identifier entities
    public static Identifier persistIdentifierEntity(Identifier newIdentifier, IdentifierService service, String requestOperation) {
        if (Objects.nonNull(newIdentifier)) {
            if (Objects.nonNull(newIdentifier.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newIdentifier.getId(), newIdentifier);
                newIdentifier = service.getOne(newIdentifier.getId());
            } else {
                newIdentifier = service.create(newIdentifier);
            }
        }

        return newIdentifier;
    }

    // to create or update Meta entities
    public static Meta persistMetaEntity(Meta newMeta, MetaService service, String requestOperation) {
        if (Objects.nonNull(newMeta)) {
            if (Objects.nonNull(newMeta.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newMeta.getId(), newMeta);
                newMeta = service.getOne(newMeta.getId());
            } else {
                newMeta = service.create(newMeta);
            }
        }

        return newMeta;
    }

    // to create or update Reference entities
    public static Reference persistReferenceEntity(Reference newReference, ReferenceService service, String requestOperation) {
        if (Objects.nonNull(newReference)) {
            if (Objects.nonNull(newReference.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newReference.getId(), newReference);
                newReference = service.getOne(newReference.getId());
            } else {
                newReference = service.create(newReference);
            }
        }

        return newReference;
    }

    // to create or update Resource entities
    public static Resource persistResourceEntity(Resource newResource, ResourceService service, String requestOperation) {
        if (Objects.nonNull(newResource)) {
            if (Objects.nonNull(newResource.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newResource.getId(), newResource);
                newResource = service.getOne(newResource.getId());
            } else {
                newResource = service.create(newResource);
            }
        }

        return newResource;
    }

    // to create or update Resource entities
    public static Type persistTypeEntity(Type newType, TypeService service, String requestOperation) {
        if (Objects.nonNull(newType)) {
            if (Objects.nonNull(newType.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newType.getId(), newType);
                newType = service.getOne(newType.getId());
            } else {
                newType = service.create(newType);
            }
        }

        return newType;
    }

    // to create or update UriType entities
    public static UriType persistUriTypeEntity(UriType newUriType, UriTypeService service, String requestOperation) {
        if (Objects.nonNull(newUriType)) {
            if (Objects.nonNull(newUriType.getId()) && Objects.equals(requestOperation, UPDATE_OP)) {
                service.update(newUriType.getId(), newUriType);
                newUriType = service.getOne(newUriType.getId());
            } else {
                newUriType = service.create(newUriType);
            }
        }

        return newUriType;
    }

}