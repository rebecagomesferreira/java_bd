package view; // define o pacote da view

import control.FornecedorController; // importa o controlador de fornecedores
import control.MaterialController; // importa o controlador de materiais
import dao.FornecedorDAO; // importa o dao de fornecedor
import dao.MaterialDAO; // importa o dao de material
import model.Fornecedor; // importa o modelo fornecedor
import model.Material; // importa o modelo material
import java.util.List; // importa lista
import java.util.Scanner; // importa scanner

public class EcoCode6 { // declara a classe principal da view

    private static final Scanner scanner = new Scanner(System.in); // cria o objeto scanner para ler entradas do usuário
    private static final FornecedorController fornecedorCtrl = new FornecedorController(new FornecedorDAO()); // cria o controlador de fornecedor
    private static final MaterialController materialCtrl = new MaterialController(new MaterialDAO()); // cria o controlador de material

    public static void iniciar() { // método que inicia o programa
        int opcao; // cria a variável de opção
        System.out.println("bem-vindo ao ecocode!"); // mensagem de boas-vindas

        do { // inicia o loop do menu
            System.out.println("\n===== menu principal ====="); // mostra título do menu
            System.out.println("1 - gerenciar fornecedores"); // opção 1
            System.out.println("2 - gerenciar materiais"); // opção 2
            System.out.println("0 - sair"); // opção 0 para sair
            System.out.print("escolha , "); // pede a opção
            opcao = scanner.nextInt(); // lê o número digitado
            scanner.nextLine(); // consome a quebra de linha

            switch (opcao) { // verifica a opção escolhida
                case 1 -> menuFornecedores(); // chama o menu de fornecedores
                case 2 -> menuMateriais(); // chama o menu de materiais
                case 0 -> System.out.println("adeus!"); // encerra o programa
                default -> System.out.println("opção inválida."); // caso o usuário digite número errado
            }

        } while (opcao != 0); // repete até o usuário digitar 0

        scanner.close(); // fecha o scanner ao sair
    } // fim do método iniciar

    private static void menuFornecedores() { // menu de fornecedores
        int opcao; // variável de controle
        do {
            System.out.println("\n===menu fornecedores==="); // título do menu
            System.out.println("1 - cadastrar"); // opção 1
            System.out.println("2 - listar"); // opção 2
            System.out.println("3 - atualizar"); // opção 3
            System.out.println("4 - excluir"); // opção 4
            System.out.println("0 - voltar"); // opção 0
            System.out.print("escolha , "); // pede opção
            opcao = scanner.nextInt(); // lê número
            scanner.nextLine(); // consome quebra de linha

            switch (opcao) { // verifica a opção
                case 1 -> cadastrarFornecedor(); // chama método de cadastro
                case 2 -> listarFornecedores(); // chama método de listagem
                case 3 -> atualizarFornecedor(); // chama método de atualização
                case 4 -> excluirFornecedor(); // chama método de exclusão
                case 0 -> System.out.println("voltando!"); // retorna ao menu principal
                default -> System.out.println("opção inválida."); // opção errada
            }

        } while (opcao != 0); // repete até escolher voltar
    } // fim do menu de fornecedores

    private static void cadastrarFornecedor() { // método para cadastrar fornecedor
        System.out.println("\n=== cadastrar fornecedor ==="); // título
        System.out.print("cpf/cnpj , "); // pede cpf ou cnpj
        String cpfCnpj = scanner.nextLine(); // lê valor
        System.out.print("nome , "); // pede nome
        String nome = scanner.nextLine(); // lê nome
        System.out.print("telefone , "); // pede telefone
        String telefone = scanner.nextLine(); // lê telefone
        System.out.print("tipo (pessoa física / jurídica) , "); // pede tipo
        String tipo = scanner.nextLine(); // lê tipo

        fornecedorCtrl.cadastrar(new Fornecedor(cpfCnpj, nome, telefone, tipo)); // cadastra fornecedor
        System.out.println("fornecedor cadastrado com sucesso!"); // confirma cadastro
    } // fim do cadastro de fornecedor

    private static void listarFornecedores() { // método para listar fornecedores
        System.out.println("\n=== lista de fornecedores ==="); // título
        List<Fornecedor> lista = fornecedorCtrl.listar(); // obtém lista de fornecedores
        if (lista.isEmpty()) { // verifica se está vazia
            System.out.println("ainda não tem fornecedor cadastrado."); // mensagem
        } else {
            lista.forEach(System.out::println); // imprime cada fornecedor
        }
    } // fim da listagem de fornecedores

    private static void atualizarFornecedor() { // método para atualizar fornecedor
        System.out.println("\n=== atualizar fornecedor ==="); // título
        System.out.print("id do fornecedor , "); // pede id
        int id = scanner.nextInt(); // lê id
        scanner.nextLine(); // consome quebra de linha

        Fornecedor f = fornecedorCtrl.buscarPorId(id); // busca fornecedor pelo id
        if (f == null) { // verifica se existe
            System.out.println("fornecedor não encontrado."); // mensagem de erro
            return; // sai do método
        }

        System.out.print("novo nome (enter para manter) , "); // pede novo nome
        String nome = scanner.nextLine(); // lê nome
        System.out.print("novo telefone (enter para manter) , "); // pede novo telefone
        String telefone = scanner.nextLine(); // lê telefone
        System.out.print("novo tipo (enter para manter) , "); // pede tipo
        String tipo = scanner.nextLine(); // lê tipo
        System.out.print("novo cpf/cnpj (enter para manter) , "); // pede cpf/cnpj
        String cpfCnpj = scanner.nextLine(); // lê cpf/cnpj

        if (!nome.isEmpty()) f.setNome(nome); // atualiza nome se informado
        if (!telefone.isEmpty()) f.setTelefone(telefone); // atualiza telefone
        if (!tipo.isEmpty()) f.setTipo(tipo); // atualiza tipo
        if (!cpfCnpj.isEmpty()) f.setCpfCnpj(cpfCnpj); // atualiza cpf/cnpj

        fornecedorCtrl.atualizar(f); // salva alterações
        System.out.println("atualização feita."); // confirma atualização
    } // fim da atualização de fornecedor

    private static void excluirFornecedor() { // método para excluir fornecedor
        System.out.print("\nid do fornecedor a excluir , "); // pede id
        int id = scanner.nextInt(); // lê id
        scanner.nextLine(); // consome quebra de linha
        fornecedorCtrl.deletar(id); // chama método de exclusão
        System.out.println("fornecedor removido (se existia)."); // confirma exclusão
    } // fim da exclusão de fornecedor

    private static void menuMateriais() { // menu de materiais
        int opcao; // variável de controle
        do {
            System.out.println("\n--- menu materiais ---"); // título
            System.out.println("1 - cadastrar"); // opção 1
            System.out.println("2 - listar"); // opção 2
            System.out.println("3 - atualizar"); // opção 3
            System.out.println("4 - excluir"); // opção 4
            System.out.println("0 - voltar"); // opção 0
            System.out.print("escolha , "); // pede opção
            opcao = scanner.nextInt(); // lê número
            scanner.nextLine(); // consome quebra de linha

            switch (opcao) { // verifica opção
                case 1 -> cadastrarMaterial(); // chama cadastro
                case 2 -> listarMateriais(); // chama listagem
                case 3 -> atualizarMaterial(); // chama atualização
                case 4 -> excluirMaterial(); // chama exclusão
                case 0 -> System.out.println("voltando..."); // volta ao menu principal
                default -> System.out.println("opção inválida."); // erro
            }

        } while (opcao != 0); // repete até sair
    } // fim do menu materiais

    private static void cadastrarMaterial() { // método para cadastrar material
        System.out.println("\n=== cadastrar material ==="); // título
        System.out.print("tipo , "); // pede tipo
        String tipo = scanner.nextLine(); // lê tipo
        System.out.print("descrição , "); // pede descrição
        String descricao = scanner.nextLine(); // lê descrição
        System.out.print("preço por kg , "); // pede preço
        double preco = scanner.nextDouble(); // lê preço
        scanner.nextLine(); // consome quebra de linha

        materialCtrl.cadastrar(new Material(tipo, descricao, preco)); // cadastra material
        System.out.println("material cadastrado com sucesso!"); // confirma
    } // fim do cadastro de material

    private static void listarMateriais() { // método para listar materiais
        System.out.println("\n=== lista de materiais ==="); // título
        List<Material> lista = materialCtrl.listar(); // obtém lista
        if (lista.isEmpty()) { // verifica se vazia
            System.out.println("nenhum material registrado."); // mensagem
        } else {
            lista.forEach(System.out::println); // imprime cada material
        }
    } // fim da listagem de materiais

    private static void atualizarMaterial() { // método para atualizar material
        System.out.println("\n=== atualizar material ==="); // título
        System.out.print("id do material , "); // pede id
        int id = scanner.nextInt(); // lê id
        scanner.nextLine(); // consome quebra de linha

        Material m = materialCtrl.buscarPorId(id); // busca material
        if (m == null) { // verifica se existe
            System.out.println("material não encontrado."); // mensagem
            return; // sai do método
        }

        System.out.print("novo tipo (enter para manter) , "); // pede tipo
        String tipo = scanner.nextLine(); // lê tipo
        System.out.print("nova descrição (enter para manter) , "); // pede descrição
        String desc = scanner.nextLine(); // lê descrição
        System.out.print("novo preço por kg (-1 para manter) , "); // pede preço
        double preco = scanner.nextDouble(); // lê preço
        scanner.nextLine(); // consome quebra de linha

        if (!tipo.isEmpty()) m.setTipo(tipo); // atualiza tipo
        if (!desc.isEmpty()) m.setDescricao(desc); // atualiza descrição
        if (preco >= 0) m.setPrecoKg(preco); // atualiza preço

        materialCtrl.atualizar(m); // salva alterações
        System.out.println("material atualizado."); // confirma atualização
    } // fim da atualização de material

    private static void excluirMaterial() { // método para excluir material
        System.out.print("\nid do material a excluir , "); // pede id
        int id = scanner.nextInt(); // lê id
        scanner.nextLine(); // consome quebra de linha
        materialCtrl.deletar(id); // exclui material
        System.out.println("material removido (se existia)."); // confirma exclusão
    } // fim da exclusão de material
} // fim da classe ecocode6
