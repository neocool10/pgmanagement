package com.neocool.pgmanagement;

import com.neocool.pgmanagement.controller.MController;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MController mcontroller = new MController();
        try{
            mcontroller.main();
        } catch(Exception e) {
            
        }
        
    }
}
