/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jinvindia.inventory.hrank;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artist{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        
        String firstName;
        String lastName;    
        
        public Artist(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
}
