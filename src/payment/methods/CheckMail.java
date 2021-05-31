package src.payment.methods;

public class CheckMail extends CheckHand{
    private String from;
    private String to;
    private String cep;
    private String address;

    @Override
    public int toInt() {
        return 3;
    }

    @Override
    public String toString() {
        return "Cheque pelos Correios";
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
