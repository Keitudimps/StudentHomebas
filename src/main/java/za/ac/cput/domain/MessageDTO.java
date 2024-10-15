package za.ac.cput.domain;

public class MessageDTO {
    private Long senderId;
    private Long receiverId;
    private String content;

    public MessageDTO(Long senderId, Long receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public MessageDTO setSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }


    public Long getReceiverId() {
        return receiverId;
    }

    public MessageDTO setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageDTO setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                '}';
    }
}