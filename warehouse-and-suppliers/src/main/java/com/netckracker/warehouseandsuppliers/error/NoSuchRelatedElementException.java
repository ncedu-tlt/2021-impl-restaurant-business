package com.netckracker.warehouseandsuppliers.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoSuchRelatedElementException extends RuntimeException {
    private String entityName;
}
