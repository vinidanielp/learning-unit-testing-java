package br.com.alura.adopet.api.validacoes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetComAdocaoEmAndamentoTest {
	
	@InjectMocks
	private ValidacaoPetComAdocaoEmAndamento validador;
	
	@Mock
	private AdocaoRepository adocaoRepository;
	
	@Mock
	private SolicitacaoAdocaoDto dto;
	
	
	@Test
	void naoDeveriaPermitirSolicitacaoDeAdocaoDePetComPedidoEmAndamento() {
		//ARRANGE
		BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(
				dto.idPet(),
				StatusAdocao.AGUARDANDO_AVALIACAO)
		).willReturn(true);
		
		//ACT + ASSERT
		assertThrows(ValidacaoException.class, () -> validador.validar(dto));
	}
	
	@Test
	void naoDeveriaPermitirSolicitacaoDeAdocaoDePetComPedidoInexistente() {
		//ARRANGE
		BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(
				dto.idPet(),
				StatusAdocao.AGUARDANDO_AVALIACAO)
		).willReturn(false);
		
		//ACT + ASSERT
		assertDoesNotThrow(() -> validador.validar(dto));
	}


}
