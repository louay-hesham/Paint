/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Logging;

/**
 *
 * @author lo2ay
 */
public interface Logging {
    
    public default void log(Object obj){
        System.out.println(obj);
    }
}
