package de.rki.demis.fhir.util.fhir_object.enums;

import de.rki.demis.fhir.util.fhir_object.classes.Configuration;
import org.hl7.fhir.exceptions.FHIRException;

public enum BundleType {
    /**
     * The bundle is a document. The first resource is a Composition.
     */
    DOCUMENT,

    /**
     * The bundle is a message. The first resource is a MessageHeader.
     */
    MESSAGE,

    /**
     * The bundle is a transaction - intended to be processed by a server as an atomic commit.
     */
    TRANSACTION,

    /**
     * The bundle is a transaction response. Because the response is a transaction response, the transaction has succeeded, and all responses are error free.
     */
    TRANSACTIONRESPONSE,

    /**
     * The bundle is a set of actions - intended to be processed by a server as a group of independent actions.
     */
    BATCH,

    /**
     * The bundle is a batch response. Note that as a batch, some responses may indicate failure and others success.
     */
    BATCHRESPONSE,

    /**
     * The bundle is a list of resources from a history interaction on a server.
     */
    HISTORY,

    /**
     * The bundle is a list of resources returned as a result of a search/query interaction, operation, or message.
     */
    SEARCHSET,

    /**
     * The bundle is a set of resources collected into a single package for ease of distribution that imposes no processing obligations or behavioral rules beyond persistence.
     */
    COLLECTION,

    /**
     * added to help the parsers with the generic types
     */
    NULL;

    public static BundleType fromCode(String codeString) throws FHIRException {
        if (codeString == null || codeString.isEmpty())
            return null;
        if ("document".equals(codeString))
            return DOCUMENT;
        if ("message".equals(codeString))
            return MESSAGE;
        if ("transaction".equals(codeString))
            return TRANSACTION;
        if ("transaction-response".equals(codeString))
            return TRANSACTIONRESPONSE;
        if ("batch".equals(codeString))
            return BATCH;
        if ("batch-response".equals(codeString))
            return BATCHRESPONSE;
        if ("history".equals(codeString))
            return HISTORY;
        if ("searchset".equals(codeString))
            return SEARCHSET;
        if ("collection".equals(codeString))
            return COLLECTION;
        if (Configuration.isAcceptInvalidEnums())
            return null;
        else
            throw new FHIRException("::: Unknown BundleType code '" + codeString + "' :::");
    }

    public String toCode() {
        return switch (this) {
            case DOCUMENT -> "document";
            case MESSAGE -> "message";
            case TRANSACTION -> "transaction";
            case TRANSACTIONRESPONSE -> "transaction-response";
            case BATCH -> "batch";
            case BATCHRESPONSE -> "batch-response";
            case HISTORY -> "history";
            case SEARCHSET -> "searchset";
            case COLLECTION -> "collection";
            case NULL -> null;
        };
    }

    public String getSystem() {
        return switch (this) {
            case DOCUMENT, COLLECTION, SEARCHSET, HISTORY, BATCHRESPONSE, BATCH, TRANSACTIONRESPONSE, TRANSACTION, MESSAGE ->
                    "http://hl7.org/fhir/bundle-type";
            case NULL -> null;
        };
    }

    public String getDefinition() {
        return switch (this) {
            case DOCUMENT -> "The bundle is a document. The first resource is a Composition.";
            case MESSAGE -> "The bundle is a message. The first resource is a MessageHeader.";
            case TRANSACTION ->
                    "The bundle is a transaction - intended to be processed by a server as an atomic commit.";
            case TRANSACTIONRESPONSE ->
                    "The bundle is a transaction response. Because the response is a transaction response, the transaction has succeeded, and all responses are error free.";
            case BATCH ->
                    "The bundle is a set of actions - intended to be processed by a server as a group of independent actions.";
            case BATCHRESPONSE ->
                    "The bundle is a batch response. Note that as a batch, some responses may indicate failure and others success.";
            case HISTORY -> "The bundle is a list of resources from a history interaction on a server.";
            case SEARCHSET ->
                    "The bundle is a list of resources returned as a result of a search/query interaction, operation, or message.";
            case COLLECTION ->
                    "The bundle is a set of resources collected into a single package for ease of distribution that imposes no processing obligations or behavioral rules beyond persistence.";
            case NULL -> null;
        };
    }

    public String getDisplay() {
        return switch (this) {
            case DOCUMENT -> "Document";
            case MESSAGE -> "Message";
            case TRANSACTION -> "Transaction";
            case TRANSACTIONRESPONSE -> "Transaction Response";
            case BATCH -> "Batch";
            case BATCHRESPONSE -> "Batch Response";
            case HISTORY -> "History List";
            case SEARCHSET -> "Search Results";
            case COLLECTION -> "Collection";
            case NULL -> null;
        };
    }
}

