/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criadorpastassi.controller;

import com.google.gson.Gson;
import criadorpastassi.model.Pasta;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author thiag
 */
public class FXMLMainController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnModificarSubPastas;
    @FXML
    private TextField textFieldMateria;
    @FXML
    private Button btnCriarPasta;
    @FXML
    private Label labelSubPastas;
    
    private static final String myConfigPath = "src/criadorpastassi/configs/MyConfigs.json";
    public Pasta pastaPadrao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInitialConfig();
        this.textFieldMateria.setText(pastaPadrao.getNome());
        this.labelSubPastas.setText(imprimirPasta(pastaPadrao, ""));
       
    }
    
    public void btnCriarPastClicked(){
        if(verificarTextCurso()){
            Pasta p = new Pasta(this.textFieldMateria.getText());
            p.setSubPastas(criarSubPastaInicial());
            criarPasta(p, "");
        }
    }
    
    public void btnModificarSubPastas(){
        
    }
    
    private boolean verificarTextCurso(){
        if(this.textFieldMateria.getText().length() != 0){
            return true;
        }
        return false;
    }
    
    private void criarPasta(Pasta pasta, String path) {
        File f = new File(path + pasta.getNome());
        f.mkdirs();
        path = f.getAbsolutePath() + "\\";
        System.out.println("nt nte " + path);
        for(int i = 0; i < pasta.getSubPastas().size(); i++){
            criarPasta(pasta.getSubPastas().get(i), path);
        }
    }
    
    private void setInitialConfig() {
        try {
            File f = new File(myConfigPath);
            String json = new String(Files.readAllBytes(f.toPath()));
            if(json.length() != 0){
                Gson gson = new Gson();
                pastaPadrao = gson.fromJson(json, Pasta.class);
            }
            return;
        } catch (Exception e) {
            System.out.println("Arquivo Padrao nao encontrado !!");
            System.out.println("Criando novo Arquivo Padrao !!");
        }
        
        Pasta pasta = new Pasta("teste");
        pasta.setSubPastas(criarSubPastaInicial());
        Gson gson = new Gson();
        try {
            File f = new File("src/criadorpastassi/configs/MyConfigs.json");
            System.out.println(f.getAbsolutePath());
            FileWriter fw = new FileWriter(f);
            fw.write(gson.toJson(pasta));
            fw.close();
            
        } catch (Exception e) {
            System.out.println("Error ao criar arquivo");
            System.err.println(e.getMessage());
        }
        
    }
  
    
    private ArrayList<Pasta> criarSubPastaInicial(){
        ArrayList<Pasta> listaP = new ArrayList<>();
        listaP.add(new Pasta("Exercícios"));
        listaP.add(new Pasta("Livros"));
        listaP.add(new Pasta("Slides"));
        listaP.add(new Pasta("Trabalhos"));
        listaP.get(0).getSubPastas().add(new Pasta("Práticos"));
        listaP.get(0).getSubPastas().add(new Pasta("Teóricos"));
        listaP.get(0).getSubPastas().get(0).getSubPastas().add(new Pasta("Códigos"));
        listaP.get(0).getSubPastas().get(0).getSubPastas().add(new Pasta("Observações"));
        listaP.get(1).getSubPastas().add(new Pasta("Completos"));
        listaP.get(1).getSubPastas().add(new Pasta("Parciais"));
        
        return listaP;
    }
    
    private String imprimirPasta(Pasta pasta, String resultado) {
        resultado += "->" + pasta.getNome() + "\n";
        String spacos = insertEspacos(resultado);
        for(int i = 0; i < pasta.getSubPastas().size(); i++){ 
            resultado += spacos;
            resultado = imprimirPasta(pasta.getSubPastas().get(i), resultado);
        }
        return resultado;
    }
    
    private String insertEspacos(String valor){
        String retorno = "";
        int ultimoBr = valor.lastIndexOf("\n");
       
        for(int i = ultimoBr - 1; i >= 0; i--){
            if(valor.charAt(i) == '\n'){
                break;
            }
            retorno += " ";
        }
        
        return retorno + "";
    }
    
}
