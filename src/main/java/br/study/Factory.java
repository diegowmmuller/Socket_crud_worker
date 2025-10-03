package br.study;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    private final List<Employee> workers;


    public Factory() {
        this.workers = new ArrayList<>();
        populateList();
    }

    public Employee findOne(String name, String email){
        if(name == null || email == null){
            return null;
        }

        return workers.stream().filter(w -> w.getName().equalsIgnoreCase(name) && w.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    public List<Employee> findAll(){
        return new ArrayList<>(workers);
    }

    public void addEmployee(Employee emp){
        workers.add(emp);
    }

    public String deleteEmployee(String email){
        if(email == null){
            return "necessario enviar um email para busca";        }

        boolean removido = workers.removeIf(w -> w.getEmail().equalsIgnoreCase(email));

        return removido ? "Removido com sucesso":"Nenhum trabalhador encontrado";

    }

    public void populateList(){
        addEmployee(new Employee("Diego Maglia", "diego.maglia@email.com", 3500.0));
        addEmployee(new Employee("Ana Souza", "ana.souza@email.com", 4200.0));
        addEmployee(new Employee("Carlos Lima", "carlos.lima@email.com", 3900.0));
        addEmployee(new Employee("Mariana Rocha", "mariana.rocha@email.com", 4800.0));
        addEmployee(new Employee("Paulo Santos", "paulo.santos@email.com", 4100.0));
        addEmployee(new Employee("Juliana Alves", "juliana.alves@email.com", 3700.0));
        addEmployee(new Employee("Rafael Costa", "rafael.costa@email.com", 4500.0));
    }



}


