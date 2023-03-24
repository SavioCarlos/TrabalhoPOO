import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    private List<Tarefa> tarefasPendentes;
    private List<Tarefa> tarefasConcluidas;
    private String usuario;

    public GerenciadorTarefas() {
        tarefasPendentes = new ArrayList<>();
        tarefasConcluidas = new ArrayList<>();
        this.usuario = usuario;
        carregarTarefas();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefasPendentes.add(tarefa);
        salvarTarefas();
    }

    public void concluirTarefa(Tarefa tarefa) {
        tarefa.setConcluida(true);
        tarefa.setDataConclusao(LocalDate.now());
        tarefasPendentes.remove(tarefa);
        tarefasConcluidas.add(tarefa);
        salvarTarefas();
    }

    public void exibirTarefasPendentes() {
        System.out.println("Tarefas pendentes:");
        for (Tarefa tarefa : tarefasPendentes) {
            System.out.println("- " + tarefa.getTitulo());
        }
    }

    public void exibirTarefasConcluidas() {
        System.out.println("Tarefas concluídas:");
        for (Tarefa tarefa : tarefasConcluidas) {
            System.out.println("- " + tarefa.getTitulo() + " (concluída em " + tarefa.getDataConclusao() + ")");
        }
    }

    public List<Tarefa> getTarefasPendentes() {
        return tarefasPendentes;
    }
    
    private void carregarTarefas() { 
        PersistenciaDados persistencia = new PersistenciaDados();
        tarefasPendentes = persistencia.carregarTarefas(usuario);
    }

    private void salvarTarefas() {
        PersistenciaDados persistencia = new PersistenciaDados();
        persistencia.salvarTarefas(tarefasPendentes, tarefasConcluidas, usuario);
    }
}
