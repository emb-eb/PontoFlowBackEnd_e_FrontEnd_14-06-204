// pages/GestaoFuncionarios.js

import React, { useState } from 'react';
import ListaFuncionarios from './components/ListaFuncionarios';
import AdicionarFuncionario from './components/AdicionarFuncionario';

const GestaoFuncionarios = () => {
  const [funcionarios, setFuncionarios] = useState([]);

  const adicionarFuncionario = (novoFuncionario) => {
    setFuncionarios([...funcionarios, { id: Date.now(), ...novoFuncionario }]);
  }

  return (
    <div className="gestao-funcionarios">
      <h1>Gestão de Funcionários</h1>
      <ListaFuncionarios funcionarios={funcionarios} />
      <AdicionarFuncionario onAdicionar={adicionarFuncionario} />
    </div>
  );
}

export default GestaoFuncionarios;
