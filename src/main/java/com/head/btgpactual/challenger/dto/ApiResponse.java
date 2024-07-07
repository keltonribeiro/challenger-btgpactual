package com.head.btgpactual.challenger.dto;

import java.io.Serializable;
import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse paginationResponse) implements Serializable {
}
