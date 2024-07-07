package com.head.btgpactual.challenger.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {

    private final String product;

    @Field(targetType = FieldType.DECIMAL128)
    private final BigDecimal price;

    private final Integer quantity;
}
