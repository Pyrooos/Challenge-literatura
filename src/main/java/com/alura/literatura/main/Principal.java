package com.alura.literatura.main;

import com.alura.literatura.ConvertirDatos;
import com.alura.literatura.services.ConsumoAPI;

import java.net.URL;

public class Principal {

        private ConsumoAPI consumoAPI = new ConsumoAPI();
        private ConvertirDatos convertirDatos = new ConvertirDatos();

        public void muestraMenu(){
                var json = consumoAPI.getDatos(URL_BASE);
        }

}
