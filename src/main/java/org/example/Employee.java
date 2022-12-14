package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Employee<T extends Number, U extends String> {
    T id;
    U name;
    T salary;
}
