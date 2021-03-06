package micro_services.email_sender.model;


public class Email {

    private String toAddress;

    private String password;

    private String fromAddress;

    private String subject;

    private String message;

    private EmailStatus status;

    public Email(String to, String pwd, String from, String subject, String msg) {
        this.toAddress = to;
        this.password = pwd;
        this.fromAddress = from;
        this.subject = subject;
        this.message = msg;
    }

    public String getToAddress() {
        return toAddress;
    }


    public String getPassword() {
        return password;
    }


    public String getFromAddress() {
        return fromAddress;
    }


    public String getSubject() {
        return subject;
    }


    public String getMessage() {
        return message;
    }


    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Email{" +
                "toAddress='" + toAddress + '\'' +
                ", password='" + password + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
