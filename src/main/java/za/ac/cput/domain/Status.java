package za.ac.cput.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import za.ac.cput.domain.PropertyStatus;

@Embeddable
public class Status {

    @NotNull
    private PropertyStatus statusName;
    private String rejectionReason;


    private Status(Builder builder) {
        this.statusName = builder.statusName;
        this.rejectionReason = builder.rejectionReason;
    }

    protected Status() {

    }


    public PropertyStatus getStatusName() {
        return statusName;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public String getDescription() {
        return this.statusName.getDescription();
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusName=" + statusName +
                ", description='" + getDescription() + '\'' +
                (rejectionReason != null ? ", rejectionReason='" + rejectionReason + '\'' : "") +
                '}';
    }


    public static class Builder {
        private PropertyStatus statusName;
        private String rejectionReason;

        public Builder setStatus(PropertyStatus statusName) {
            this.statusName = statusName;
            return this;
        }

        public Builder setRejectionReason(String rejectionReason) {
            if (this.statusName != PropertyStatus.REJECTED) {
                throw new IllegalArgumentException("Rejection reason can only be set if status is REJECTED.");
            }
            this.rejectionReason = rejectionReason;
            return this;
        }

        public Status build() {
            if (statusName == PropertyStatus.REJECTED && (rejectionReason == null || rejectionReason.isEmpty())) {
                throw new IllegalArgumentException("Rejection reason must be provided when status is REJECTED.");
            }
            return new Status(this);
        }
    }
}
