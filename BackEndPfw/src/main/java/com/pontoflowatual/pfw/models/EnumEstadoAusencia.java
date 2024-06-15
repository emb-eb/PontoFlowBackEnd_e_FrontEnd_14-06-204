package com.pontoflowatual.pfw.models;


 

    public enum EnumEstadoAusencia {
    
        PEDIDO("Solicitado"),
        APROVADO("Aprovado"),
        REPROVADO("Reprovado");
    
        private final String estado;
    
        EnumEstadoAusencia(String estado) {
            this.estado = estado;
        }
    
        public String getEstado() {
            return estado;
        }
    
    }
    
    


