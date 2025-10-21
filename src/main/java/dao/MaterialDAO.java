package dao;

import model.Material;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public void inserir(Material m) {
        String sql = "INSERT INTO MATERIAL (tipo, descricao, preco_kg) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getTipo());
            stmt.setString(2, m.getDescricao());
            stmt.setDouble(3, m.getPrecoKg());
            stmt.executeUpdate();
            System.out.println("Material cadastrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir material: " + e.getMessage());
        }
    }

    public List<Material> listar() {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT * FROM MATERIAL";
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("id_material"));
                m.setTipo(rs.getString("tipo"));
                m.setDescricao(rs.getString("descricao"));
                m.setPrecoKg(rs.getDouble("preco_kg"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar materiais: " + e.getMessage());
        }
        return lista;
    }

    public Material buscarPorId(int id) {
        String sql = "SELECT * FROM MATERIAL WHERE id_material = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("id_material"));
                m.setTipo(rs.getString("tipo"));
                m.setDescricao(rs.getString("descricao"));
                m.setPrecoKg(rs.getDouble("preco_kg"));
                return m;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar material: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Material m) {
        StringBuilder sql = new StringBuilder("UPDATE MATERIAL SET ");
        boolean primeiro = true;

        if (m.getTipo() != null && !m.getTipo().isEmpty()) {
            sql.append("tipo = ?");
            primeiro = false;
        }
        if (m.getDescricao() != null && !m.getDescricao().isEmpty()) {
            if (!primeiro) sql.append(", ");
            sql.append("descricao = ?");
            primeiro = false;
        }
        if (m.getPrecoKg() >= 0) {
            if (!primeiro) sql.append(", ");
            sql.append("preco_kg = ?");
        }

        sql.append(" WHERE id_material = ?");

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (m.getTipo() != null && !m.getTipo().isEmpty()) stmt.setString(idx++, m.getTipo());
            if (m.getDescricao() != null && !m.getDescricao().isEmpty()) stmt.setString(idx++, m.getDescricao());
            if (m.getPrecoKg() >= 0) stmt.setDouble(idx++, m.getPrecoKg());
            stmt.setInt(idx, m.getId());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Material atualizado.");
            } else {
                System.out.println("Nenhum material encontrado com esse ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar material: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM MATERIAL WHERE id_material = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Material deletado.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar material: " + e.getMessage());
        }
    }
}
