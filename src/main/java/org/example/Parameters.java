package org.example;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parameters {

    Boolean notNull;
    Boolean unique;
    Boolean primaryKey;

}
