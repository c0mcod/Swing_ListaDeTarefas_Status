package br.com.lista.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import br.com.lista.dao.ListaDAO;
import br.com.lista.model.ListadeAtividade;
import br.com.lista.table.*;
public class InterfacePrincipal extends TableMethod{
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws SQLException {
		ListaDAO dao = new ListaDAO();
		ListadeAtividade listaAtividade = new ListadeAtividade();
		TableMethod tm = new TableMethod();
		
		JFrame janela = new JFrame();
		janela.setSize(600, 400);
		janela.setTitle("Gerenciador de atividade");
		janela.setDefaultCloseOperation(janela.EXIT_ON_CLOSE);

		JButton btnC = new JButton("Criar atividade");
		JButton btnU = new JButton("Atualizar atividade");
		JButton btnD = new JButton("Deletar atividade");
		
		btnC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame janelaC = new JFrame();
				janelaC.setSize(500,300);
				janelaC.setTitle("Criar atividade");
				janelaC.setDefaultCloseOperation(janelaC.DISPOSE_ON_CLOSE);
				
				JLabel labelTitulo = new JLabel("Titulo");
				JTextField campoTitulo = new JTextField(20);
				
				JLabel labelDescricao = new JLabel("Descrição");
				JTextField campoDescricao = new JTextField(20);
				
				JRadioButton atividadePendente = new JRadioButton("Pendente");
				JRadioButton atividadeFeita = new JRadioButton("Feita");
				ButtonGroup grupo = new ButtonGroup();
				grupo.add(atividadePendente);
				grupo.add(atividadeFeita);
				
				
				JButton enviarTarefa = new JButton("Enviar");
				
				enviarTarefa.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String titleBD = campoTitulo.getText();
						String descBD = campoDescricao.getText();
						
						
						if(atividadePendente.isSelected()) {
							String ap = "Pendente";
							listaAtividade.setStatus(ap);
						}
						else if(atividadeFeita.isSelected()) {
							String af = "Feita";
							listaAtividade.setStatus(af);
						}
						listaAtividade.setData_criacao(new Date());
						
						listaAtividade.setTitulo(titleBD);
						listaAtividade.setDescricao(descBD);
						try {
							dao.inserirAtividade(listaAtividade);
							tm.atualizarTable();
							janelaC.dispose();
						
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(enviarTarefa, "Atividade Cadastrada!");
						
						
					}
				});
				
				
				janelaC.add(labelTitulo);
				janelaC.add(campoTitulo);
				janelaC.add(labelDescricao);
				janelaC.add(campoDescricao);
				janelaC.add(atividadeFeita);
				janelaC.add(atividadePendente);
				janelaC.add(enviarTarefa);
				
				janelaC.setLayout(new GridLayout(10,5));
				
				janelaC.setVisible(true);
				
			}
		});

		btnU.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame janelaU = new JFrame();
				janelaU.setSize(500,300);
				janelaU.setTitle("Atualizar Atividade");
				janelaU.setDefaultCloseOperation(janelaU.DISPOSE_ON_CLOSE);
				
				JLabel labelTitulo = new JLabel("Titulo");
				JTextField campoTitulo = new JTextField(20);
				
				JLabel labelDescricao = new JLabel("Descrição");
				JTextField campoDescricao = new JTextField(20);
				
				JLabel labelId = new JLabel("ID");
				JTextField campoId = new JTextField(10);
				
				JRadioButton atividadePendente = new JRadioButton("Pendente");
				JRadioButton atividadeFeita = new JRadioButton("Feita");
				ButtonGroup grupo = new ButtonGroup();
				grupo.add(atividadePendente);
				grupo.add(atividadeFeita);
				
				
				JButton enviarTarefa = new JButton("Enviar");
				
				enviarTarefa.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String titleBD = campoTitulo.getText();
						String descBD = campoDescricao.getText();
						String idBd = campoId.getText();
						int idBdToInt = Integer.parseInt(idBd);
						
						
						if(atividadePendente.isSelected()) {
							String ap = "Pendente";
							listaAtividade.setStatus(ap);
						}
						else if(atividadeFeita.isSelected()) {
							String af = "Feita";
							listaAtividade.setStatus(af);
						}
						listaAtividade.setData_criacao(new Date());
						
						listaAtividade.setId(idBdToInt);
						listaAtividade.setTitulo(titleBD);
						listaAtividade.setDescricao(descBD);
						
						try {
							dao.atualizarAtividade(listaAtividade);
							tm.atualizarTable();
							janelaU.dispose();
						
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(enviarTarefa, "Atividade atualizada!");
						
						
					}
				});
				janelaU.add(labelTitulo);
				janelaU.add(campoTitulo);
				janelaU.add(labelDescricao);
				janelaU.add(campoDescricao);
				janelaU.add(labelId);
				janelaU.add(campoId);
				janelaU.add(enviarTarefa);
				janelaU.setLayout(new GridLayout(10,4));
				
				janelaU.setVisible(true);
				
			}
		});
		
		btnD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame FrameDelete = new JFrame();
				FrameDelete.setSize(500,400);
				FrameDelete.setTitle("Deletar atividade");
				FrameDelete.setDefaultCloseOperation(FrameDelete.DISPOSE_ON_CLOSE);
				
				JLabel labelId = new JLabel("ID");
				JTextField campoId = new JTextField(10);
				JButton enviar = new JButton("Enviar");
				
				enviar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String idBd = campoId.getText();
						int intIdBd = Integer.parseInt(idBd);
						
						try {
							dao.deletarAtividade(intIdBd);
							tm.atualizarTable();
							JOptionPane.showMessageDialog(enviar, "Atividade removida!");
							FrameDelete.dispose();
							
						}catch (Exception e1) {
							e1.printStackTrace();
						}
						
					}
				});
				FrameDelete.add(labelId);
				FrameDelete.add(campoId);
				FrameDelete.add(enviar);
				FrameDelete.setLayout(new GridLayout(10,5));
				FrameDelete.setVisible(true);
			}
		});

		JPanel painelInferior = new JPanel();

		painelInferior.add(btnC);
		painelInferior.add(btnU);
		painelInferior.add(btnD);

		tm.atualizarTable();
		janela.setLayout(new BorderLayout());
		janela.add(scroll, BorderLayout.CENTER);
		janela.add(painelInferior, BorderLayout.SOUTH);

		janela.setVisible(true);
	}

}
