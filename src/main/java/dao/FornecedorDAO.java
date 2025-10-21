package dao;

import model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void inserir(Fornecedor f) {
        String sql = "INSERT INTO FORNECEDOR (cpf_cnpj, nome, telefone, tipo_fornecedor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getCpfCnpj());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getTelefone());
            stmt.setString(4, f.getTipo());
            stmt.executeUpdate();
            System.out.println("Fornecedor cadastrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir fornecedor: " + e.getMessage());
        }
    }

    public List<Fornecedor> listar() {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM FORNECEDOR";
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id_fornecedor"));
                f.setCpfCnpj(rs.getString("cpf_cnpj"));
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setTipo(rs.getString("tipo_fornecedor"));
                lista.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        }
        return lista;
    }

    public Fornecedor buscarPorId(int id) {
        String sql = "SELECT * FROM FORNECEDOR WHERE id_fornecedor = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id_fornecedor"));
                f.setCpfCnpj(rs.getString("cpf_cnpj"));
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setTipo(rs.getString("tipo_fornecedor"));
                return f;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedor: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Fornecedor f) {
        StringBuilder sql = new StringBuilder("UPDATE FORNECEDOR SET ");
        boolean primeiro = true;

        if (f.getNome() != null && !f.getNome().isEmpty()) {
            sql.append("nome = ?");
            primeiro = false;
        }
        if (f.getTelefone() != null && !f.getTelefone().isEmpty()) {
            if (!primeiro) sql.append(", ");
            sql.append("telefone = ?");
            primeiro = false;
        }
        if (f.getTipo() != null && !f.getTipo().isEmpty()) {
            if (!primeiro) sql.append(", ");
            sql.append("tipo_fornecedor = ?");
            primeiro = false;
        }
        if (f.getCpfCnpj() != null && !f.getCpfCnpj().isEmpty()) {
            if (!primeiro) sql.append(", ");
            sql.append("cpf_cnpj = ?");
        }

        sql.append(" WHERE id_fornecedor = ?");

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (f.getNome() != null && !f.getNome().isEmpty()) stmt.setString(idx++, f.getNome());
            if (f.getTelefone() != null && !f.getTelefone().isEmpty()) stmt.setString(idx++, f.getTelefone());
            if (f.getTipo() != null && !f.getTipo().isEmpty()) stmt.setString(idx++, f.getTipo());
            if (f.getCpfCnpj() != null && !f.getCpfCnpj().isEmpty()) stmt.setString(idx++, f.getCpfCnpj());

            stmt.setInt(idx, f.getId());
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Fornecedor atualizado.");
            } else {
                System.out.println("Nenhum fornecedor com este ID encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM FORNECEDOR WHERE id_fornecedor = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Fornecedor deletado.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar fornecedor: " + e.getMessage());
        }
    }
}
