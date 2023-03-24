import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

public class Principal {
   private static GerenciadorTarefas gerenciadorTarefas;
   private static Scanner scanner;


   public static void main(String[] args) {
     gerenciadorTarefas = new GerenciadorTarefas();
     scanner = new Scanner(System.in);
     boolean sair = false;
     int opcao;
     while (!sair) {
         exibirMenu();
         try {
             opcao = scanner.nextInt();
             scanner.nextLine();
             switch (opcao) {
                 case 1:
                     criarNovaTarefa();
                     break;
                 case 2:
                     concluirTarefa();
                     break;
                 case 3:
                     exibirTarefasPendentes();
                     break;
                 case 4:
                     exibirTarefasConcluidas();
                     break;
                 case 5:
                     sair = true;
                     break;
                 default:
                     System.out.println("Opção inválida.");
                     break;
             }
         } catch (InputMismatchException e) {
             System.out.println("Opção inválida. Digite novamente.");
             scanner.nextLine();
         }
     }
   }

   private static void exibirMenu() {
       System.out.println("Digite uma opção:");
       System.out.println("1 - Criar nova tarefa");
       System.out.println("2 - Concluir tarefa");
       System.out.println("3 - Exibir tarefas pendentes");
       System.out.println("4 - Exibir tarefas concluídas");
       System.out.println("5 - Sair");
   }
   
   private static void criarNovaTarefa() {
       System.out.println("Digite o título da tarefa:");
       String titulo = scanner.nextLine();
       System.out.println("Digite a descrição da tarefa:");
       String descricao = scanner.nextLine();
       Tarefa tarefa = new Tarefa(titulo, descricao);
       gerenciadorTarefas.adicionarTarefa(tarefa);
       System.out.println("Tarefa criada com sucesso.");
   }
   
   private static void concluirTarefa() {
       exibirTarefasPendentes();
       System.out.println("Digite o número da tarefa a ser concluída:");
       int numero = scanner.nextInt();
       scanner.nextLine();
       List<Tarefa> tarefas = gerenciadorTarefas.getTarefasPendentes();
       if (numero > 0 && numero <= tarefas.size()) {
           Tarefa tarefa = tarefas.get(numero - 1);
           tarefa.setDataConclusao(LocalDate.now());
           gerenciadorTarefas.concluirTarefa(tarefa);
           System.out.println("Tarefa " + numero + " concluída com sucesso.");
       } 
       else {
           System.out.println("Número de tarefa inválido.");
       }
   }
   
   private static void exibirTarefasPendentes() {
       gerenciadorTarefas.exibirTarefasPendentes();
   }
   
   private static void exibirTarefasConcluidas() {
       gerenciadorTarefas.exibirTarefasConcluidas();
  }
}