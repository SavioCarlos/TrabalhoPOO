import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistenciaDados {
    private static final String PASTA_DADOS = "dados";
    
    public static void salvarTarefas(List<Tarefa> tarefas, String usuario) throws IOException {
        File pastaUsuario = new File(PASTA_DADOS, usuario);
        if (!pastaUsuario.exists()) {
            pastaUsuario.mkdirs();
        }
        
        File arquivoTarefas = new File(pastaUsuario, "tarefas.dat");
        FileWriter escritor = new FileWriter(arquivoTarefas);
        for (Tarefa tarefa : tarefas) {
            escritor.write(tarefa.getTitulo() + ";" + tarefa.getDescricao() + ";" +
                    tarefa.getDataCriacao() + ";" + tarefa.getDataConclusao() + "\n");
        }
        escritor.close();
    }
    
    public static List<Tarefa> carregarTarefas(String usuario) throws IOException {
        List<Tarefa> tarefas = new ArrayList<>();
        File pastaUsuario = new File(PASTA_DADOS, usuario);
        if (!pastaUsuario.exists()) {
            return tarefas;
        }
        
        File arquivoTarefas = new File(pastaUsuario, "tarefas.dat");
        if (!arquivoTarefas.exists()) {
            return tarefas;
        }
        
        Scanner scanner = new Scanner(arquivoTarefas);
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split(";");
            Tarefa tarefa = new Tarefa(campos[0], campos[1]);
            tarefa.setDataCriacao(LocalDate.parse(campos[2]));
            if (!campos[3].equals("null")) {
                tarefa.setDataConclusao(LocalDate.parse(campos[3]));
            }
            tarefas.add(tarefa);
        }
        scanner.close();
        
        return tarefas;
    }
}