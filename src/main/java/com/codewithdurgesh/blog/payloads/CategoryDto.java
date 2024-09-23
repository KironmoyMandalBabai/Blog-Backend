package com.codewithdurgesh.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.util.Introspection;
@Getter
@NoArgsConstructor
@Setter
public class CategoryDto {
    private Integer categoryId;
    private  String categoryTitle;
    private  String categoryDescription;

}
