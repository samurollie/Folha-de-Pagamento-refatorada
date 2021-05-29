package src.payment.methods;

public class CheckMail extends CheckHand{
    private String from;
    private String to;
    private String address;
    
    public CheckMail(String destination, String from, String to, String address) {
        super(destination);
        this.setFrom(from);
        this.setTo(to);
        this.setAddress(address);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
