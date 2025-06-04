package com.lorian.lorianBank.Conta.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.Conta.TipoConta;

public record ContaPostDTO(TipoConta tipo, UUID cliente_id) {

}
