package br.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    private final Integer PORTA;
    private ObjectMapper mapper;
    private Factory factory;



    public Server(Integer PORTA) {
        this.PORTA = PORTA;
        this.mapper = new ObjectMapper();
        this.factory = new Factory();

    }

    public void createServer(){
        try(ServerSocket server = new ServerSocket(PORTA)){
            System.out.println("Server rodando na porta: " + PORTA);

            while (true){
                Socket client = server.accept();
                System.out.println("Cliente entrou: " + client.getInetAddress().getHostAddress());

                new Thread(()->{
                    try(BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true)
                    ){
                        String clientRequest = in.readLine();
                        RequestDTO requestObject = mapper.readValue(clientRequest, RequestDTO.class);

                        String clientResponse = actionType(requestObject);
                        out.println(clientResponse);

                    }catch (IOException e){
                        System.out.println("IO error"+e.getMessage());
                    }
                }).start();
            }

        }catch (IOException e){
            System.out.println("IO error"+e.getMessage());
        }
    }


    private String actionType(RequestDTO req){
        String action = req.getAction();
        Employee data = req.getData();

        return switch (action) {
            case "findOne" -> findOne(data);
            case "create" -> create(data);
            case "delete" -> delete(data);
            case "findAll" -> findAll();
            default -> "{\"status\":\"error\",\"message\":\"Action desconhecida\"}";
        };
    }

    private String findOne(Employee data) {
        Employee emp = factory.findOne(data.getName(), data.getEmail());
        System.out.println(emp.getName());
        System.out.println(emp.getSalary());
        System.out.println();
        String response;

        try {
            ResponseDTO<Employee> res = new ResponseDTO<>("Success", emp);
            response = mapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao processar objeto -> string: |metodo findOne|" + e.getMessage());
            response = "{\"error\":\"Falha ao processar objeto\"}";
        }
        System.out.println(response);
        return response;
    }


    private String create(Employee data){
        factory.addEmployee(data);
        ResponseDTO<Void> res = new ResponseDTO<>("Success");
        String response;
        try{
            response = mapper.writeValueAsString(res);
        } catch (JsonProcessingException e){
            System.out.println("Erro ao processar objeto -> string: |metodo create|" + e.getMessage());
            response = "{\"error\":\"Falha ao processar objeto\"}";
        }
        return response;
    }

    private String findAll(){
        List<Employee> employees = factory.findAll();
        ResponseDTO<List<Employee>> res = new ResponseDTO<>("Success", employees);
        String response;
        try{
            response = mapper.writeValueAsString(res);
        } catch (JsonProcessingException e){
            System.out.println("Erro ao processar objeto -> string: |metodo findAll|" + e.getMessage());
            response = "{\"error\":\"Falha ao processar objeto\"}";
        }
        return response;
    }

    private String delete(Employee data){
        factory.deleteEmployee(data.getEmail());
        ResponseDTO<Void> res = new ResponseDTO<>("Sucess");
        String response;
        try{
            response = mapper.writeValueAsString(res);
        } catch (JsonProcessingException e){
            System.out.println("Erro ao processar objeto -> string: |metodo delete|" + e.getMessage());
            response = "{\"error\":\"Falha ao processar objeto\"}";
        }
        return response;
    }

}
