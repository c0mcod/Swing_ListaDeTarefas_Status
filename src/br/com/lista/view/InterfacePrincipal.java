package br.com.lista.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.lista.controller.listaController;
import br.com.lista.table.*;

public class InterfacePrincipal extends TableMethod {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws SQLException {
		TableMethod tm = new TableMethod();
		listaController lc = new listaController();

		JFrame janela = new JFrame();
		janela.setSize(700, 500);
		janela.setTitle("Gerenciador de atividade");
		janela.setDefaultCloseOperation(janela.EXIT_ON_CLOSE);

		JPanel painelTitulo = new JPanel();
		painelTitulo.setBackground(new Color(230, 230, 250));
		JLabel titulo = new JLabel("Lista de Atividades");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		painelTitulo.add(titulo);
		painelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));

		JButton btnC = new JButton("Criar atividade");
		JButton btnU = new JButton("Atualizar atividade");
		JButton btnD = new JButton("Deletar atividade");

		Dimension tamanhoBotao = new Dimension(160, 30);
		btnC.setPreferredSize(tamanhoBotao);
		btnU.setPreferredSize(tamanhoBotao);
		btnD.setPreferredSize(tamanhoBotao);

		btnC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lc.adicionarAtividade();
			}
		});

		btnU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lc.atualizarAtividade();
			}
		});

		btnD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lc.deletarAtividade();
			}
		});

		JPanel painelInferior = new JPanel();

		painelInferior.add(btnC);
		painelInferior.add(btnU);
		painelInferior.add(btnD);

		painelInferior.setBorder(new EmptyBorder(10, 0, 10, 0));

		tm.atualizarTable();
		janela.setLayout(new BorderLayout());

		janela.setLayout(new BorderLayout());
		janela.add(painelTitulo, BorderLayout.NORTH);
		janela.add(scroll, BorderLayout.CENTER);
		janela.add(painelInferior, BorderLayout.SOUTH);
		janela.setVisible(true);
	}

}
