package com.head.btgpactual.challenger.dto;

import java.math.BigDecimal;

public record OrderItemEvent (String produto, Integer quantidade, BigDecimal preco){
}
