package za.ac.cput.domain;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;


/**
 * Mpumzi Mbula
 * 219053324
 * Document.java
 *
 */
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;
    private String documentName;
    @Lob
    @Column(length=100000)
    private byte[] fileContents;
    private LocalDateTime dateTimeUploaded;

    @ManyToOne
    Property property;

    @ManyToOne
    Landlord landlord;


    public Document() {

    }
    private Document(Builder builder){
        this.documentId=builder.documentId;
        this.documentName=builder.documentName;
        this.fileContents=builder.fileContents;
        this.dateTimeUploaded=builder.dateTimeUploaded;
    }


    public Long getDocumentId() {
        return documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public byte[] getFileContents() {
        return fileContents;
    }

    public LocalDateTime getDateTimeUploaded() {
        return dateTimeUploaded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document document)) return false;
        return Objects.equals(documentId, document.documentId) && Objects.equals(documentName, document.documentName) && Arrays.equals(fileContents, document.fileContents) && Objects.equals(dateTimeUploaded, document.dateTimeUploaded);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(documentId, documentName, dateTimeUploaded);
        result = 31 * result + Arrays.hashCode(fileContents);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                ", dateTimeUploaded=" + dateTimeUploaded +
                '}';
    }

    public static class  Builder{
        private Long documentId;
        private String documentName;
        private byte[] fileContents;

        private LocalDateTime dateTimeUploaded;
        public Builder(){

        }
        public Builder copy(Document document){

            this.documentId=document.documentId;
            this.documentName=document.documentName;
            this.fileContents=document.fileContents;
            this.dateTimeUploaded=document.dateTimeUploaded;

            return this;
        }

        public Builder setDocumentId(Long documentId) {
            this.documentId = documentId;
            return this;
        }

        public Builder setDocumentName(String documentName) {
            this.documentName = documentName;
            return this;
        }

        public Builder setFileContents(byte[] fileContents) {
            this.fileContents = fileContents;
            return this;
        }

        public Builder setDateTimeUploaded(LocalDateTime dateTimeUploaded) {
            this.dateTimeUploaded = dateTimeUploaded;
            return this;
        }

        public Document build(){
            return new Document(this);
        }
    }
}
