package za.ac.cput.factory;

import za.ac.cput.domain.Document;
import za.ac.cput.util.DocumentHelper;
import za.ac.cput.util.Helper;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DocumentFactory {
    public static Document buildDocument(Long documentId, String documentName, byte[]fileContents, LocalDateTime dateTimeUploaded ){
        if(documentId<=0||Helper.isNullOrEmpty(documentName)||Helper.isByteArrayNull(fileContents)||Helper.isInvalidDateTime(dateTimeUploaded)){
            return null;
        }
      return new Document.Builder().setDocumentId(documentId)
              .setDocumentName(documentName)
              .setFileContents(fileContents)
              .setDateTimeUploaded(dateTimeUploaded)
              .build();
    }

    public static Document buildDocument( String documentName, byte[]fileContents, LocalDateTime dateTimeUploaded ){
        if(Helper.isNullOrEmpty(documentName)||Helper.isByteArrayNull(fileContents)||Helper.isInvalidDateTime(dateTimeUploaded)){
            return null;
        }
        return new Document.Builder()
                .setDocumentName(documentName)
                .setFileContents(fileContents)
                .setDateTimeUploaded(dateTimeUploaded)
                .build();
    }
    public static Document buildDocument( String documentName, byte[]fileContents ){
        if(Helper.isNullOrEmpty(documentName)||Helper.isByteArrayNull(fileContents)){
            return null;
        }
        return new Document.Builder()
                .setDocumentName(documentName)
                .setFileContents(fileContents)
                .build();
    }
}

