package com.api;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * Azure Functions with HTTP Trigger.
 */

public class Function {

    List<Funcionario> funcionarios = new ArrayList<>();

    public List<Funcionario> testlist() {

        funcionarios.add(new Funcionario(new Long(1), "mrl", 67, 981018186));
        funcionarios.add(new Funcionario(new Long(2), "ramos", 67, 935243139));

        return funcionarios;
    }

    @FunctionName("createfuncionario")
    public Funcionario createFuncionario(@HttpTrigger(name = "createFuncionario", methods = {
            HttpMethod.POST }, route = "funcionario") Funcionario funcionario) {

        Funcionario func = new Funcionario();
        func.setID(funcionario.getID());
        func.setNome(funcionario.getNome());
        func.setIdade(funcionario.getIdade());
        func.setSalário(funcionario.getSalário());

        return func;
    }

    @FunctionName("readfuncionario")
    public List<Funcionario> readFuncionario(@HttpTrigger(name = "readFuncinario", methods = {
            HttpMethod.GET }, route = "funcionario") String funcionario) {

        return funcionarios;
    }

    @FunctionName("editfuncionario")
    public Funcionario editFuncionario(@HttpTrigger(name = "editFuncionario", methods = {
            HttpMethod.PUT }, route = "funcionario") Funcionario funcionario) {

        for (Funcionario func : funcionarios) {
            if(func.ID == funcionario.ID){
                func.setNome(funcionario.getNome());
                func.setIdade(funcionario.getIdade());
                func.setSalário(funcionario.getSalário());
            }
        }
        return funcionario;
    }

    @FunctionName("deletefuncionario")
    public String deleteFuncionario(@HttpTrigger(name = "deleteFuncionario", methods = {
            HttpMethod.DELETE }, route = "funcionario") int id) {
   
        for (Funcionario func : funcionarios) {
            if(func.ID == id){
                funcionarios.remove(id);
            }
        }     

        return "ok";
    }

}

@Data
@Getter
@Setter
@AllArgsConstructor
class Funcionario {

    Long ID;
    String nome;
    int idade;
    double salário;

    Funcionario(){
    }

}
