package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;  // Either a Student or a Landlord

    @ManyToOne
    private User receiver; // Either a Student or a Landlord

    private String content;
    private LocalDateTime timestamp;

    protected Message() {
    }

    protected Message(MessageBuilder m) {
        this.id = m.id;
        this.sender = m.sender;
        this.receiver = m.receiver;
        this.content = m.content;
        this.timestamp = m.timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender.getUserId() +
                ", receiver=" + receiver.getUserId()+
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public static class MessageBuilder {
        private Long id;
        private User sender;
        private User receiver;
        private String content;
        private LocalDateTime timestamp;

        public MessageBuilder() {

        }

        public MessageBuilder setSender(User sender) {
            this.sender = sender;
            return this;
        }

        public MessageBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public MessageBuilder setReceiver(User receiver) {
            this.receiver = receiver;
            return this;
        }

        public MessageBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}

