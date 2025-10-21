package control;

import dao.MaterialDAO;
import model.Material;
import java.util.List;

public class MaterialController {
    private MaterialDAO dao;

    public MaterialController(MaterialDAO dao) {
        this.dao = dao;
    }

    public void cadastrar(Material m) {
        dao.inserir(m);
    }

    public List<Material> listar() {
        return dao.listar();
    }

    public Material buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizar(Material m) {
        dao.atualizar(m);
    }

    public void deletar(int id) {
        dao.deletar(id);
    }
}
