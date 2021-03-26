package br.com.gasomed.service;

import java.util.List;

import br.com.gasomed.modelo.Usuario;
import br.com.gasomed.repository.UsuarioRepository;
import br.com.gasomed.util.MensagemPainelUtil;

public class UsuarioService {
	private UsuarioRepository repositorio = new UsuarioRepository();

	public void Salvar(Usuario usuario) {
		try {
			if(usuario.getId() == null) {
				this.repositorio.Salvar(usuario);
				MensagemPainelUtil.Sucesso("USUARIO CADASTRADO COM SUCESSO");
			} else {
				this.repositorio.Editar(usuario);
				MensagemPainelUtil.Sucesso("EDICAO CONCLUIDA");
			}
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
		}

	}

	public void Remover(Usuario usuario) {
		try {
			this.repositorio.Excluir(usuario);
			MensagemPainelUtil.Sucesso("USUARIO EXCLUIDO!!");
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Remover!! " + e.getMessage());
		}

	}

	public List<Usuario> ListandoUsuario() {
		try {
			return repositorio.Listar();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar!! " + e.getMessage());
			return null;
		}

	}

	public Usuario BuscandoId(Long id) {
		try {
			return this.repositorio.BuscarPorId(id);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por ID!! " + e.getMessage());
			return null;
		}

	}

	public List<Usuario> ListandoPorNome(String login) {
		try {
			return this.repositorio.ListarPorNome(login);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar valores por Nome!! " + e.getMessage());
			return null;
		}

	}
	
	public Usuario BuscandoPorNome(String login) {
		try {
			return this.repositorio.BuscarPorLogin(login);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
	
	public boolean VerificaSeTemDados() {
		try {
			return this.repositorio.VerTabelaVazia();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!!" + e.getMessage());
			return false;
		}
	}
	
	public List<String> ListarSoLogins(){
		try {
			return this.repositorio.ListarSomenteUsuarios();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar Nomes!!" + e.getMessage());
			return null;
		}
	}
	
	public Usuario VerificandoCampos(String login, String cpf) {
		try {
			return this.repositorio.VerificarSeExiste(login, cpf);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
	
	public Usuario Autentica(String login, String senha) {
		try {
			return this.repositorio.AutenticaGamb(login, senha);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
}
