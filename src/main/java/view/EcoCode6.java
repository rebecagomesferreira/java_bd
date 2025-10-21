package view;

import control.FornecedorController;
import control.MaterialController;
import dao.FornecedorDAO;
import dao.MaterialDAO;
import model.Fornecedor;
import model.Material;

import java.util.List;
import java.util.Scanner;

public class EcoCode6 {

    private static final Scanner sc = new Scanner(System.in);
    private static final FornecedorController fornecedorCtrl = new FornecedorController(new FornecedorDAO());
    private static final MaterialController materialCtrl = new MaterialController(new MaterialDAO());

    public static void main(String[] args) {
        int opcao;

        System.out.println("Bem-vindo ao EcoCode!");

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Gerenciar fornecedores");
            System.out.println("2 - Gerenciar materiais");
            System.out.println("0 - Sair");
            System.out.print("Escolha , ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuFornecedores();
                case 2 -> menuMateriais();
                case 0 -> System.out.println("Adeus!");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void menuFornecedores() {
        int opcao;
        do {
            System.out.println("\n===MENU FORNECEDORES===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha , ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarFornecedor();
                case 2 -> listarFornecedores();
                case 3 -> atualizarFornecedor();
                case 4 -> excluirFornecedor();
                case 0 -> System.out.println("Voltando!");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarFornecedor() {
        System.out.println("\n=== CADASTRAR FORNECEDOR ===");
        System.out.print("CPF/CNPJ , ");
        String cpfCnpj = sc.nextLine();
        System.out.print("Nome , ");
        String nome = sc.nextLine();
        System.out.print("Telefone , ");
        String telefone = sc.nextLine();
        System.out.print("Tipo (Pessoa Física / Jurídica) , ");
        String tipo = sc.nextLine();

        fornecedorCtrl.cadastrar(new Fornecedor(cpfCnpj, nome, telefone, tipo));
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    private static void listarFornecedores() {
        System.out.println("\n=== LISTA DE FORNECEDORES ===");
        List<Fornecedor> lista = fornecedorCtrl.listar();
        if (lista.isEmpty()) {
            System.out.println("Ainda não tem fornecedor cadastrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void atualizarFornecedor() {
        System.out.println("\n=== ATUALIZAR FORNECEDOR ===");
        System.out.print("ID do fornecedor , ");
        int id = sc.nextInt();
        sc.nextLine();

        Fornecedor f = fornecedorCtrl.buscarPorId(id);
        if (f == null) {
            System.out.println("Fornecedor nao encontrado.");
            return;
        }

        System.out.print("Novo nome (Enter para manter) , ");
        String nome = sc.nextLine();
        System.out.print("Novo telefone (Enter para manter) , ");
        String telefone = sc.nextLine();
        System.out.print("Novo tipo (Enter para manter) , ");
        String tipo = sc.nextLine();
        System.out.print("Novo CPF/CNPJ (Enter para manter) , ");
        String cpfCnpj = sc.nextLine();

        if (!nome.isEmpty()) f.setNome(nome);
        if (!telefone.isEmpty()) f.setTelefone(telefone);
        if (!tipo.isEmpty()) f.setTipo(tipo);
        if (!cpfCnpj.isEmpty()) f.setCpfCnpj(cpfCnpj);

        fornecedorCtrl.atualizar(f);
        System.out.println("Atualização feita.");
    }

    private static void excluirFornecedor() {
        System.out.print("\nID do fornecedor a excluir , ");
        int id = sc.nextInt();
        sc.nextLine();
        fornecedorCtrl.deletar(id);
        System.out.println("Fornecedor removido (se existia).");
    }

    private static void menuMateriais() {
        int opcao;
        do {
            System.out.println("\n--- MENU MATERIAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha , ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarMaterial();
                case 2 -> listarMateriais();
                case 3 -> atualizarMaterial();
                case 4 -> excluirMaterial();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarMaterial() {
        System.out.println("\n=== CADASTRAR MATERIAL ===");
        System.out.print("Tipo , ");
        String tipo = sc.nextLine();
        System.out.print("Descrição , ");
        String descricao = sc.nextLine();
        System.out.print("Preço por kg , ");
        double preco = sc.nextDouble();
        sc.nextLine();

        materialCtrl.cadastrar(new Material(tipo, descricao, preco));
        System.out.println("Material cadastrado com sucesso!");
    }

    private static void listarMateriais() {
        System.out.println("\n=== LISTA DE MATERIAIS ===");
        List<Material> lista = materialCtrl.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum material registrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void atualizarMaterial() {
        System.out.println("\n=== ATUALIZAR MATERIAL ===");
        System.out.print("ID do material , ");
        int id = sc.nextInt();
        sc.nextLine();

        Material m = materialCtrl.buscarPorId(id);
        if (m == null) {
            System.out.println("Material não encontrado.");
            return;
        }

        System.out.print("Novo tipo (Enter para manter) , ");
        String tipo = sc.nextLine();
        System.out.print("Nova descrição (Enter para manter) , ");
        String desc = sc.nextLine();
        System.out.print("Novo preço por kg (-1 para manter) , ");
        double preco = sc.nextDouble();
        sc.nextLine();

        if (!tipo.isEmpty()) m.setTipo(tipo);
        if (!desc.isEmpty()) m.setDescricao(desc);
        if (preco >= 0) m.setPrecoKg(preco);

        materialCtrl.atualizar(m);
        System.out.println("Material atualizado.");
    }

    private static void excluirMaterial() {
        System.out.print("\nID do material a excluir , ");
        int id = sc.nextInt();
        sc.nextLine();
        materialCtrl.deletar(id);
        System.out.println("Material removido (se existia).");
    }
}
