/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import modelDAO.FuncionarioDAO;

/**
 *
 * @author bielm
 */
@WebServlet(name = "ControllerFuncionario", urlPatterns = {"/ControllerFuncionario"})
public class ControllerFuncionario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            String acao = request.getParameter("acao");
            
            if(acao.equals("Cadastrar")){
                
                String nome = request.getParameter("txtNome");
                String endereco = request.getParameter("txtEndereco");
                String rg = request.getParameter("txtRg");
                String cpf = request.getParameter("txtCpf");
                String telefone = request.getParameter("txtTelefone");
                String email = request.getParameter("txtEmail");
                String cidade = request.getParameter("txtCidade");
                String funcao = request.getParameter("txtFuncao");
                
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(nome);
                funcionario.setEndereco(endereco);
                funcionario.setRg(rg);
                funcionario.setCpf(cpf);
                funcionario.setTelefone(telefone);
                funcionario.setEmail(email);
                funcionario.setCidade(cidade);
                funcionario.setFuncao(funcao);
                
                FuncionarioDAO fdao = new FuncionarioDAO();
                fdao.cadastrar(funcionario);
                
                response.sendRedirect("view/Status/Sucesso.jsp");
                
            }else if(acao.equals("Listar")){
                
                FuncionarioDAO fdao = new FuncionarioDAO();
                
                ArrayList<Funcionario> funcionarios = fdao.listar();
                
                request.setAttribute("listarFuncionario", funcionarios);
                
                RequestDispatcher rd = request.getRequestDispatcher("view/Funcionario/ListarFuncionario.jsp");
                rd.forward(request, response);
                
            }else if(acao.equals("Atualizar")){               
                
                String nome = request.getParameter("txtNome");
                String endereco = request.getParameter("txtEndereco");
                String rg = request.getParameter("txtRg");
                String cpf = request.getParameter("txtCpf");
                String telefone = request.getParameter("txtTelefone");
                String email = request.getParameter("txtEmail");
                String cidade = request.getParameter("txtCidade");
                String funcao = request.getParameter("txtFuncao");
                String idFuncionario = request.getParameter("idFuncionario");
                
                Funcionario novoFuncionario = new Funcionario();
                novoFuncionario.setNome(nome);
                novoFuncionario.setEndereco(endereco);
                novoFuncionario.setRg(rg);
                novoFuncionario.setCpf(cpf);
                novoFuncionario.setTelefone(telefone);
                novoFuncionario.setEmail(email);
                novoFuncionario.setCidade(cidade);
                novoFuncionario.setFuncao(funcao);
                novoFuncionario.setId_funcionario(Integer.parseInt(idFuncionario));
                
                FuncionarioDAO fdao = new FuncionarioDAO();
                fdao.atualizar(novoFuncionario);
                
                response.sendRedirect("view/Status/Sucesso.jsp");
                
            }else if(acao.equals("Excluir")){
                
                String idFuncionario = request.getParameter("idFuncionario");
                
                Funcionario excluirFuncionario = new Funcionario();
                excluirFuncionario.setId_funcionario(Integer.parseInt(idFuncionario));
                
                FuncionarioDAO fdao = new FuncionarioDAO();
                fdao.excluir(excluirFuncionario);
                
                response.sendRedirect("view/Status/Sucesso.jsp");
            }
            
        } catch (Exception e) {
            request.setAttribute("erro", e);
            RequestDispatcher rd = request.getRequestDispatcher("/view/Status/Erro.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
