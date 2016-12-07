package br.com.jpa.programa;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.jpa.entity.Federacao;
import br.com.jpa.helper.Helper;

public class CadastroFederacao {

	public static void main(String[] args) {

		while (true) {
			try {
				String sigla = showInputDialog("Informe a sigla: ");
				String uf = showInputDialog("Informe a uf: ");

				Federacao federacao = new Federacao();
				federacao.setSigla(sigla);
				federacao.setUf(uf);

				salvarFederacao(federacao);
			} catch (Exception e) {
				showMessageDialog(null, "Erro: " + e.getMessage());

			}
			int opcao = showConfirmDialog(null, "Deseja continuar?", "Confirma", JOptionPane.YES_NO_OPTION);
			if (opcao != JOptionPane.YES_OPTION) {
				break;

			}
		}
	}

	private static void salvarFederacao(Federacao federacao) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();

		Helper dao = new Helper(em);

		dao.salvarFederacao(federacao);
		showMessageDialog(null, "Federação incluída com sucesso.");
	}
}
