package za.ac.cput.domain;

public enum PropertyStatus {
    PENDING("The property is awaiting approval."),
    APPROVED("The property has been approved."),
    REJECTED("The property has been rejected.");

    private final String description;

    PropertyStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
