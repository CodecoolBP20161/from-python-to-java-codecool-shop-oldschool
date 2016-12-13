package micro_services.model;


public class Email {

    private String toAddress;

    private String fromAddress;

    private String subject;

    private String message;

    private EmailStatus status;

    public Email(String to, String from, String subject, String msg) {
        this.toAddress = to;
        this.fromAddress = from;
        this.subject = subject;
        this.message = msg;
        this.status = EmailStatus.IN_PROGRESS;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }
}
