package de.rki.demis.fhir.util.service;

<<<<<<< HEAD
<<<<<<< HEAD
import de.rki.demis.fhir.model.BaseEntity;
import de.rki.demis.fhir.service.BaseService;
import de.rki.demis.fhir.util.constant.RequestOperation;
=======
=======
import de.rki.demis.fhir.model.BundleEntryComponent;
import de.rki.demis.fhir.model.BundleEntryRequestComponent;
import de.rki.demis.fhir.model.BundleEntryResponseComponent;
import de.rki.demis.fhir.model.BundleEntrySearchComponent;
import de.rki.demis.fhir.model.BundleLinkComponent;
>>>>>>> acf3b2c (wip)
import de.rki.demis.fhir.model.CanonicalType;
import de.rki.demis.fhir.model.CodeType;
import de.rki.demis.fhir.model.CodeableConcept;
import de.rki.demis.fhir.model.Coding;
import de.rki.demis.fhir.model.Enumeration;
import de.rki.demis.fhir.model.Extension;
import de.rki.demis.fhir.model.Identifier;
import de.rki.demis.fhir.model.Meta;
import de.rki.demis.fhir.model.Reference;
import de.rki.demis.fhir.model.Signature;
import de.rki.demis.fhir.model.Type;
import de.rki.demis.fhir.model.UriType;
import de.rki.demis.fhir.service.BundleEntryComponentService;
import de.rki.demis.fhir.service.BundleEntryRequestComponentService;
import de.rki.demis.fhir.service.BundleEntryResponseComponentService;
import de.rki.demis.fhir.service.BundleEntrySearchComponentService;
import de.rki.demis.fhir.service.BundleLinkComponentService;
import de.rki.demis.fhir.service.CanonicalTypeService;
import de.rki.demis.fhir.service.CodeTypeService;
import de.rki.demis.fhir.service.CodeableConceptService;
import de.rki.demis.fhir.service.CodingService;
import de.rki.demis.fhir.service.EnumerationBundleTypeService;
import de.rki.demis.fhir.service.ExtensionService;
import de.rki.demis.fhir.service.IdentifierService;
import de.rki.demis.fhir.service.MetaService;
import de.rki.demis.fhir.service.ReferenceService;
import de.rki.demis.fhir.service.ResourceService;
import de.rki.demis.fhir.service.SignatureService;
import de.rki.demis.fhir.service.TypeService;
import de.rki.demis.fhir.service.UriTypeService;
import de.rki.demis.fhir.util.constant.RequestOperation;
import de.rki.demis.fhir.util.fhir_object.classes.Resource;
<<<<<<< HEAD
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
import de.rki.demis.fhir.util.fhir_object.enums.BundleType;
>>>>>>> acf3b2c (wip)
import org.springframework.stereotype.Service;

import java.util.Objects;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import static de.rki.demis.fhir.util.constant.Constants.UPDATE_OP;
>>>>>>> c598496 (update issues in BinaryService fixed)
=======
>>>>>>> acf3b2c (wip)

@Service
public class PersistenceService {

<<<<<<< HEAD
<<<<<<< HEAD
    public static <T extends BaseEntity> T persistEntity(T newEntity, BaseService<T> service, RequestOperation requestOperation) {
        if (Objects.nonNull(newEntity)) {
            newEntity = Objects.equals(requestOperation, RequestOperation.Update) && Objects.nonNull(newEntity.getId()) // to create directly the entity if id = null
                    ? service.getRepository().existsById(newEntity.getId()) // to update the entity if already exist or create it when not
                        ? service.update(newEntity.getId(), newEntity)
                        : service.create(newEntity)
                    : service.create(newEntity);
        }
        return newEntity;
=======
=======
    // to create or update BundleEntryComponent entities
    public static BundleEntryComponent persistBundleEntryComponentEntity(
            BundleEntryComponent bundleEntryComponent,
            BundleEntryComponentService service,
            RequestOperation requestOperation) {

        if (Objects.nonNull(bundleEntryComponent)) {
            if (Objects.nonNull(bundleEntryComponent.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(bundleEntryComponent.getId(), bundleEntryComponent);
                bundleEntryComponent = service.getOne(bundleEntryComponent.getId());
            } else {
                bundleEntryComponent = service.create(bundleEntryComponent);
            }
        }

        return bundleEntryComponent;
    }

    // to create or update BundleEntrySearchComponent entities
    public static BundleEntrySearchComponent persistBundleEntrySearchComponentEntity(
            BundleEntrySearchComponent bundleEntrySearchComponent,
            BundleEntrySearchComponentService service,
            RequestOperation requestOperation) {

        if (Objects.nonNull(bundleEntrySearchComponent)) {
            if (Objects.nonNull(bundleEntrySearchComponent.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(bundleEntrySearchComponent.getId(), bundleEntrySearchComponent);
                bundleEntrySearchComponent = service.getOne(bundleEntrySearchComponent.getId());
            } else {
                bundleEntrySearchComponent = service.create(bundleEntrySearchComponent);
            }
        }

        return bundleEntrySearchComponent;
    }

    // to create or update BundleEntryRequestComponent entities
    public static BundleEntryRequestComponent persistBundleEntryRequestComponentEntity(
            BundleEntryRequestComponent bundleEntryRequestComponent,
            BundleEntryRequestComponentService service,
            RequestOperation requestOperation) {

        if (Objects.nonNull(bundleEntryRequestComponent)) {
            if (Objects.nonNull(bundleEntryRequestComponent.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(bundleEntryRequestComponent.getId(), bundleEntryRequestComponent);
                bundleEntryRequestComponent = service.getOne(bundleEntryRequestComponent.getId());
            } else {
                bundleEntryRequestComponent = service.create(bundleEntryRequestComponent);
            }
        }

        return bundleEntryRequestComponent;
    }

    // to create or update BundleEntryResponseComponent entities
    public static BundleEntryResponseComponent persistBundleEntryResponseComponentEntity(
            BundleEntryResponseComponent bundleEntryResponseComponent,
            BundleEntryResponseComponentService service,
            RequestOperation requestOperation) {

        if (Objects.nonNull(bundleEntryResponseComponent)) {
            if (Objects.nonNull(bundleEntryResponseComponent.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(bundleEntryResponseComponent.getId(), bundleEntryResponseComponent);
                bundleEntryResponseComponent = service.getOne(bundleEntryResponseComponent.getId());
            } else {
                bundleEntryResponseComponent = service.create(bundleEntryResponseComponent);
            }
        }

        return bundleEntryResponseComponent;
    }

    // to create or update BundleLinkComponent entities
    public static BundleLinkComponent persistBundleLinkComponentEntity(
            BundleLinkComponent bundleLinkComponent,
            BundleLinkComponentService service,
            RequestOperation requestOperation) {

        if (Objects.nonNull(bundleLinkComponent)) {
            if (Objects.nonNull(bundleLinkComponent.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(bundleLinkComponent.getId(), bundleLinkComponent);
                bundleLinkComponent = service.getOne(bundleLinkComponent.getId());
            } else {
                bundleLinkComponent = service.create(bundleLinkComponent);
            }
        }

        return bundleLinkComponent;
    }

>>>>>>> acf3b2c (wip)
    // to create or update CanonicalType entities
    public static CanonicalType persistCanonicalTypeEntity(
            CanonicalType newCanonicalType,
            CanonicalTypeService service,
            RequestOperation requestOperation) {

        if (Objects.nonNull(newCanonicalType)) {
            if (Objects.nonNull(newCanonicalType.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newCanonicalType.getId(), newCanonicalType);
                newCanonicalType = service.getOne(newCanonicalType.getId());
            } else {
                newCanonicalType = service.create(newCanonicalType);
            }
        }

        return newCanonicalType;
    }

    // to create or update CodeableConcept entities
    public static CodeableConcept persistCodeableConceptEntity(
            CodeableConcept newCodeableConcept,
            CodeableConceptService service,
            RequestOperation requestOperation) {

        if (Objects.nonNull(newCodeableConcept)) {
            if (Objects.nonNull(newCodeableConcept.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newCodeableConcept.getId(), newCodeableConcept);
                newCodeableConcept = service.getOne(newCodeableConcept.getId());
            } else {
                newCodeableConcept = service.create(newCodeableConcept);
            }
        }

        return newCodeableConcept;
    }

    // to create or update CodeType entities
    public static CodeType persistCodeTypeEntity(CodeType newCodeType, CodeTypeService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newCodeType)) {
            if (Objects.nonNull(newCodeType.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newCodeType.getId(), newCodeType);
                newCodeType = service.getOne(newCodeType.getId());
            } else {
                newCodeType = service.create(newCodeType);
            }
        }

        return newCodeType;
    }

    // to create or update Coding entities
    public static Coding persistCodingEntity(Coding newCoding, CodingService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newCoding)) {
            if (Objects.nonNull(newCoding.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newCoding.getId(), newCoding);
                newCoding = service.getOne(newCoding.getId());
            } else {
                newCoding = service.create(newCoding);
            }
        }

        return newCoding;
    }

    // to create or update Enumeration BundleType entities
    public static Enumeration<BundleType> persistEnumerationBundleTypeEntity(Enumeration<BundleType> newEnumeration, EnumerationBundleTypeService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newEnumeration)) {
            if (Objects.nonNull(newEnumeration.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newEnumeration.getId(), newEnumeration);
                newEnumeration = service.getOne(newEnumeration.getId());
            } else {
                newEnumeration = service.create(newEnumeration);
            }
        }

        return newEnumeration;
    }

    // to create or update Extension entities
    public static Extension persistExtensionEntity(Extension newExtension, ExtensionService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newExtension)) {
            if (Objects.nonNull(newExtension.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newExtension.getId(), newExtension);
                newExtension = service.getOne(newExtension.getId());
            } else {
                newExtension = service.create(newExtension);
            }
        }

        return newExtension;
    }

    // to create or update Identifier entities
    public static Identifier persistIdentifierEntity(Identifier newIdentifier, IdentifierService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newIdentifier)) {
            if (Objects.nonNull(newIdentifier.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newIdentifier.getId(), newIdentifier);
                newIdentifier = service.getOne(newIdentifier.getId());
            } else {
                newIdentifier = service.create(newIdentifier);
            }
        }

        return newIdentifier;
    }

    // to create or update Meta entities
    public static Meta persistMetaEntity(Meta newMeta, MetaService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newMeta)) {
            if (Objects.nonNull(newMeta.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newMeta.getId(), newMeta);
                newMeta = service.getOne(newMeta.getId());
            } else {
                newMeta = service.create(newMeta);
            }
        }

        return newMeta;
    }

    // to create or update Reference entities
    public static Reference persistReferenceEntity(Reference newReference, ReferenceService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newReference)) {
            if (Objects.nonNull(newReference.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newReference.getId(), newReference);
                newReference = service.getOne(newReference.getId());
            } else {
                newReference = service.create(newReference);
            }
        }

        return newReference;
    }

    // to create or update Resource entities
    public static Resource persistResourceEntity(Resource newResource, ResourceService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newResource)) {
            if (Objects.nonNull(newResource.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newResource.getId(), newResource);
                newResource = service.getOne(newResource.getId());
            } else {
                newResource = service.create(newResource);
            }
        }

        return newResource;
    }

    // to create or update Signature entities
    public static Signature persistSignatureEntity(Signature signature, SignatureService service, RequestOperation requestOperation) {
        if (Objects.nonNull(signature)) {
            if (Objects.nonNull(signature.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(signature.getId(), signature);
                signature = service.getOne(signature.getId());
            } else {
                signature = service.create(signature);
            }
        }

        return signature;
    }

    // to create or update Resource entities
    public static Type persistTypeEntity(Type newType, TypeService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newType)) {
            if (Objects.nonNull(newType.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newType.getId(), newType);
                newType = service.getOne(newType.getId());
            } else {
                newType = service.create(newType);
            }
        }

        return newType;
    }

    // to create or update UriType entities
    public static UriType persistUriTypeEntity(UriType newUriType, UriTypeService service, RequestOperation requestOperation) {
        if (Objects.nonNull(newUriType)) {
            if (Objects.nonNull(newUriType.getId()) && Objects.equals(requestOperation, RequestOperation.Update)) {
                service.update(newUriType.getId(), newUriType);
                newUriType = service.getOne(newUriType.getId());
            } else {
                newUriType = service.create(newUriType);
            }
        }

        return newUriType;
>>>>>>> c598496 (update issues in BinaryService fixed)
    }

}
