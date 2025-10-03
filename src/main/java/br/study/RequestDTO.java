package br.study;



public class RequestDTO {

    private String action;
    private Employee data;

    public RequestDTO() {
    }

    public RequestDTO(String action, Employee data) {
        this.action = action;
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Employee getData() {
        return data;
    }

    public void setData(Employee data) {
        this.data = data;
    }
}
