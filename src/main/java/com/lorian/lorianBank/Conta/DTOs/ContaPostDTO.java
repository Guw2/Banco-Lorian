package com.lorian.lorianBank.conta.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.conta.TipoConta;

public record ContaPostDTO(TipoConta tipo, UUID cliente_id) {

}
