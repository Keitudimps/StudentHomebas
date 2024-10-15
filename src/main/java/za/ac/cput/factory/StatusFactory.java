package za.ac.cput.factory;

import za.ac.cput.domain.PropertyStatus;
import za.ac.cput.domain.Status;

public class StatusFactory {

    public static Status createPendingStatus() {
        return new Status.Builder()
                .setStatus(PropertyStatus.PENDING)
                .build();
    }


    public static Status createApprovedStatus() {
        return new Status.Builder()
                .setStatus(PropertyStatus.APPROVED)
                .build();
    }

    public static Status createRejectedStatus(String rejectionReason) {
        return new Status.Builder()
                .setStatus(PropertyStatus.REJECTED)
                .setRejectionReason(rejectionReason)
                .build();
    }
}

