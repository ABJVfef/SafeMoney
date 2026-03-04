package com.safemoney.services;

import com.safemoney.domains.*;

import com.safemoney.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Service
@Profile({"dev", "test"}) // roda automaticamente em dev e test
public class DBService implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;
    @Autowired
    private CentroCustoRepository centroCustoRepository;
    @Autowired
    private EntidadeRepository entidadeRepository;
    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initDB();
    }

    @Transactional
    public void initDB() {


        Usuario user1 = new Usuario(null, "Usuário Teste", "teste@email.com");
        usuarioRepository.save(user1);


        CentroCusto c1 = new CentroCusto();
        c1.setUsuario(user1);
        c1.setNome("Alimentação");
        c1.setCodigo("ALIM");
        c1.setAtivo(true);

        CentroCusto c2 = new CentroCusto();
        c2.setUsuario(user1);
        c2.setNome("Moradia");
        c2.setCodigo("MORA");
        c2.setAtivo(true);

        centroCustoRepository.saveAll(Arrays.asList(c1, c2));


        Entidade e1 = new Entidade();
        e1.setUsuario(user1);
        e1.setNome("Supermercado Extra");
        e1.setTipo("Fornecedor");

        Entidade e2 = new Entidade();
        e2.setUsuario(user1);
        e2.setNome("Empresa XYZ (Salário)");
        e2.setTipo("Cliente");

        entidadeRepository.saveAll(Arrays.asList(e1, e2));


        ContaBancaria conta1 = new ContaBancaria();
        conta1.setUsuario(user1);
        conta1.setApelido("Conta Principal");
        conta1.setInstituicao("Banco Digital");
        conta1.setSaldoInicial(new BigDecimal("1000.00"));
        conta1.setDataSaldoInicial(LocalDate.now().withDayOfMonth(1));
        conta1.setAtiva(true);

        contaBancariaRepository.save(conta1);


        CartaoCredito cartao1 = new CartaoCredito();
        cartao1.setUsuario(user1);
        cartao1.setApelido("Nubank");
        cartao1.setBandeira("Mastercard");
        cartao1.setEmissor("Nubank");
        cartao1.setFechamentoFaturaDia(5);
        cartao1.setVencimentoFaturaDia(12);
        cartao1.setAtivo(true);

        cartaoCreditoRepository.save(cartao1);

        Endereco end1 = new Endereco();
        end1.setRua("Tchurusbangos");
        end1.setNumero("420");
        end1.setCidade("Extrema");
        end1.setUsuario(user1);

        Endereco end2 = new Endereco();
        end2.setRua("Tchurusbagos");
        end2.setNumero("666");
        end2.setCidade("Ferpa");
        end2.setUsuario(user1);

        enderecoRepository.save(end1);
        enderecoRepository.save(end2);


        System.out.println("🚀 DBService: dados iniciais inseridos (perfil dev/test).");
    }
}
