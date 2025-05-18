package br.com.lista.controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.com.lista.dao.ListaDAO;
import br.com.lista.model.ListadeAtividade;
import br.com.lista.table.TableMethod;

public class listaController {
	ListaDAO dao = new ListaDAO();
	ListadeAtividade listaAtividade = new ListadeAtividade();
	TableMethod tm = new TableMethod();

	public void adicionarAtividade() {
		JFrame janelaC = new JFrame();
	    janelaC.setSize(400, 300);
	    janelaC.setTitle("Criar atividade");
	    janelaC.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    janelaC.setLocationRelativeTo(null); // Centraliza na tela
	    
	    // Criando um painel principal com margens
	    JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
	    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    
	    // Painel para os campos de formulário usando GridBagLayout
	    JPanel painelForm = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5, 5, 5, 5);
	    
	    // Configurando campos e labels
	    JLabel labelTitulo = new JLabel("Título:");
	    JTextField campoTitulo = new JTextField(20);
	    
	    JLabel labelDescricao = new JLabel("Descrição:");
	    JTextField campoDescricao = new JTextField(20);
	    
	    // Configurando os radio buttons
	    JPanel painelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JRadioButton atividadePendente = new JRadioButton("Pendente");
	    JRadioButton atividadeFeita = new JRadioButton("Feita");
	    atividadePendente.setSelected(true); // Default para pendente
	    
	    ButtonGroup grupo = new ButtonGroup();
	    grupo.add(atividadePendente);
	    grupo.add(atividadeFeita);
	    
	    painelRadio.add(atividadePendente);
	    painelRadio.add(atividadeFeita);
	    
	    // Adicionando componentes ao painelForm com GridBagLayout
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.EAST;
	    painelForm.add(labelTitulo, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.weightx = 1.0;
	    painelForm.add(campoTitulo, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.weightx = 0.0;
	    painelForm.add(labelDescricao, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.weightx = 1.0;
	    painelForm.add(campoDescricao, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.weightx = 0.0;
	    painelForm.add(new JLabel("Status:"), gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.WEST;
	    painelForm.add(painelRadio, gbc);
	    
	    // Painel para os botões
	    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JButton enviarTarefa = new JButton("Enviar");
	    JButton cancelarTarefa = new JButton("Cancelar");
	    
	    cancelarTarefa.addActionListener(_ -> janelaC.dispose());
	    
	    painelBotoes.add(cancelarTarefa);
	    painelBotoes.add(enviarTarefa);
	    
	    enviarTarefa.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String titleBD = campoTitulo.getText();
	            String descBD = campoDescricao.getText();
	            
	            if (titleBD.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(janelaC, "O título não pode estar vazio!", 
	                        "Erro", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            if (atividadePendente.isSelected()) {
	                String ap = "Pendente";
	                listaAtividade.setStatus(ap);
	            } else if (atividadeFeita.isSelected()) {
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
	                JOptionPane.showMessageDialog(janelaC, "Erro ao cadastrar: " + e1.getMessage(), 
	                        "Erro", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            JOptionPane.showMessageDialog(null, "Atividade Cadastrada!");
	        }
	    });
	    
	    // Montando a janela
	    painelPrincipal.add(painelForm, BorderLayout.CENTER);
	    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	    janelaC.add(painelPrincipal);
	    
	    janelaC.setVisible(true);
	}

	public void atualizarAtividade() {
		JFrame janelaU = new JFrame();
	    janelaU.setSize(400, 320);
	    janelaU.setTitle("Atualizar Atividade");
	    janelaU.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    janelaU.setLocationRelativeTo(null); // Centraliza na tela
	    
	    // Criando um painel principal com margens
	    JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
	    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    
	    // Painel para os campos de formulário usando GridBagLayout
	    JPanel painelForm = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5, 5, 5, 5);
	    
	    // Configurando campos e labels
	    JLabel labelId = new JLabel("ID:");
	    JTextField campoId = new JTextField(10);
	    
	    JLabel labelTitulo = new JLabel("Título:");
	    JTextField campoTitulo = new JTextField(20);
	    
	    JLabel labelDescricao = new JLabel("Descrição:");
	    JTextField campoDescricao = new JTextField(20);
	    
	    // Configurando os radio buttons
	    JPanel painelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JRadioButton atividadePendente = new JRadioButton("Pendente");
	    JRadioButton atividadeFeita = new JRadioButton("Feita");
	    atividadePendente.setSelected(true); // Default para pendente
	    
	    ButtonGroup grupo = new ButtonGroup();
	    grupo.add(atividadePendente);
	    grupo.add(atividadeFeita);
	    
	    painelRadio.add(atividadePendente);
	    painelRadio.add(atividadeFeita);
	    
	    // Adicionando componentes ao painelForm com GridBagLayout
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.EAST;
	    painelForm.add(labelId, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.weightx = 1.0;
	    painelForm.add(campoId, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.weightx = 0.0;
	    painelForm.add(labelTitulo, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.weightx = 1.0;
	    painelForm.add(campoTitulo, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.weightx = 0.0;
	    painelForm.add(labelDescricao, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.weightx = 1.0;
	    painelForm.add(campoDescricao, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.weightx = 0.0;
	    painelForm.add(new JLabel("Status:"), gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    gbc.anchor = GridBagConstraints.WEST;
	    painelForm.add(painelRadio, gbc);
	    
	    // Painel para os botões
	    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JButton enviarTarefa = new JButton("Atualizar");
	    JButton cancelarTarefa = new JButton("Cancelar");
	    
	    cancelarTarefa.addActionListener(_ -> janelaU.dispose());
	    
	    painelBotoes.add(cancelarTarefa);
	    painelBotoes.add(enviarTarefa);
	    
	    enviarTarefa.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String titleBD = campoTitulo.getText();
	            String descBD = campoDescricao.getText();
	            String idBd = campoId.getText();
	            
	            if (idBd.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(janelaU, "O ID não pode estar vazio!", 
	                        "Erro", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            if (titleBD.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(janelaU, "O título não pode estar vazio!", 
	                        "Erro", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            try {
	                int idBdToInt = Integer.parseInt(idBd);
	                
	                if (atividadePendente.isSelected()) {
	                    String ap = "Pendente";
	                    listaAtividade.setStatus(ap);
	                } else if (atividadeFeita.isSelected()) {
	                    String af = "Feita";
	                    listaAtividade.setStatus(af);
	                }
	                
	                listaAtividade.setData_criacao(new Date());
	                listaAtividade.setId(idBdToInt);
	                listaAtividade.setTitulo(titleBD);
	                listaAtividade.setDescricao(descBD);
	                
	                dao.atualizarAtividade(listaAtividade);
	                tm.atualizarTable();
	                janelaU.dispose();
	                JOptionPane.showMessageDialog(null, "Atividade atualizada!");
	                
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(janelaU, "ID inválido! Digite um número.", 
	                        "Erro", JOptionPane.ERROR_MESSAGE);
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(janelaU, "Erro ao atualizar: " + e1.getMessage(), 
	                        "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
	    
	    // Montando a janela
	    painelPrincipal.add(painelForm, BorderLayout.CENTER);
	    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	    janelaU.add(painelPrincipal);
	    
	    janelaU.setVisible(true);
	}

	public void deletarAtividade() {
		JFrame frameDelete = new JFrame();
		frameDelete.setSize(300, 180);
		frameDelete.setTitle("Deletar atividade");
		frameDelete.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frameDelete.setLocationRelativeTo(null); // Centraliza na tela

		// Criando um painel principal com margens
		JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Painel para os campos de formulário
		JPanel painelForm = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel labelId = new JLabel("ID:");
		JTextField campoId = new JTextField(10);

		// Adicionando componentes ao painelForm
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		painelForm.add(labelId, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1.0;
		painelForm.add(campoId, gbc);

		// Painel para os botões
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton enviar = new JButton("Deletar");
		JButton cancelar = new JButton("Cancelar");

		cancelar.addActionListener(_ -> frameDelete.dispose());

		painelBotoes.add(cancelar);
		painelBotoes.add(enviar);

		enviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idBd = campoId.getText();

				if (idBd.trim().isEmpty()) {
					JOptionPane.showMessageDialog(frameDelete, "O ID não pode estar vazio!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					int intIdBd = Integer.parseInt(idBd);

					// Confirmação antes de deletar
					int opcao = JOptionPane.showConfirmDialog(frameDelete,
							"Tem certeza que deseja remover a atividade ID " + intIdBd + "?", "Confirmação",
							JOptionPane.YES_NO_OPTION);

					if (opcao == JOptionPane.YES_OPTION) {
						dao.deletarAtividade(intIdBd);
						tm.atualizarTable();
						JOptionPane.showMessageDialog(null, "Atividade removida!");
						frameDelete.dispose();
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frameDelete, "ID inválido! Digite um número.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frameDelete, "Erro ao remover: " + e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Montando a janela
		painelPrincipal.add(painelForm, BorderLayout.CENTER);
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
		frameDelete.add(painelPrincipal);

		frameDelete.setVisible(true);
	}
}
