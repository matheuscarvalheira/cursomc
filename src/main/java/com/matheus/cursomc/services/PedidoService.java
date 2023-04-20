package com.matheus.cursomc.services;

import com.matheus.cursomc.domain.ItemPedido;
import com.matheus.cursomc.domain.PagamentoComBoleto;
import com.matheus.cursomc.domain.Pedido;
import com.matheus.cursomc.domain.enums.EstadoPagamento;
import com.matheus.cursomc.repositories.ItemPedidoRepository;
import com.matheus.cursomc.repositories.PagamentoRepository;
import com.matheus.cursomc.repositories.PedidoRepository;
import com.matheus.cursomc.repositories.ProdutoRepository;
import com.matheus.cursomc.services.exceptions.ObjectNotFoundtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PedidoRepository repo;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundtException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }
}
