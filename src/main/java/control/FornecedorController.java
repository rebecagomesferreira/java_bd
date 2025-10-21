package control;

import dao.FornecedorDAO;
import model.Fornecedor;
import java.util.List;

public class FornecedorController {
    private FornecedorDAO dao;

    public FornecedorController(FornecedorDAO dao) {
        this.dao = dao;
    }

    public void cadastrar(Fornecedor f) {
        dao.inserir(f);
    }

    public List<Fornecedor> listar() {
        return dao.listar();
    }

    public Fornecedor buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizar(Fornecedor f) {
        dao.atualizar(f);
    }

    public void deletar(int id) {
        dao.deletar(id);
    }
}
