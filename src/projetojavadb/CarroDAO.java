package projetojavadb;

import java.sql.*;
import java.util.*;


public class CarroDAO {
    
    private Connection con;
    
    public CarroDAO(Connection con){
        setCon(con);       
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String inserir (CarroBean carro){
        String sql = "inserir into carro(placa,cor,descricao) values (?,?,?) ";
        try {
            PreparedStatement ps = getCon().prepareStatement (sql);
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso.";
            } else {
                return "Erro ao inserir no banco.";
            }
        }   
        
        catch (SQLException e){
            return e.getMessage();
        }
    }
        public String alterar(CarroBean carro) {
            String sql = "update carro set cor = ?,descricao = ?";
            sql += "where placa = ?";
            
            try {
                PreparedStatement ps = getCon().prepareStatement(sql);
                ps.setString(1, carro.getCor());
                ps.setString(2, carro.getDescricao());
                ps.setString(3, carro.getPlaca());
                
                if (ps.executeUpdate() > 0) {
                    return "Foi alterado com sucesso";
                }else {
                    return "Erro ao realizar a alteração";
                }
            }
            catch (SQLException e){
                    return e.getMessage();
                    }
        }
        public String excluir (CarroBean carro) {
            String sql = "delete from carro where placa = ?";
            
            try {
                PreparedStatement ps = getCon().prepareStatement(sql);
                ps.setString(1, carro.getPlaca());
                
                
               if (ps.executeUpdate() > 0) {
                   return "Deletado com Sucesso";
               }else {
                   return "Erro ao deletar";
               }
            }
            catch (SQLException e) {
                return e.getMessage();
            }
        }
        public List<CarroBean> ListarTodos(){
            String sql = "select * from carro ";
            List<CarroBean> listaCarro = new ArrayList<CarroBean>();
            
            try{
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null){
                    while (rs.next()){
                        CarroBean cb = new CarroBean();
                        cb.setPlaca(rs.getString(1));
                        cb.setCor(rs.getString(2));
                        cb.setDescricao(rs.getString(3));
                        listaCarro.add(cb);
                    }
                    return listaCarro;
                }else {
                    return null;
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
    }
    
    

